package menu;

import invoice.DBServices;
import invoice.User;
import login.Login;
import projects.PMAcceptDeveloperHours;
import projects.Projects;
import reports.Reports;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clients.Clients;
import company.Company;
import employees.AssignEmployee;
import employees.EmployeeHours;
import employees.Employees;
import generateinvoice.GenerateInvoices;
import importfiles.Import;

public class Menu implements ActionListener{
	JPanel currentContentPane,child;
	DBServices service=new DBServices();
	public Menu(final JPanel contentPane,final User user){
		contentPane.removeAll();
		
		JLabel headingLabel = new JLabel("");
		headingLabel.setForeground(Color.BLUE);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		headingLabel.setBounds(250, 35, 650, 45);
		contentPane.add(headingLabel);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		logoutButton.setBounds(850, 75, 100, 35);
		logoutButton.addActionListener(this);
		contentPane.add(logoutButton);
		if(user!=null && user.getUserrole().equalsIgnoreCase("Accountant")){
			String maintainLabels[] = {"MAINTAIN","EMPLOYEE", "CLIENT", "PROJECT", "COMPANY"};
		    final JComboBox maintainComboBox = new JComboBox(maintainLabels);	    
		    maintainComboBox.setMaximumRowCount(7);
		    maintainComboBox.setSelectedIndex(0);
		    maintainComboBox.setBounds(100, 100, 125, 35);
		    maintainComboBox.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 if(maintainComboBox.getSelectedIndex() != -1) {                     
		        		if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("Maintain")){
		        			JOptionPane.showMessageDialog(null, "Please select any one of Maintain Menu option.");
			     		}
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("EMPLOYEE")){
		     				new Employees(contentPane,user);
		     			}
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("CLIENT")){
		     				new Clients(contentPane,user);
		     			}
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("PROJECT")){
		     				new Projects(contentPane,user);
		     			}
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("COMPANY")){
		     				new Company(contentPane,user);
		     			}
		     		}
		         }
			});	    
		    contentPane.add(maintainComboBox);
		
		    JButton addemployeeButton = new JButton("Generate Invoices");
			addemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			addemployeeButton.setBounds(230, 100, 200, 35);
			addemployeeButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 new GenerateInvoices(contentPane,user);
		         }
			});
			contentPane.add(addemployeeButton);
		    
			String facultyLabels[] = {"Reports","Invoice Report", "Project Report", "Budget Report", "Payroll Report"};
			final JComboBox facultyComboBox = new JComboBox(facultyLabels);
			facultyComboBox.setMaximumRowCount(5);
			facultyComboBox.setSelectedIndex(0);
			facultyComboBox.setBounds(450, 100, 100, 35);
			facultyComboBox.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 if(facultyComboBox.getSelectedIndex() != -1) {   
		        		if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Reports")){
		        			JOptionPane.showMessageDialog(null, "Please select any one of Reports Menu option.");
					    }
		        		if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Invoice Report")){
		        			new Reports().invoiceReport(contentPane,user);
			     		}
		     			if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Project Report")){
		     				new Reports().projectReport(contentPane,user);
		     			}
		     			if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Budget Report")){
		     				new Reports().budgetReport(contentPane,user);
		     			}
		     			if(facultyComboBox.getItemAt(facultyComboBox.getSelectedIndex()).equals("Payroll Report")){
		     				new Reports().payrollReport(contentPane,user);
		     			}
		     		}
		         }
			});	    
			contentPane.add(facultyComboBox);
			
			JButton importButton = new JButton("Import");
			importButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			importButton.setBounds(575, 100, 200, 35);
			importButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 new Import(contentPane,null,user);
		         }
			});
			contentPane.add(importButton);
		}
		
		if(user!=null && user.getUserrole().equalsIgnoreCase("Project Manager")){
			String maintainLabels[] = {"MAINTAIN","EMPLOYEE", "PROJECT", "ASSIGN DEVELOPERS"};
		    final JComboBox maintainComboBox = new JComboBox(maintainLabels);	    
		    maintainComboBox.setMaximumRowCount(7);
		    maintainComboBox.setSelectedIndex(0);
		    maintainComboBox.setBounds(100, 100, 125, 35);
		    maintainComboBox.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 if(maintainComboBox.getSelectedIndex() != -1) {                     
		        		if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("Maintain")){
		        			JOptionPane.showMessageDialog(null, "Please select any one of Maintain Menu option.");
			     		}
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("EMPLOYEE")){
		     				new Employees(contentPane,user);
		     			}
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("PROJECT")){
		     				new Projects(contentPane,user);
		     			}
		     			if(maintainComboBox.getItemAt(maintainComboBox.getSelectedIndex()).equals("ASSIGN DEVELOPERS")){
		     				new AssignEmployee(contentPane,user);
		     			}
		     		}
		         }
			});	    
		    contentPane.add(maintainComboBox);
		
			String reportLabels[] = {"Reports","Project Report", "Budget Report"};
			final JComboBox reportComboBox = new JComboBox(reportLabels);
			reportComboBox.setMaximumRowCount(5);
			reportComboBox.setSelectedIndex(0);
			reportComboBox.setBounds(230, 100, 100, 35);
			reportComboBox.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 if(reportComboBox.getSelectedIndex() != -1) {   
		        		if(reportComboBox.getItemAt(reportComboBox.getSelectedIndex()).equals("Reports")){
		        			JOptionPane.showMessageDialog(null, "Please select any one of Reports Menu option.");
					    }
		        		if(reportComboBox.getItemAt(reportComboBox.getSelectedIndex()).equals("Project Report")){
		     				new Reports().projectReport(contentPane,user);
		     			}
		     			if(reportComboBox.getItemAt(reportComboBox.getSelectedIndex()).equals("Budget Report")){
		     				new Reports().budgetReport(contentPane,user);
		     			}		     			
		     		}
		         }
			});	    
			contentPane.add(reportComboBox);
			
			JButton addemployeeButton = new JButton("Approved Developer Hours");
			addemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			addemployeeButton.setBounds(350, 100, 250, 35);
			addemployeeButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 new PMAcceptDeveloperHours(contentPane,user);
		         }
			});
			contentPane.add(addemployeeButton);
		}
		
		if(user!=null && user.getUserrole().equalsIgnoreCase("Developer")){
			JButton addemployeeButton = new JButton("Developer Work Time");
			addemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			addemployeeButton.setBounds(230, 100, 200, 35);
			addemployeeButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 new EmployeeHours().addEmployeeHours(contentPane,user);
		         }
			});
			contentPane.add(addemployeeButton);
		}
		
//		String[] tokens=service.getCompany();
		
//		headingLabel = new JLabel("COMPANY INFORMATION");
//		headingLabel.setForeground(Color.BLACK);
//		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
//		headingLabel.setBounds(250, 150, 400, 40);
//		contentPane.add(headingLabel);
//		
//		headingLabel = new JLabel("Name : "+tokens[0]);
//		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		headingLabel.setBounds(100, 200, 400, 25);
//		contentPane.add(headingLabel);
//		
//		headingLabel = new JLabel("Address Line1 : "+tokens[1]);
//		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		headingLabel.setBounds(100, 230, 400, 25);
//		contentPane.add(headingLabel);
//		
//		headingLabel = new JLabel("Address Line2 : "+tokens[2]);
//		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		headingLabel.setBounds(100, 260, 100, 25);
//		contentPane.add(headingLabel);
//		
//		headingLabel = new JLabel("City : "+tokens[3]);
//		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		headingLabel.setBounds(100, 290, 400, 25);
//		contentPane.add(headingLabel);
//		
//		headingLabel = new JLabel("State : "+tokens[4]);
//		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		headingLabel.setBounds(100, 320, 400, 25);
//		contentPane.add(headingLabel);
//		
//		headingLabel = new JLabel("Zip : "+tokens[5]);
//		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
//		headingLabel.setBounds(100, 350, 100, 25);
//		contentPane.add(headingLabel);
		
		
		currentContentPane=contentPane;
		contentPane.repaint();
	}
	public void actionPerformed(ActionEvent e) {
		new Login(currentContentPane);
    } 
}
