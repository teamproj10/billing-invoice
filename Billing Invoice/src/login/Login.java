package login;

import invoice.DBServices;
import invoice.User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import company.Company;

public class Login implements ActionListener{
	JButton btnNewFrame;
	JTextField userText;
	JPasswordField passwordText;
	DBServices service=new DBServices();
	JPanel currentContentPane;
	public Login(JPanel contentPane){
		contentPane.removeAll();	
		
		JLabel headingLabel = new JLabel("");
		headingLabel = new JLabel("User Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		headingLabel.setBounds(310, 180, 100, 30);
		contentPane.add(headingLabel);
		
		userText = new JTextField();
		userText.setFont(new Font("Times New Roman",  Font.PLAIN, 16));
		userText.setBounds(450, 180, 250, 30);
		contentPane.add(userText);
		
		headingLabel = new JLabel("Password");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		headingLabel.setBounds(310, 230, 100, 30);
		contentPane.add(headingLabel);
		
		passwordText = new JPasswordField();
		passwordText.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		passwordText.setBounds(450, 230, 250, 30);
		contentPane.add(passwordText);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		loginButton.setBounds(450, 325, 78, 35);
		loginButton.addActionListener(this);
		contentPane.add(loginButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	public void actionPerformed(ActionEvent e) {
		if(userText.getText().equalsIgnoreCase("")){
			JOptionPane.showMessageDialog(null, "Enter User Name");
		}else if(passwordText.getText().equalsIgnoreCase("")){
			JOptionPane.showMessageDialog(null, "Enter Passowrd");
		}else{
			User resultUser=service.userAuthentication(userText.getText(), passwordText.getText());
			if(resultUser!=null){
				new Company(currentContentPane,resultUser);
			}else{
				JOptionPane.showMessageDialog(null, "Please enter correct user name and password");
			}
		}
    } 
}
