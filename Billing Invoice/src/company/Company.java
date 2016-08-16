package company;

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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import employees.Employees;

public class Company implements ActionListener{
	JTextField name,address1,address2,city,state,zip;
	DBServices service=new DBServices();
	JPanel currentContentPane;
	String rowIndexPointer="";
	User tempUser=null;
	public Company(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		tempUser=user;
		
		String[] tokens=service.getCompany();
		
		JLabel headingLabel = new JLabel("COMPANY INFORMATION");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 400, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Name : "+tokens[0]);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(200, 200, 400, 25);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Address Line1 : "+tokens[1]);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(200, 230, 400, 25);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Address Line2 : "+tokens[2]);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(200, 260, 400, 25);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("City : "+tokens[3]);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(200, 290, 400, 25);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("State : "+tokens[4]);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(200, 320, 400, 25);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Zip : "+tokens[5]);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(200, 350, 100, 25);
		contentPane.add(headingLabel);
		
		if(user!=null && user.getUserrole().equalsIgnoreCase("Accountant")){
			JButton updateemployeeButton = new JButton("Update Company");
			updateemployeeButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			updateemployeeButton.setBounds(325, 400, 200, 40);
			updateemployeeButton.addActionListener(this);
			contentPane.add(updateemployeeButton);
		
			JButton backButton = new JButton("Back");
			backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			backButton.setBounds(200, 400, 100, 40);
			backButton.addActionListener(this);
			contentPane.add(backButton);
		}
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void updateCompany(JPanel contentPane,User user,String tokens[]){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("UPDATE COMPANY INFORMATION");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 400, 40);
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
		
		headingLabel = new JLabel("Address Line1");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		address1 = new JTextField();
		address1.setText(tokens[1]);
		address1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		address1.setBounds(250, 230, 250, 25);
		contentPane.add(address1);
		
		headingLabel = new JLabel("Address Line2");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 100, 25);
		contentPane.add(headingLabel);
		
		address2 = new JTextField();
		address2.setText(tokens[2]);
		address2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		address2.setBounds(250, 260, 250, 25);
		contentPane.add(address2);
		
		headingLabel = new JLabel("City");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 125, 25);
		contentPane.add(headingLabel);
		
		city = new JTextField();
		city.setText(tokens[3]);
		city.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		city.setBounds(250, 290, 250, 25);
		contentPane.add(city);
		
		headingLabel = new JLabel("State");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 320, 125, 25);
		contentPane.add(headingLabel);
		
		state = new JTextField();
		state.setText(tokens[4]);
		state.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		state.setBounds(250, 320, 250, 25);
		contentPane.add(state);
		
		headingLabel = new JLabel("Zip");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 350, 125, 25);
		contentPane.add(headingLabel);
		
		zip = new JTextField();
		zip.setText(tokens[5]);
		zip.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		zip.setBounds(250, 350, 250, 25);
		contentPane.add(zip);
		
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
			new Company(currentContentPane,tempUser);
		}
		if(e.getActionCommand().equalsIgnoreCase("Update Company")){
			String[] tokens=service.getCompany();
			updateCompany(currentContentPane,tempUser,tokens);
		}
		if(e.getActionCommand().equalsIgnoreCase("Update")){
			if(name!=null && address1!=null && city!=null && state!=null && zip!=null){
				if(name.getText().trim().length()!=0 && address1.getText().trim().length()!=0 && city.getText().trim().length()!=0 && state.getText().trim().length()!=0 && zip.getText().trim().length()!=0){
					String[] tokens=new String[6];
					tokens[0]=name.getText().trim();
					tokens[1]=address1.getText().trim();
					tokens[2]=address2.getText().trim();
					tokens[3]=city.getText().trim();
					tokens[4]=state.getText().trim();
					tokens[5]=zip.getText().trim();
					service.updateCompanyInformation(tokens);
					new Company(currentContentPane,tempUser);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new course details.");
			}
		}
    }	  
}
