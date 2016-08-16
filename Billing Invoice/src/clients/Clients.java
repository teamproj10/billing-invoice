package clients;

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

import company.Company;

public class Clients implements ActionListener{
	JTextField number,name,addressLine1,addressLine2,city,state,zip,email,contact;
	JComboBox invoiceFreqSelectionBox,billingTermsSelectionBox,invoiceGroupSelectionBox;
	DBServices service=new DBServices();
	JPanel currentContentPane;
	String rowIndexPointer="";
	User tempuser=null;
	public Clients(){
		
	}
	public Clients(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		tempuser=user;
		JLabel headingLabel = new JLabel("VIEW CLIENTS");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(250, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList clientList=service.getAllClients();
		String clientColumnNames[] = {"Number", "Name", "Address Line 1","Address Line 2", "City", "State","Zip", "Email", "Contact", "Invoice Freq", "Billing Terms", "Invoice Grouping"};
		int columnsCount=12;			
		String clientRowColumnDataValues[][]=new String[0][columnsCount];
		if(clientList!=null && clientList.size()!=0){
			clientRowColumnDataValues=new String[clientList.size()][columnsCount];
			Iterator iterator=clientList.iterator();
			int poistion=0;
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				clientRowColumnDataValues[poistion][0]=tokens[0];
				clientRowColumnDataValues[poistion][1]=tokens[1];
				clientRowColumnDataValues[poistion][2]=tokens[2];
				clientRowColumnDataValues[poistion][3]=tokens[3];
				clientRowColumnDataValues[poistion][4]=tokens[4];
				clientRowColumnDataValues[poistion][5]=tokens[5];
				clientRowColumnDataValues[poistion][6]=tokens[6];
				clientRowColumnDataValues[poistion][7]=tokens[7];
				clientRowColumnDataValues[poistion][8]=tokens[8];
				clientRowColumnDataValues[poistion][9]=tokens[9];
				clientRowColumnDataValues[poistion][10]=tokens[10];
				clientRowColumnDataValues[poistion][11]=tokens[11];
				poistion=poistion+1;
			}			
		}	
		
		final JTable clientTable = new JTable(clientRowColumnDataValues, clientColumnNames);
	    ListSelectionModel tableRowSelection = clientTable.getSelectionModel();
	    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		rowIndexPointer=""+clientTable.getSelectedRow();
	    	}
	    });	    
		JScrollPane tableScrollPane = new JScrollPane(clientTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);
		
		JButton addclientButton = new JButton("Add Client");
		addclientButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		addclientButton.setBounds(700, 200, 200, 40);
		addclientButton.addActionListener(this);
		contentPane.add(addclientButton);
		
		JButton updateclientButton = new JButton("Update Client");
		updateclientButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		updateclientButton.setBounds(700, 260, 200, 40);
		updateclientButton.addActionListener(this);
		contentPane.add(updateclientButton);
		
		JButton deleteButton = new JButton("InActive Client");
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
	
	public void addNewClient(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("ADD NEW CLIENT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Number");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		number = new JTextField();
		number.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		number.setBounds(250, 200, 250, 25);
		contentPane.add(number);
		
		headingLabel = new JLabel("Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		name = new JTextField();
		name.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		name.setBounds(250, 230, 250, 25);
		contentPane.add(name);
		
		headingLabel = new JLabel("Address Line1");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 125, 25);
		contentPane.add(headingLabel);
		
		addressLine1 = new JTextField();
		addressLine1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		addressLine1.setBounds(250, 260, 250, 25);
		contentPane.add(addressLine1);

		headingLabel = new JLabel("Address Line2");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 100, 25);
		contentPane.add(headingLabel);
		
		addressLine2 = new JTextField();
		addressLine2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		addressLine2.setBounds(250, 290, 250, 25);
		contentPane.add(addressLine2);
		
		headingLabel = new JLabel("City");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 320, 100, 25);
		contentPane.add(headingLabel);
		
		city = new JTextField();
		city.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		city.setBounds(250, 320, 250, 25);
		contentPane.add(city);
		
		headingLabel = new JLabel("State");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 350, 100, 25);
		contentPane.add(headingLabel);
		
		state = new JTextField();
		state.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		state.setBounds(250, 350, 250, 25);
		contentPane.add(state);
		
		headingLabel = new JLabel("Zip");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 380, 125, 25);
		contentPane.add(headingLabel);
		
		zip = new JTextField();
		zip.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		zip.setBounds(250, 380, 250, 25);
		contentPane.add(zip);
		
		headingLabel = new JLabel("Email");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 410, 120, 25);
		contentPane.add(headingLabel);
		
		email = new JTextField();
		email.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		email.setBounds(250, 410, 250, 25);
		contentPane.add(email);

		headingLabel = new JLabel("Contact");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 440, 100, 25);
		contentPane.add(headingLabel);
		
		contact = new JTextField();
		contact.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contact.setBounds(250, 440, 250, 25);
		contentPane.add(contact);
		
		headingLabel = new JLabel("Select Invoice Freq");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(75, 470, 125, 25);
		contentPane.add(headingLabel);
		
		invoiceFreqSelectionBox = new JComboBox();
		invoiceFreqSelectionBox.addItem("Weekly");
		invoiceFreqSelectionBox.addItem("BiWeekly");
		invoiceFreqSelectionBox.addItem("Monthly");
		invoiceFreqSelectionBox.addItem("Monthly-Cal");		
		//JScrollPane invoiceFreqSelectionBoxScrollPane = new JScrollPane(invoiceFreqSelectionBox);
		invoiceFreqSelectionBox.setBounds(250, 470, 250, 25);
	    contentPane.add(invoiceFreqSelectionBox);
		
		headingLabel = new JLabel("Select Billing Terms");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(75, 500, 125, 25);
		contentPane.add(headingLabel);
		
		billingTermsSelectionBox = new JComboBox();
		billingTermsSelectionBox.addItem("Due on Recipt");
		billingTermsSelectionBox.addItem("Net 10 Days");
		billingTermsSelectionBox.addItem("Net 20 Days");
		billingTermsSelectionBox.addItem("Net 30 Days");
		billingTermsSelectionBox.addItem("Net 60 Days");
		//JScrollPane billingTermsSelectionBoxScrollPane = new JScrollPane(billingTermsSelectionBox);
		billingTermsSelectionBox.setBounds(250, 500, 250, 25);
	    contentPane.add(billingTermsSelectionBox);
		
		headingLabel = new JLabel("Select Invoice Grouping");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(75, 530, 125, 25);
		contentPane.add(headingLabel);
		
		invoiceGroupSelectionBox = new JComboBox();
		invoiceGroupSelectionBox.addItem("Project");
		invoiceGroupSelectionBox.addItem("Invoice");
		//JScrollPane invoiceGroupSelectionBoxScrollPane = new JScrollPane(invoiceGroupSelectionBox);
		invoiceGroupSelectionBox.setBounds(250, 530, 250, 25);
	    contentPane.add(invoiceGroupSelectionBox);
		
		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(400, 575, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(250, 575, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void updateClient(JPanel contentPane,String[] tokens,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("UPDATE CLIENT");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		headingLabel = new JLabel("Number");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 200, 100, 25);
		contentPane.add(headingLabel);
		
		number = new JTextField();
		number.setText(tokens[0]);
		number.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		number.setBounds(250, 200, 250, 25);
		contentPane.add(number);
		
		headingLabel = new JLabel("Name");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 230, 100, 25);
		contentPane.add(headingLabel);
		
		name = new JTextField();
		name.setText(tokens[1]);
		name.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		name.setBounds(250, 230, 250, 25);
		contentPane.add(name);
		
		headingLabel = new JLabel("Address Line1");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 260, 125, 25);
		contentPane.add(headingLabel);
		
		addressLine1 = new JTextField();
		addressLine1.setText(tokens[2]);
		addressLine1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		addressLine1.setBounds(250, 260, 250, 25);
		contentPane.add(addressLine1);

		headingLabel = new JLabel("Address Line2");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 290, 100, 25);
		contentPane.add(headingLabel);
		
		addressLine2 = new JTextField();
		addressLine2.setText(tokens[3]);
		addressLine2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		addressLine2.setBounds(250, 290, 250, 25);
		contentPane.add(addressLine2);
		
		headingLabel = new JLabel("City");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 320, 100, 25);
		contentPane.add(headingLabel);
		
		city = new JTextField();
		city.setText(tokens[4]);
		city.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		city.setBounds(250, 320, 250, 25);
		contentPane.add(city);
		
		headingLabel = new JLabel("State");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 350, 100, 25);
		contentPane.add(headingLabel);
		
		state = new JTextField();
		state.setText(tokens[5]);
		state.setFont(new Font("Times New Roman",  Font.PLAIN, 14));
		state.setBounds(250, 350, 250, 25);
		contentPane.add(state);
		
		headingLabel = new JLabel("Zip");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 380, 125, 25);
		contentPane.add(headingLabel);
		
		zip = new JTextField();
		zip.setText(tokens[6]);
		zip.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		zip.setBounds(250, 380, 250, 25);
		contentPane.add(zip);
		
		headingLabel = new JLabel("Email");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 410, 120, 25);
		contentPane.add(headingLabel);
		
		email = new JTextField();
		email.setText(tokens[7]);
		email.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		email.setBounds(250, 410, 250, 25);
		contentPane.add(email);

		headingLabel = new JLabel("Contact");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(100, 440, 100, 25);
		contentPane.add(headingLabel);
		
		contact = new JTextField();
		contact.setText(tokens[8]);
		contact.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contact.setBounds(250, 440, 250, 25);
		contentPane.add(contact);
		
		headingLabel = new JLabel("Select Invoice Freq");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(75, 470, 125, 25);
		contentPane.add(headingLabel);
		
		invoiceFreqSelectionBox = new JComboBox();
		invoiceFreqSelectionBox.addItem("Weekly");
		invoiceFreqSelectionBox.addItem("BiWeekly");
		invoiceFreqSelectionBox.addItem("Monthly");
		invoiceFreqSelectionBox.addItem("Monthly-Cal");	
		invoiceFreqSelectionBox.setSelectedItem(tokens[9]);
		//JScrollPane invoiceFreqSelectionBoxScrollPane = new JScrollPane(invoiceFreqSelectionBox);
		invoiceFreqSelectionBox.setBounds(250, 470, 250, 25);
	    contentPane.add(invoiceFreqSelectionBox);
		
		headingLabel = new JLabel("Select Billing Terms");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(75, 500, 125, 25);
		contentPane.add(headingLabel);
		
		billingTermsSelectionBox = new JComboBox();
		billingTermsSelectionBox.addItem("Due on Recipt");
		billingTermsSelectionBox.addItem("Net 10 Days");
		billingTermsSelectionBox.addItem("Net 20 Days");
		billingTermsSelectionBox.addItem("Net 30 Days");
		billingTermsSelectionBox.addItem("Net 60 Days");
		billingTermsSelectionBox.setSelectedItem(tokens[10]);
		//JScrollPane billingTermsSelectionBoxScrollPane = new JScrollPane(billingTermsSelectionBox);
		billingTermsSelectionBox.setBounds(250, 500, 250, 25);
	    contentPane.add(billingTermsSelectionBox);
		
		headingLabel = new JLabel("Select Invoice Grouping");
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		headingLabel.setBounds(75, 530, 125, 25);
		contentPane.add(headingLabel);
		
		invoiceGroupSelectionBox = new JComboBox();
		invoiceGroupSelectionBox.addItem("Project");
		invoiceGroupSelectionBox.addItem("Invoice");
		invoiceGroupSelectionBox.setSelectedItem(tokens[11]);
		//JScrollPane invoiceGroupSelectionBoxScrollPane = new JScrollPane(invoiceGroupSelectionBox);
		invoiceGroupSelectionBox.setBounds(250, 530, 250, 25);
	    contentPane.add(invoiceGroupSelectionBox);
		
		JButton saveButton = new JButton("Update");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(400, 575, 100, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(250, 575, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Back")){
			String[] tokens=service.getCompany();
			new Clients(currentContentPane,tempuser);
		}
		if(e.getActionCommand().equalsIgnoreCase("Add Client")){
			addNewClient(currentContentPane,tempuser);
		}
		if(e.getActionCommand().equalsIgnoreCase("Update Client")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList clientList=service.getAllClients();
				String[] tokens=(String[])clientList.get(Integer.parseInt(rowIndexPointer));
				updateClient(currentContentPane,tokens,tempuser);
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("InActive Client")){
			if(rowIndexPointer.trim().length()!=0){
				ArrayList clientList=service.getAllClients();
				service.inactiveClient((String[])clientList.get(Integer.parseInt(rowIndexPointer)));
				new Clients(currentContentPane,tempuser);
			}else{
				JOptionPane.showMessageDialog(null, "Please select row of the table.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Save")){
			if(name!=null && number!=null && addressLine1!=null && addressLine2!=null && city!=null && state!=null && zip!=null && email!=null && contact!=null && invoiceFreqSelectionBox!=null && billingTermsSelectionBox!=null && invoiceGroupSelectionBox!=null){
				if(name.getText().trim().length()!=0 && number.getText().trim().length()!=0 && addressLine1.getText().trim().length()!=0 && addressLine2.getText().trim().length()!=0 && city.getText().trim().length()!=0 && state.getText().trim().length()!=0 && zip.getText().trim().length()!=0 && email.getText().trim().length()!=0 && contact.getText().trim().length()!=0){
					String invoicefrequency=(String)(invoiceFreqSelectionBox.getItemAt(invoiceFreqSelectionBox.getSelectedIndex()));
					String billingterms=(String)(billingTermsSelectionBox.getItemAt(billingTermsSelectionBox.getSelectedIndex()));
					String invoicegroup=(String)(invoiceGroupSelectionBox.getItemAt(invoiceGroupSelectionBox.getSelectedIndex()));
					String[] tokens=new String[12];
					tokens[0]=number.getText().trim();
					tokens[1]=name.getText().trim();
					tokens[2]=addressLine1.getText().trim();
					tokens[3]=addressLine2.getText().trim();
					tokens[4]=city.getText().trim();
					tokens[5]=state.getText().trim();
					tokens[6]=zip.getText().trim();
					tokens[7]=email.getText().trim();
					tokens[8]=contact.getText().trim();
					tokens[9]=invoicefrequency.trim();
					tokens[10]=billingterms.trim();
					tokens[11]=invoicegroup.trim();
					service.addNewClient(tokens);					
					new Clients(currentContentPane,tempuser);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new client details.");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new client details.");
			}
		}
		if(e.getActionCommand().equalsIgnoreCase("Update")){
			if(name!=null && addressLine1!=null && addressLine2!=null && city!=null && state!=null && zip!=null && email!=null && contact!=null && invoiceFreqSelectionBox!=null && billingTermsSelectionBox!=null && invoiceGroupSelectionBox!=null){
				if(name.getText().trim().length()!=0 && addressLine1.getText().trim().length()!=0 && addressLine2.getText().trim().length()!=0 && city.getText().trim().length()!=0 && state.getText().trim().length()!=0 && zip.getText().trim().length()!=0 && email.getText().trim().length()!=0 && contact.getText().trim().length()!=0){
					String invoicefrequency=(String)(invoiceFreqSelectionBox.getItemAt(invoiceFreqSelectionBox.getSelectedIndex()));
					String billingterms=(String)(billingTermsSelectionBox.getItemAt(billingTermsSelectionBox.getSelectedIndex()));
					String invoicegroup=(String)(invoiceGroupSelectionBox.getItemAt(invoiceGroupSelectionBox.getSelectedIndex()));
					String[] tokens=new String[12];
					tokens[0]=number.getText().trim();
					tokens[1]=name.getText().trim();
					tokens[2]=addressLine1.getText().trim();
					tokens[3]=addressLine2.getText().trim();
					tokens[4]=city.getText().trim();
					tokens[5]=state.getText().trim();
					tokens[6]=zip.getText().trim();
					tokens[7]=email.getText().trim();
					tokens[8]=contact.getText().trim();
					tokens[9]=invoicefrequency.trim();
					tokens[10]=billingterms.trim();
					tokens[11]=invoicegroup.trim();
					service.updateClient(tokens);					
					new Clients(currentContentPane,tempuser);
				}else{
					JOptionPane.showMessageDialog(null, "Please enter all add new client details.");
				}
			}else{
				JOptionPane.showMessageDialog(null, "Please enter all add new client details.");
			}
		}
    }
}
