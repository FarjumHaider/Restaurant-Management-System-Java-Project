package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;

//import entity.Product;
//import repository.ProductRepo;

public class ProductFrame extends JFrame implements ActionListener
{
	 private JLabel proIdLabel, proNameLabel, priceLabel; 
	 private JTextField proIdTF, proNameTF,priceTF;
	 private JButton logoutBtn,searchBtn, insertBtn,updateBtn,deleteBtn,refreshBtn,backBtn,getallBtn;
	 private JTable proTable;
	 private JScrollPane proTableSP;
	 private JPanel panel;
	 
	private User user;
	private ProductRepo pr;
	private UserRepo ur;
	
	private Color myColor;
	
	public ProductFrame(User user)
	{
		super("ProductFrame");
		this.setSize(1000,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		pr=new ProductRepo();
		ur = new UserRepo();
		
		
		panel = new JPanel();
		panel.setLayout(null);
		
		myColor = new Color(130,255,130);
		
		String data[][] = {{"", "", ""}};
		
		String head[] = {"PId", "Name", "Price"};
		
		proTable = new JTable(data,head);
		proTableSP = new JScrollPane(proTable);
		proTableSP.setBounds(500, 50, 450, 300);
		proTable.setEnabled(false);
		panel.add(proTableSP);
		
		
		
		proIdLabel = new JLabel("Product ID:");
		proIdLabel.setBounds(70,100,110,40);
		panel.add(proIdLabel);
		
		proIdTF = new JTextField();
		proIdTF.setBounds(170,100,110,40);
		panel.add(proIdTF);
		
		proNameLabel = new JLabel("Product Name:");
		proNameLabel.setBounds(70,205,110,40);
		panel.add(proNameLabel);
		
		proNameTF = new JTextField();
		proNameTF.setBounds(170,205,110,40);
		panel.add(proNameTF);
		
		
		priceLabel = new JLabel("Price:");
		priceLabel.setBounds(70,310,110,40);
		panel.add(priceLabel);
		
		priceTF = new JTextField();
		priceTF.setBounds(170,310,110,40);
		panel.add(priceTF);
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(130,450,100,40);
		searchBtn.addActionListener(this);
		panel.add(searchBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(270,450,100,40);
		insertBtn.addActionListener(this);
		panel.add(insertBtn);
		
		updateBtn=new JButton("Update");
		updateBtn.setBounds(410,450,100,40);
		updateBtn.addActionListener(this);
		updateBtn.setEnabled(false);
		panel.add(updateBtn);
		
		deleteBtn=new JButton("Delete");
		deleteBtn.setBounds(550,450,100,40);
		deleteBtn.addActionListener(this);
		deleteBtn.setEnabled(false);
		panel.add(deleteBtn);
		
		refreshBtn= new JButton("Refresh");
		refreshBtn.setBounds(685,450,100,40);
		refreshBtn.addActionListener(this);
		refreshBtn.setEnabled(false);
		panel.add(refreshBtn);
		
		backBtn= new JButton("Back");
		backBtn.setBounds(685,515,100,40);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		getallBtn=new JButton("Get All");
		getallBtn.setBounds(685,380,100,40);
		getallBtn.addActionListener(this);
		panel.add(getallBtn);
		
		logoutBtn=new JButton("LogOut");
		logoutBtn.setBounds(550,515,100,40);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		panel.setBackground(myColor);
		
		this.add(panel);	
	}
	
	
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(searchBtn.getText()))
		{
			if(!proIdTF.getText().equals("") || !proIdTF.getText().equals(null))
			{
				Product p = pr.searchProduct(proIdTF.getText());
				
				
				if(p!= null)
				{
					proIdTF.setText(p.getProductId());
					proNameTF.setText(p.getProName());
					priceTF.setText(p.getPrice()+"");
					
					proIdTF.setEnabled(false);
					proNameTF.setEnabled(true);
					priceTF.setEnabled(true);
					
					
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
					refreshBtn.setEnabled(true);
					insertBtn.setEnabled(false);
					searchBtn.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild ID");
				}
			}
		}
		
		else if(command.equals(insertBtn.getText()))
		{
			
			Product p=new Product();
			
			p.setProductId(proIdTF.getText());
			p.setProName(proNameTF.getText());
			p.setPrice(Double.parseDouble(priceTF.getText()));
			
			pr.insertProduct(p);
			JOptionPane.showMessageDialog(this, "Inserted successfully");
			
			proIdTF.setText("");
			proNameTF.setText("");
			priceTF.setText("");
			
			searchBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
			
		}
		
		else if(command.equals(refreshBtn.getText()))
		{
			proIdTF.setText("");
			proNameTF.setText("");
			priceTF.setText("");
			
			proIdTF.setEnabled(true);
			
			searchBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		
		else if(command.equals(updateBtn.getText()))
		{
			Product p=new Product();
			
			p.setProductId(proIdTF.getText());
			p.setProName(proNameTF.getText());
			p.setPrice(Double.parseDouble(priceTF.getText()));
			
			pr.updateProduct(p);
			
			JOptionPane.showMessageDialog(this, "Updated");
			
			proIdTF.setText("");
			proNameTF.setText("");
			priceTF.setText("");
			
			searchBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
			proIdTF.setEnabled(true);
			proNameTF.setEnabled(true);
			priceTF.setEnabled(true);
						
		}
		
		
		else if(command.equals(deleteBtn.getText()))
		{
			pr.deleteProduct(proIdTF.getText());
			
			
			JOptionPane.showMessageDialog(this,"Deleted");
			
			proIdTF.setText("");
			proNameTF.setText("");
			priceTF.setText("");
			
			proIdTF.setEnabled(true);
			proNameTF.setEnabled(true);
			priceTF.setEnabled(true);
	
			searchBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		
		
		else if(command.equals(getallBtn.getText()))
		{
			String data[][] = pr.getAllProduct();
			String head[] = {"PId", "Name", "Price"};
			
			panel.remove(proTableSP);
			
			proTable = new JTable(data,head);
			proTable.setEnabled(false);
			proTableSP = new JScrollPane(proTable);
			proTableSP.setBounds(500, 50, 450, 300);
			panel.add(proTableSP);
			
			panel.revalidate();
			panel.repaint();
			
		}
		
		else if(command.equals(backBtn.getText()))
		{
			EmployeeHome eh = new EmployeeHome(user);
			eh.setVisible(true);
			this.setVisible(false);
		}
		
		else if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lg=new LoginFrame();
			lg.setVisible(true);
			this.setVisible(false);
		}

		else{}
	}
	
	
	
	
}