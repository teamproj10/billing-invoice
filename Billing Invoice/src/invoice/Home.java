package invoice;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.Login;

public class Home extends JFrame {
	private JPanel contentPane;
	private JFrame frame;
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					Home frame = new Home();
					frame.setVisible(true);					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}
 
	public Home(){
		String current = "                                                       ";
		setTitle(current+current+"EAGLES CONSULTING COMPANY");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		DBServices dbServices=new DBServices();
//		dbServices.addallClients(dbServices.CLIENTS);
//		dbServices.addallProjects(dbServices.PROJECTS);
//		dbServices.addallUsers(dbServices.USERS);
//		dbServices.addCompanyInformation(dbServices.COMPANY);
		new Login(contentPane);
	}
}
