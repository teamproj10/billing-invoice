package employees;

import invoice.DBServices;
import invoice.User;
import menu.Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import company.Company;

public class Employees implements ActionListener{
	JTextField name,title,billrate;
	JComboBox roleSelectionBox;
	DBServices service=new DBServices();
	JPanel currentContentPane;
	String rowIndexPointer="";
	User tempUser=null;
	public Employees(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		tempUser=user;
		JLabel headingLabel = new JLabel("VIEW EMPLOYEES");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList employeeList=service.getAllEmployees();
		int columnsCount=4;
		String employeeColumnNames[] = {"Name", "Title", "Bill Rate","Role"};
		String employeeRowColumnDataValues[][]=new String[0][columnsCount];
		if(employeeList!=null && employeeList.size()!=0){
			employeeRowColumnDataValues=new String[employeeList.size()][columnsCount];
			Iterator iterator=employeeList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				employeeRowColumnDataValues[poistion][0]=tokens[0];
				employeeRowColumnDataValues[poistion][1]=tokens[1];
				employeeRowColumnDataValues[poistion][2]=tokens[2];
				employeeRowColumnDataValues[poistion][3]=tokens[3];
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
		
		if(user!=null && user.getUserrole().equalsIgnoreCase("Accountant")){
			JButton addemployeeButton = new JButton("Add Employee");
			addemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			addemployeeButton.setBounds(700, 200, 200, 40);
			addemployeeButton.addActionListener(this);
			contentPane.add(addemployeeButton);
		}
		
		JButton updateemployeeButton = new JButton("Update Employee");
		updateemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateemployeeButton.setBounds(700, 260, 200, 40);
		updateemployeeButton.addActionListener(this);
		contentPane.add(updateemployeeButton);
		
		JButton deleteButton = new JButton("InActive Employees");
		deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		deleteButton.setBounds(700, 320, 200, 40);
		deleteButton.addActionListener(this);
		contentPane.add(deleteButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(300, 525, 100, 40);
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
		
		JLabel headingLabel = new JLabel("ADD NEW EMPLOYEE");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		name = new JTextField();
		name.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		name.setBounds(250, 200, 250, 25);
		contentPane.add(name);
		
		headingLabel = new JLabel("Title");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		title = new JTextField();
		title.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		title.setBounds(250, 230, 250, 25);
		contentPane.add(title);
		
		headingLabel = new JLabel("Bill Rate");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 100, 25);
		contentPane.add(headingLabel);
		
		billrate = new JTextField();
		billrate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		billrate.setBounds(250, 260, 250, 25);
		contentPane.add(billrate);
		
		headingLabel = new JLabel("Role");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 125, 25);
		contentPane.add(headingLabel);
		
		roleSelectionBox = new JComboBox();
		roleSelectionBox.addItem("Project Manager");
		roleSelectionBox.addItem("Developer");		
		//JScrollPane invoiceFreqSelectionBoxScrollPane = new JScrollPane(roleSelectionBox);
		roleSelectionBox.setBounds(250, 290, 250, 25);
	    contentPane.add(roleSelectionBox);
		
		JButton saveButton = new JButton("Save");
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
	
	public void updateEmployee(JPanel contentPane,String[] tokens,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("UPDATE EMPLOYEE");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		name = new JTextField();
		name.setText(tokens[0]);
		name.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		name.setBounds(250, 200, 250, 25);
		contentPane.add(name);
		
		headingLabel = new JLabel("Title");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		title = new JTextField();
		title.setText(tokens[1]);
		title.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		title.setBounds(250, 230, 250, 25);
		contentPane.add(title);
		
		headingLabel = new JLabel("Bill Rate");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 100, 25);
		contentPane.add(headingLabel);
		
		billrate = new JTextField();
		billrate.setText(tokens[2]);
		billrate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		billrate.setBounds(250, 260, 250, 25);
		contentPane.add(billrate);
		
		headingLabel = new JLabel("Role");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 125, 25);
		contentPane.add(headingLabel);
		
		roleSelectionBox = new JComboBox();
		roleSelectionBox.addItem("Project Manager");
		roleSelectionBox.addItem("Developer");
		roleSelectionBox.setSelectedItem(tokens[3]);
		//JScrollPane invoiceFreqSelectionBoxScrollPane = new JScrollPane(roleSelectionBox);
		roleSelectionBox.setBounds(250, 290, 250, 25);
	    contentPane.add(roleSelectionBox);
		
		JButton saveButton = new JButton("Update");
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
			new Employees(currentContentPane,tempUser);
		}
		if(e.getActionCommand().equalsIgnoreCase("Add Employee")){
			addNewEmployee(currentContentPane,tempUser);
		}
		if(e.getActionCommand().equalsIgnoreCase("Update Employee")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList employeeList=service.getAllEmployees();
				String[] tokens=(String[])employeeList.get(Integer.parseInt(rowIndexPointer));
				updateEmployee(currentContentPane,tokens,tempUser);
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("InActive Employee")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList employeeList=service.getAllEmployees();
				service.inactiveEmployee((String[])employeeList.get(Integer.parseInt(rowIndexPointer)));
				new Employees(currentContentPane,tempUser);
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Save")){
			if(name!=null && title!=null && billrate!=null && roleSelectionBox!=null){
				if(name.getText().trim().length()!=0 && title.getText().trim().length()!=0 && billrate.getText().trim().length()!=0){
					String role=(String)(roleSelectionBox.getItemAt(roleSelectionBox.getSelectedIndex()));
					String[] tokens=new String[4];
					tokens[0]=name.getText().trim();
					tokens[1]=title.getText().trim();
					tokens[2]=billrate.getText().trim();
					tokens[3]=role.trim();
					service.addNewEmployee(tokens);
					new Employees(currentContentPane,tempUser);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Update")){
			if(name!=null && title!=null && billrate!=null && roleSelectionBox!=null){
				if(name.getText().trim().length()!=0 && title.getText().trim().length()!=0 && billrate.getText().trim().length()!=0){
					String role=(String)(roleSelectionBox.getItemAt(roleSelectionBox.getSelectedIndex()));
					String[] tokens=new String[4];
					tokens[0]=name.getText().trim();
					tokens[1]=title.getText().trim();
					tokens[2]=billrate.getText().trim();
					tokens[3]=role.trim();
					service.updateEmployee(tokens);
					new Employees(currentContentPane,tempUser);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
			}
		}
    }
}
