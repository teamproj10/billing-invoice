package employees;

import invoice.DBServices;
import invoice.User;
import menu.Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import company.Company;

public class AssignEmployee implements ActionListener{
	JTextField name,title,billrate;
	JComboBox projectSelectionBox,developerSelectionBox;
	DBServices service=new DBServices();
	JPanel currentContentPane;
	String rowIndexPointer="";
	User tempUser=null;
	public AssignEmployee(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		tempUser=user;
		JLabel headingLabel = new JLabel("VIEW ASSIGNED DEVELOPERS");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList assignDeveloperList=service.getAssignedDevelopers();
		int columnsCount=5;
		String employeeColumnNames[] = {"Client Number", "Porject Number", "Developer Name", "Bill Rate", "Assign Date"};
		String employeeRowColumnDataValues[][]=new String[0][columnsCount];
		if(assignDeveloperList!=null && assignDeveloperList.size()!=0){
			employeeRowColumnDataValues=new String[assignDeveloperList.size()][columnsCount];
			Iterator iterator=assignDeveloperList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				employeeRowColumnDataValues[poistion][0]=tokens[0];
				employeeRowColumnDataValues[poistion][1]=tokens[1];
				employeeRowColumnDataValues[poistion][2]=tokens[2];
				employeeRowColumnDataValues[poistion][3]=tokens[3];
				employeeRowColumnDataValues[poistion][4]=tokens[4];
				poistion=poistion+1;
			}			
		}
		final JTable employeeTable = new JTable(employeeRowColumnDataValues, employeeColumnNames);
	    ListSelectionModel tableRowSelection = employeeTable.getSelectionModel();
	    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		rowIndexPointer=""+employeeTable.getSelectedRow();
	    	}
	    });	    
		JScrollPane tableScrollPane = new JScrollPane(employeeTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);
		
		JButton updateemployeeButton = new JButton("Assign Developer");
		updateemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateemployeeButton.setBounds(400, 500, 200, 40);
		updateemployeeButton.addActionListener(this);
		contentPane.add(updateemployeeButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(250, 500, 100, 40);
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Company(currentContentPane,tempUser);
			}
		});
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void addNewEmployee(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("ASSIGN DEVELOPER");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList projectList=service.getAllActiveProjects();
		ArrayList employeeList=service.getAllActiveEmployees();
		
		headingLabel = new JLabel("Select Project");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 125, 25);
		contentPane.add(headingLabel);
		
		projectSelectionBox = new JComboBox();
		if(projectList!=null && projectList.size()!=0){
			for(int index=0;index<projectList.size();index++){
				String tokens[]=(String[])projectList.get(index);
				projectSelectionBox.addItem(tokens[2]);
			}
		}		
		//JScrollPane projectSelectionBoxScrollPane = new JScrollPane(projectSelectionBox);
		projectSelectionBox.setBounds(250, 200, 250, 25);
	    contentPane.add(projectSelectionBox);
	    
	    headingLabel = new JLabel("Select Developer");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 125, 25);
		contentPane.add(headingLabel);
		
		developerSelectionBox = new JComboBox();
		if(employeeList!=null && employeeList.size()!=0){
			for(int index=0;index<employeeList.size();index++){
				String tokens[]=(String[])employeeList.get(index);
				developerSelectionBox.addItem(tokens[0]);
			}
		}	
		//JScrollPane developerSelectionBoxScrollPane = new JScrollPane(developerSelectionBox);
		developerSelectionBox.setBounds(250, 230, 250, 25);
	    contentPane.add(developerSelectionBox);
		
		JButton saveButton = new JButton("Assign");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 400, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(400, 400, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Back")){
			String[] tokens=service.getCompany();
			addNewEmployee(currentContentPane,tempUser);
		}
		if(e.getActionCommand().equalsIgnoreCase("Assign Developer")){
			addNewEmployee(currentContentPane,tempUser);
		}
		if(e.getActionCommand().equalsIgnoreCase("Assign")){
			if(projectSelectionBox!=null && developerSelectionBox!=null){
				ArrayList projectList=service.getAllActiveProjects();
				String[] projecttokens=(String[])projectList.get(projectSelectionBox.getSelectedIndex());
				String selectedDeveloper=(String)(developerSelectionBox.getItemAt(developerSelectionBox.getSelectedIndex()));
				SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
				String[] tokens=new String[4];
				tokens[0]=projecttokens[1];
				tokens[1]=selectedDeveloper;
				tokens[2]=sdf.format(new Date());
				tokens[3]=projecttokens[0];
				service.insertassigneDevelopertoProject(tokens);
				new AssignEmployee(currentContentPane,tempUser);
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
			}
		}
    }
	
}
