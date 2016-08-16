package projects;

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

import clients.Clients;
import company.Company;

public class Projects implements ActionListener{
	JTextField client,projectNumber,projectName,startDate,endDate,status,projectManager,clientContact,budget;

	DBServices service=new DBServices();
	JPanel currentContentPane;
	String rowIndexPointer="";
	User tempuser=null;
	public Projects(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		tempuser=user;
		JLabel headingLabel = new JLabel("VIEW PROJECTS");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList projectList=service.getAllProjects();
		int columnsCount=9;
		String projectColumnNames[] = {"Client", "Project Number", "Project Name","State Date", "End Date", "Status","Project Manager", "Client Contact", "Budget"};
		String projectRowColumnDataValues[][]=new String[0][columnsCount];
		if(projectList!=null && projectList.size()!=0){
			projectRowColumnDataValues=new String[projectList.size()][columnsCount];		
			Iterator iterator=projectList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				projectRowColumnDataValues[poistion][0]=tokens[0];
				projectRowColumnDataValues[poistion][1]=tokens[1];
				projectRowColumnDataValues[poistion][2]=tokens[2];
				projectRowColumnDataValues[poistion][3]=tokens[3];
				projectRowColumnDataValues[poistion][4]=tokens[4];
				projectRowColumnDataValues[poistion][5]=tokens[5];
				projectRowColumnDataValues[poistion][6]=tokens[6];
				projectRowColumnDataValues[poistion][7]=tokens[7];
				projectRowColumnDataValues[poistion][8]=tokens[8];
				poistion=poistion+1;
			}			
		}
		
		final JTable ProjectTable = new JTable(projectRowColumnDataValues, projectColumnNames);
	    ListSelectionModel tableRowSelection = ProjectTable.getSelectionModel();
	    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		rowIndexPointer=""+ProjectTable.getSelectedRow();
	    	}
	    });	    
		JScrollPane tableScrollPane = new JScrollPane(ProjectTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);
		
		if(user!=null && user.getUserrole().equalsIgnoreCase("Accountant")){
			JButton addProjectButton = new JButton("Add Project");
			addProjectButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			addProjectButton.setBounds(700, 200, 200, 40);
			addProjectButton.addActionListener(this);
			contentPane.add(addProjectButton);
		}
		JButton updateProjectButton = new JButton("Update Project");
		updateProjectButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateProjectButton.setBounds(700, 260, 200, 40);
		updateProjectButton.addActionListener(this);
		contentPane.add(updateProjectButton);
		
		JButton deleteButton = new JButton("InActive Project");
		deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		deleteButton.setBounds(700, 320, 200, 40);
		deleteButton.addActionListener(this);
		contentPane.add(deleteButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(300, 525, 100, 40);
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Company(currentContentPane,tempuser);
			}
		});
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void addProject(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("ADD NEW PROJECT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Client");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		client = new JTextField();
		client.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		client.setBounds(250, 200, 250, 25);
		contentPane.add(client);
		
		headingLabel = new JLabel("Project Number");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		projectNumber = new JTextField();
		projectNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		projectNumber.setBounds(250, 230, 250, 25);
		contentPane.add(projectNumber);
		
		headingLabel = new JLabel("Project Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 125, 25);
		contentPane.add(headingLabel);
		
		projectName = new JTextField();
		projectName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		projectName.setBounds(250, 260, 250, 25);
		contentPane.add(projectName);

		headingLabel = new JLabel("Start Date");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 100, 25);
		contentPane.add(headingLabel);
		
		startDate = new JTextField();
		startDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		startDate.setBounds(250, 290, 250, 25);
		contentPane.add(startDate);
		
		headingLabel = new JLabel("End Date");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 320, 100, 25);
		contentPane.add(headingLabel);
		
		endDate = new JTextField();
		endDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		endDate.setBounds(250, 320, 250, 25);
		contentPane.add(endDate);
		
		headingLabel = new JLabel("Status");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 350, 120, 25);
		contentPane.add(headingLabel);
		
		status = new JTextField();
		status.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		status.setBounds(250, 350, 250, 25);
		contentPane.add(status);
		
		headingLabel = new JLabel("Project Manager");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 380, 125, 25);
		contentPane.add(headingLabel);
		
		projectManager = new JTextField();
		projectManager.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		projectManager.setBounds(250, 380, 250, 25);
		contentPane.add(projectManager);
		
		headingLabel = new JLabel("Client Contact");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 410, 125, 25);
		contentPane.add(headingLabel);
		
		clientContact = new JTextField();
		clientContact.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		clientContact.setBounds(250, 410, 250, 25);
		contentPane.add(clientContact);

		headingLabel = new JLabel("Budget");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 440, 120, 25);
		contentPane.add(headingLabel);
		
		budget = new JTextField();
		budget.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		budget.setBounds(250, 440, 250, 25);
		contentPane.add(budget);
		
		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 475, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(400, 475, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void updateProject(JPanel contentPane,String[] tokens,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("UPDATE PROJECT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Client");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		client = new JTextField();
		client.setText(tokens[1]);
		client.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		client.setBounds(250, 200, 250, 25);
		contentPane.add(client);
		
		headingLabel = new JLabel("Project Number");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		//contentPane.add(headingLabel);
		
		projectNumber = new JTextField();
		projectNumber.setText(tokens[0]);
		projectNumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		projectNumber.setBounds(250, 230, 250, 25);
		//contentPane.add(projectNumber);
		
		headingLabel = new JLabel("Project Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 125, 25);
		contentPane.add(headingLabel);
		
		projectName = new JTextField();
		projectName.setText(tokens[2]);
		projectName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		projectName.setBounds(250, 260, 250, 25);
		contentPane.add(projectName);

		headingLabel = new JLabel("Start Date");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 100, 25);
		contentPane.add(headingLabel);
		
		startDate = new JTextField();
		startDate.setText(tokens[3]);
		startDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		startDate.setBounds(250, 290, 250, 25);
		contentPane.add(startDate);
		
		headingLabel = new JLabel("End Date");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 320, 100, 25);
		contentPane.add(headingLabel);
		
		endDate = new JTextField();
		endDate.setText(tokens[4]);
		endDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		endDate.setBounds(250, 320, 250, 25);
		contentPane.add(endDate);
		
		headingLabel = new JLabel("Status");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 350, 120, 25);
		contentPane.add(headingLabel);
		
		status = new JTextField();
		status.setText(tokens[5]);
		status.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		status.setBounds(250, 350, 250, 25);
		contentPane.add(status);
		
		headingLabel = new JLabel("Project Manager");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 380, 125, 25);
		contentPane.add(headingLabel);
		
		projectManager = new JTextField();
		projectManager.setText(tokens[6]);
		projectManager.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		projectManager.setBounds(250, 380, 250, 25);
		contentPane.add(projectManager);
		
		headingLabel = new JLabel("Client Contact");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 410, 125, 25);
		contentPane.add(headingLabel);
		
		clientContact = new JTextField();
		clientContact.setText(tokens[7]);
		clientContact.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		clientContact.setBounds(250, 410, 250, 25);
		contentPane.add(clientContact);

		headingLabel = new JLabel("Budget");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 440, 120, 25);
		contentPane.add(headingLabel);
		
		budget = new JTextField();
		budget.setText(tokens[8]);
		budget.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		budget.setBounds(250, 440, 250, 25);
		contentPane.add(budget);
		
		JButton saveButton = new JButton("Update");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 475, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(400, 475, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Back")){
			String[] tokens=service.getCompany();
			new Projects(currentContentPane,tempuser);
		}
		if(e.getActionCommand().equalsIgnoreCase("Add Project")){
			addProject(currentContentPane,tempuser);
		}
		if(e.getActionCommand().equalsIgnoreCase("Update Project")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList projectList=service.getAllProjects();
				updateProject(currentContentPane,(String[])projectList.get(Integer.parseInt(rowIndexPointer)),tempuser);
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}			
		}
		if(e.getActionCommand().equalsIgnoreCase("InActive Project")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList projectList=service.getAllProjects();
				service.inactiveProject((String[])projectList.get(Integer.parseInt(rowIndexPointer)));
				new Projects(currentContentPane,tempuser);
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Save")){			
			if(client!=null && projectNumber!=null && projectName!=null && startDate!=null && endDate!=null && status!=null && projectManager!=null && clientContact!=null && budget!=null){
				if(client.getText().trim().length()!=0 && projectNumber.getText().trim().length()!=0 && projectName.getText().trim().length()!=0 && startDate.getText().trim().length()!=0 && endDate.getText().trim().length()!=0 && status.getText().trim().length()!=0 && projectManager.getText().trim().length()!=0 && clientContact.getText().trim().length()!=0 && budget.getText().trim().length()!=0){
					String[] tokens=new String[9];
					tokens[0]=client.getText().trim();
					tokens[1]=projectNumber.getText().trim();
					tokens[2]=projectName.getText().trim();
					tokens[3]=startDate.getText().trim();
					tokens[4]=endDate.getText().trim();
					tokens[5]=status.getText().trim();
					tokens[6]=projectManager.getText().trim();
					tokens[7]=clientContact.getText().trim();
					tokens[8]=budget.getText().trim();
					service.addNewProject(tokens);
					new Projects(currentContentPane,tempuser);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new project details.");
				}					
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new project details.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Update")){
			if(client!=null && projectNumber!=null && startDate!=null && endDate!=null && status!=null && projectManager!=null && clientContact!=null && budget!=null){
				if(client.getText().trim().length()!=0 && projectName.getText().trim().length()!=0 && startDate.getText().trim().length()!=0 && endDate.getText().trim().length()!=0 && status.getText().trim().length()!=0 && projectManager.getText().trim().length()!=0 && clientContact.getText().trim().length()!=0 && budget.getText().trim().length()!=0){
					String[] tokens=new String[9];
					tokens[0]=client.getText().trim();
					tokens[1]=projectNumber.getText().trim();
					tokens[2]=projectName.getText().trim();
					tokens[3]=startDate.getText().trim();
					tokens[4]=endDate.getText().trim();
					tokens[5]=status.getText().trim();
					tokens[6]=projectManager.getText().trim();
					tokens[7]=clientContact.getText().trim();
					tokens[8]=budget.getText().trim();
					service.updateProject(tokens);
					new Projects(currentContentPane,tempuser);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new project details.");
				}					
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new project details.");
			}
		}
    }
}
