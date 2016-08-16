package importfiles;

import invoice.DBServices;
import invoice.User;
import menu.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import company.Company;

public class Import implements ActionListener{
	DBServices service=new DBServices();
	JComboBox importSelectionBox;
	JPanel currentContentPane;
	User tempuser=null;
	String filePath="";
	public Import(JPanel contentPane,ArrayList tempProjectList,User user){
		new Menu(contentPane,user);		
		tempuser=user;
		JLabel headingLabel = new JLabel("Import Files");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		JLabel lblNewLabel = new JLabel("Upload File");
		lblNewLabel.setBounds(175, 200, 100, 20);
		contentPane.add(lblNewLabel);
		
		final JTextField textField = new JTextField();
		textField.setBounds(250, 200, 175, 25);
		contentPane.add(textField);	
		
		lblNewLabel = new JLabel("Import Type");
		lblNewLabel.setBounds(175, 230, 100, 20);
		contentPane.add(lblNewLabel);
		
		importSelectionBox = new JComboBox();
		importSelectionBox.addItem("Company");
		importSelectionBox.addItem("Clients");
		importSelectionBox.addItem("Projects");
		importSelectionBox.addItem("Employees");		
		//JScrollPane importSelectionBoxScrollPane = new JScrollPane(importSelectionBox);
		importSelectionBox.setBounds(250, 230, 150, 30);
	    contentPane.add(importSelectionBox);
		
		JButton browse = new JButton("Browse");
		browse.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent ae){
				 JFileChooser fileChooser = new JFileChooser();
				 int returnValue = fileChooser.showOpenDialog(null);
				 if(returnValue == JFileChooser.APPROVE_OPTION){
					 File selectedFile = fileChooser.getSelectedFile();
					 textField.setText(selectedFile.getAbsolutePath());
					 filePath=selectedFile.getAbsolutePath();
					 textField.setEditable(false);
				 }
			 }
		 });
		browse.setBounds(425, 200, 100, 30);
		contentPane.add(browse);
		
		JButton updateProjectButton = new JButton("Import");
		updateProjectButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateProjectButton.setBounds(350, 360, 100, 40);
		updateProjectButton.addActionListener(this);
		contentPane.add(updateProjectButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(250, 525, 100, 40);
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Company(currentContentPane,tempuser);
			}
		});
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Import")){
			String selectedImportType=(String)(importSelectionBox.getItemAt(importSelectionBox.getSelectedIndex()));
			if(filePath!=null && selectedImportType!=null){
				if(filePath.trim().length()!=0 && selectedImportType.trim().length()!=0){
					service.importDataFromFile(filePath, selectedImportType);
					new Company(currentContentPane,tempuser);
				}
			}
		}
	}
}
