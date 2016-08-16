package projects;

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
import employees.AssignEmployee;
import employees.EmployeeHours;

public class PMAcceptDeveloperHours implements ActionListener{
	JTextField name,hours,overhours;
	DBServices service=new DBServices();
	JPanel currentContentPane;
	String rowIndexPointer="";
	User tempUser=null;
	public PMAcceptDeveloperHours(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		tempUser=user;
		JLabel headingLabel = new JLabel("VIEW ASSIGNED DEVELOPERS");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList assignDeveloperList=service.getDevelopersHours();
		int columnsCount=8;
		String employeeColumnNames[] = {"Client Number", "Porject Number", "Developer Name", "Date", "Hours", "Over Hours", "Bill Rate", "Approve Status"};
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
				employeeRowColumnDataValues[poistion][5]=tokens[5];
				employeeRowColumnDataValues[poistion][6]=tokens[6];
				employeeRowColumnDataValues[poistion][7]=tokens[7];
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
		
		JButton updateemployeeButton = new JButton("Approve Developer Hours");
		updateemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateemployeeButton.setBounds(400, 550, 250, 40);
		updateemployeeButton.addActionListener(this);
		contentPane.add(updateemployeeButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(200, 550, 100, 40);
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Company(currentContentPane,tempUser);
			}
		});
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void approveDeveloperHours(JPanel contentPane,String[] hourtoken,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("APPROVE DEVELOPER HOURS");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Project Number");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		name = new JTextField();
		name.setText(hourtoken[1]);
		name.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		name.setBounds(250, 200, 250, 25);
		name.setEditable(false);
		contentPane.add(name);
		
		headingLabel = new JLabel("Worked Hours");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 120, 25);
		contentPane.add(headingLabel);
		
		hours = new JTextField();
		hours.setText(hourtoken[4]);
		hours.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		hours.setBounds(250, 230, 250, 25);
		contentPane.add(hours);
		
		headingLabel = new JLabel("Over Hours");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 100, 25);
		contentPane.add(headingLabel);
		
		overhours = new JTextField();
		if(hourtoken[5]!=null && Integer.parseInt(hourtoken[5])!=0){
			overhours.setText(hourtoken[5]);
		}
		overhours.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		overhours.setBounds(250, 260, 250, 25);
		contentPane.add(overhours);
		
		JButton updateButton = new JButton("Update Hours");
		updateButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateButton.setBounds(300, 400, 150, 40);
		updateButton.addActionListener(this);
		contentPane.add(updateButton);
		
		JButton approveButton = new JButton("Approve Hours");
		approveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		approveButton.setBounds(500, 400, 150, 40);
		approveButton.addActionListener(this);
		contentPane.add(approveButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(150, 400, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Approve Developer Hours")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList assignDeveloperList=service.getDevelopersHours();
				String hourtokens[]=(String[])assignDeveloperList.get(Integer.parseInt(rowIndexPointer));
				approveDeveloperHours(currentContentPane,hourtokens,tempUser);
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Update Hours")){
			if(hours.getText().trim().length()!=0 && overhours.getText().trim().length()!=0){
				ArrayList assignDeveloperList=service.getDevelopersHours();
				String hourtokens[]=(String[])assignDeveloperList.get(Integer.parseInt(rowIndexPointer));
				service.updateEmployeeHours(hourtokens[2], hourtokens[3], hours.getText().trim(), overhours.getText());
				JOptionPane.showMessageDialog(null, "Successfully Update worked hours.");
				new PMAcceptDeveloperHours(currentContentPane,tempUser);
			}else{
				JOptionPane.showMessageDialog(null, "Please enter correct details.");
			}			
		}
		if(e.getActionCommand().equalsIgnoreCase("Approve Hours")){
			if(hours.getText().trim().length()!=0 || overhours.getText().trim().length()!=0){
				ArrayList assignDeveloperList=service.getDevelopersHours();
				String hourtokens[]=(String[])assignDeveloperList.get(Integer.parseInt(rowIndexPointer));
				service.updateApprovedEmployeeHours(hourtokens[2], hourtokens[3]);
				JOptionPane.showMessageDialog(null, "Successfully Approved worked hours.");
				new PMAcceptDeveloperHours(currentContentPane,tempUser);
			}else{
				JOptionPane.showMessageDialog(null, "Please enter correct details.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Back")){
			new PMAcceptDeveloperHours(currentContentPane,tempUser);
		}
    }	
}
