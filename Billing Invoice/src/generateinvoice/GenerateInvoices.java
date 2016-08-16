package generateinvoice;

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
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
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

public class GenerateInvoices implements ActionListener{
	JTextField day,hours;
	DBServices service=new DBServices();
	JPanel currentContentPane;
	String rowIndexPointer="";
	User tempuser=null;
	
	public GenerateInvoices(JPanel contentPane,User user){
		new Menu(contentPane,user);		
		
		JLabel headingLabel = new JLabel("GENERATE INVOICES");
		headingLabel.setForeground(Color.BLACK);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		headingLabel.setBounds(200, 150, 350, 40);
		contentPane.add(headingLabel);
		
		ArrayList invoicesList=service.generateInvoices();
		HashMap invoicesMap=new HashMap();
		if(invoicesList!=null && invoicesList.size()!=0){
			Iterator iterator=invoicesList.iterator();
			while(iterator.hasNext()){
				String[] tokens=(String[])iterator.next();
				if(invoicesMap.containsKey((String)(tokens[0]+","+tokens[1]))){
					Double amount=Double.parseDouble(tokens[6])+(Double)invoicesMap.get((String)(tokens[0]+","+tokens[1]));
					invoicesMap.put((String)((String)(tokens[0]+","+tokens[1])), amount);
				}else{
					ArrayList list=new ArrayList();
					Double amount=Double.parseDouble(tokens[6]);
					invoicesMap.put((String)((String)(tokens[0]+","+tokens[1])), amount);
				}
			}
		}
		
		String clientColumnNames[] = {"Client Number", "Project Number", "Invoice Date","Amount"};
		int columnsCount=4;			
		String clientRowColumnDataValues[][]=new String[0][columnsCount];
		if(invoicesMap!=null && invoicesMap.size()!=0){
			clientRowColumnDataValues=new String[invoicesMap.size()][columnsCount];
			int poistion=0;
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			for(Object key : invoicesMap.keySet()) {
				String[] tokens=key.toString().split(",");
				clientRowColumnDataValues[poistion][0]=tokens[0];
				clientRowColumnDataValues[poistion][1]=tokens[1];
				clientRowColumnDataValues[poistion][2]=sdf.format(new Date());
				clientRowColumnDataValues[poistion][3]=""+((Double)invoicesMap.get(key));
				//service.invoice(tokens[0], tokens[1], sdf.format(new Date()), ""+((Double)invoicesMap.get(key)));
				poistion=poistion+1;
			}		
		}
		final JTable clientTable = new JTable(clientRowColumnDataValues, clientColumnNames);
	    ListSelectionModel tableRowSelection = clientTable.getSelectionModel();
	    tableRowSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    tableRowSelection.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent e) {
	    		
	    	}
	    });	    
		JScrollPane tableScrollPane = new JScrollPane(clientTable);
		tableScrollPane.setBounds(100, 200, 550, 250);
		contentPane.add(tableScrollPane, BorderLayout.CENTER);
		
		JButton saveButton = new JButton("Generate PDF");
		saveButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		saveButton.setBounds(250, 500, 150, 40);
		saveButton.addActionListener(this);
		contentPane.add(saveButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backButton.setBounds(425, 500, 100, 40);
		backButton.addActionListener(this);
		contentPane.add(backButton);
		
		currentContentPane=contentPane;
		contentPane.repaint();		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Generate PDF")){
			ArrayList invoicesList=service.generateInvoices();
			HashMap invoicesMap=new HashMap();
			if(invoicesList!=null && invoicesList.size()!=0){
				Iterator iterator=invoicesList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					if(invoicesMap.containsKey((String)(tokens[0]+","+tokens[1]))){
						ArrayList list=(ArrayList)invoicesMap.get((String)(tokens[0]+","+tokens[1]));
						list.add(tokens);
						invoicesMap.put((String)((String)(tokens[0]+","+tokens[1])), list);
					}else{
						ArrayList list=new ArrayList();
						list.add(tokens);
						invoicesMap.put((String)((String)(tokens[0]+","+tokens[1])), list);
					}
				}
			}
			if(invoicesMap!=null && invoicesMap.size()!=0){
				for(Object key : invoicesMap.keySet()) {
					String[] tokens=key.toString().split(",");
					String filename=tokens[1];
					String[] client=service.getClient(tokens[0]);
					String[] invoice=service.getInvoice(tokens[0], tokens[1]);
					String projectname=service.getProjectName(tokens[0], tokens[1]);
					ArrayList developer=(ArrayList)invoicesMap.get(key);
					GenerateInvoice generateInvoice = new GenerateInvoice(filename,client,invoice,projectname,developer);
				}
			}			
			JOptionPane.showMessageDialog(null, "Generated Invoices successfully on Local Desktop.");
		}		
		if(e.getActionCommand().equalsIgnoreCase("Back")){
			new Company(currentContentPane,tempuser);
		}
    }

}
