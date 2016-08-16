package reports;

import invoice.DBServices;
import invoice.User;
import menu.Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import company.Company;

public class Reports implements ActionListener{
	DBServices service=new DBServices();
	JPanel currentContentPane;
	User tempuser=null;
	public Reports(){
		
	}
	public void invoiceReport(JPanel contentPane,User user){
		new Menu(contentPane,user);
		tempuser=user;
		JLabel headingLabel = new JLabel("INVOICE REPORT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		ArrayList projectList=service.invoicesReport();
		System.out.println(projectList+"  dafafsad");
		String clientColumnNames[] = {"Invoice Number", "Client Name", "Project Number", "Frequnecy", "Billing Terms", "Invoice Date","Total Amount"};
		int columnsCount=7;			
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
				poistion=poistion+1;
			}			
		}
		final JTable clientTable = new JTable(projectRowColumnDataValues, clientColumnNames);
		JScrollPane tableScrollPane = new JScrollPane(clientTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(300, 525, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}	
	public void projectReport(JPanel contentPane,User user){
		new Menu(contentPane,user);
		tempuser=user;
		JLabel headingLabel = new JLabel("PROJECT REPORT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		ArrayList projectList=service.projectsReport();
		int columnsCount=9;
		String projectColumnNames[] = {"Client Name", "Project Name", "Client Contact", "Project Manager", "Budget"};
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
				poistion=poistion+1;
			}			
		}
		final JTable clientTable = new JTable(projectRowColumnDataValues, projectColumnNames);	    	    
		JScrollPane tableScrollPane = new JScrollPane(clientTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(300, 525, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}	
	public void budgetReport(JPanel contentPane,User user){
		new Menu(contentPane,user);
		tempuser=user;
		JLabel headingLabel = new JLabel("BUDGET REPORT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);	
		ArrayList projectList=service.invoicesReport();
		String clientColumnNames[] = {"Client Name", "Project Name", "Frequnecy", "Billing Terms", "Initial Budget","Paid Budget", "Balance Budget"};
		int columnsCount=7;			
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
				poistion=poistion+1;
			}			
		}
		final JTable clientTable = new JTable(projectRowColumnDataValues, clientColumnNames);	        
		JScrollPane tableScrollPane = new JScrollPane(clientTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(300, 525, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}	
	public void payrollReport(JPanel contentPane,User user){
		new Menu(contentPane,user);
		tempuser=user;
		JLabel headingLabel = new JLabel("PAYROLL REPORT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);		
		String clientColumnNames[] = {"Project Number", "Developer Name", "Bill Rate", "Worked Date", "Worked Hours", "Over Worked Hours"};
		ArrayList projectList=service.getDevelopersHours();
		int columnsCount=6;			
		String projectRowColumnDataValues[][]=new String[0][columnsCount];
		if(projectList!=null && projectList.size()!=0){
			projectRowColumnDataValues=new String[projectList.size()][columnsCount];		
			Iterator iterator=projectList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				projectRowColumnDataValues[poistion][0]=tokens[1];
				projectRowColumnDataValues[poistion][1]=tokens[2];
				projectRowColumnDataValues[poistion][2]=tokens[6];
				projectRowColumnDataValues[poistion][3]=tokens[3];
				projectRowColumnDataValues[poistion][4]=tokens[4];
				projectRowColumnDataValues[poistion][5]=tokens[5];
				poistion=poistion+1;
			}			
		}
		final JTable clientTable = new JTable(projectRowColumnDataValues, clientColumnNames);
	    JScrollPane tableScrollPane = new JScrollPane(clientTable);
		tableScrollPane.setBounds(100, 200, 600, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(300, 525, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Back")){
			new Company(currentContentPane,tempuser);
		}
    }
}
