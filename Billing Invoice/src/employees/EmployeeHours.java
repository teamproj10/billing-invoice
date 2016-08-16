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

public class EmployeeHours implements ActionListener{
	JTextField day,hours,overhours;
	DBServices service=new DBServices();
	JPanel currentContentPane;
	JComboBox projectSelectionBox;
	User tempUser=null;
	public void addEmployeeHours(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		tempUser=user;
		JLabel headingLabel = new JLabel("ADD DEVELOPER WORK HOURS");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList projectList=service.getAllDeveloperProjects(tempUser.getUsername());
		if(projectList!=null && projectList.size()!=0){
			headingLabel = new JLabel("Select Project");
			headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			headingLabel.setBounds(100, 200, 125, 25);
			contentPane.add(headingLabel);			
			projectSelectionBox = new JComboBox();		
			for(int index=0;index<projectList.size();index++){
				String tokens[]=(String[])projectList.get(index);
				projectSelectionBox.addItem(tokens[1]);
			}
			//JScrollPane projectSelectionBoxScrollPane = new JScrollPane(projectSelectionBox);
			projectSelectionBox.setBounds(250, 200, 250, 25);
		    contentPane.add(projectSelectionBox);
			
			headingLabel = new JLabel("Date");
			headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			headingLabel.setBounds(100, 230, 100, 25);
			contentPane.add(headingLabel);
			
			day = new JTextField();
			day.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
			day.setBounds(250, 230, 250, 25);
			contentPane.add(day);
			
			headingLabel = new JLabel("Ex : MM/DD/YYYY");
			headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			headingLabel.setBounds(505, 230, 120, 25);
			contentPane.add(headingLabel);
			
			headingLabel = new JLabel("Hours");
			headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			headingLabel.setBounds(100, 260, 100, 25);
			contentPane.add(headingLabel);
			
			hours = new JTextField();
			hours.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			hours.setBounds(250, 260, 250, 25);
			contentPane.add(hours);
			
			headingLabel = new JLabel("Over Hours");
			headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			headingLabel.setBounds(100, 290, 100, 25);
			contentPane.add(headingLabel);
			
			overhours = new JTextField();
			overhours.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			overhours.setBounds(250, 290, 250, 25);
			contentPane.add(overhours);
			
			JButton saveButton = new JButton("Save");
			saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			saveButton.setBounds(250, 350, 100, 40);
			saveButton.addActionListener(this);
			contentPane.add(saveButton);
			
			JButton quitButton = new JButton("Back");
			quitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
			quitButton.setBounds(400, 350, 100, 40);
			quitButton.addActionListener(this);
			contentPane.add(quitButton);
			
			currentContentPane=contentPane;
			contentPane.repaint();	
		}else{
			headingLabel = new JLabel("Please assign developer to any project");
			headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			headingLabel.setBounds(200, 230, 450, 25);
			contentPane.add(headingLabel);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Save")){
			if(day!=null && hours!=null){
				if(day.getText().trim().length()!=0 && hours.getText().trim().length()!=0){
					ArrayList projectList=service.getAllDeveloperProjects(tempUser.getUsername());
					String[] projecttokens=(String[])projectList.get(projectSelectionBox.getSelectedIndex());
					service.insertdeveloperworktimehours(projecttokens[1],tempUser.getUsername(),day.getText().trim(),hours.getText().trim(),overhours.getText().trim(),tempUser.getBillrate(),projecttokens[0]);
					JOptionPane.showMessageDialog(null, "Successfully saved worked hours.");
					new EmployeeHours().addEmployeeHours(currentContentPane,tempUser);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter correct details.");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please enter correct details.");
			}
		}		
		if(e.getActionCommand().equalsIgnoreCase("Back")){
			new Company(currentContentPane,tempUser);
		}
    }
}
