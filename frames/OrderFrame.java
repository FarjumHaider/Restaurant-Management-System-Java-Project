package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;


public class OrderFrame extends JFrame implements ActionListener
{
	
	private JLabel ordIdLabel, ordNameLabel, quantityLabel, totalPriceLabel;
	private JTextField ordIdTF, ordNameTF, quantityTF, totalPriceTF;
	private JButton insertBtn, searchBtn, totalPriceBtn, deleteBtn, refreshBtn, getAllBtn, backBtn, logoutBtn;
	private JTable ordTable;
	private JScrollPane ordTableSP;
	private JPanel panel;
	private Color myColor;
	
	private User user;
	private OrderRepo or; 
	private ProductRepo pr;
	
	public OrderFrame(User user)
	{
		super("OrderFrame");
		this.setSize(1000,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.user = user;
		or=new OrderRepo();
		pr=new ProductRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		myColor = new Color(130,130,255);
		
		
		String data[][] = {{"", "", "",""}};
		
		String head[] = {"OrderID", "Product Name","Quantity", " Total Price"};
		
		ordTable = new JTable(data,head);
		ordTableSP = new JScrollPane(ordTable);
		ordTableSP.setBounds(500, 50, 450, 300);
		ordTable.setEnabled(false);
		panel.add(ordTableSP);
		
		
		
		
		
		
		ordIdLabel = new JLabel("Order ID:");
		ordIdLabel.setBounds(70,100,110,40);
		panel.add(ordIdLabel);
		
		ordIdTF = new JTextField();
		ordIdTF.setBounds(170,100,110,40);
		panel.add(ordIdTF);
		
		ordNameLabel = new JLabel(" Product Name:");
		ordNameLabel.setBounds(70,170,110,40);
		panel.add(ordNameLabel);
		
		ordNameTF = new JTextField();
		ordNameTF.setBounds(170,170,110,40);
		panel.add(ordNameTF);
		
		quantityLabel = new JLabel("Quantity:");
		quantityLabel.setBounds(70,240,110,40);
		panel.add(quantityLabel);
		
		quantityTF = new JTextField();
		quantityTF.setBounds(170,240,110,40);
		panel.add(quantityTF);
		
		
	
		
		totalPriceLabel = new JLabel("Total price:");
		totalPriceLabel.setBounds(70,310,110,40);
		panel.add(totalPriceLabel);
		
		totalPriceTF = new JTextField();
		totalPriceTF.setBounds(170,310,110,40);
		panel.add(totalPriceTF);
		
		
			
		totalPriceBtn=new JButton("Total price");
		totalPriceBtn.setBounds(130,450,100,40);
		totalPriceBtn.addActionListener(this);
		panel.add(totalPriceBtn);
		
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(410,450,100,40);
		searchBtn.addActionListener(this);
		panel.add(searchBtn);
		
		insertBtn = new JButton("Confirm");
		insertBtn.setBounds(270,450,100,40);
		insertBtn.addActionListener(this);
		insertBtn.setEnabled(false);
		panel.add(insertBtn);
		
		
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
		
		logoutBtn=new JButton("LogOut");
		logoutBtn.setBounds(550,515,100,40);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		getAllBtn=new JButton("Get All");
		getAllBtn.setBounds(685,380,100,40);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);
		
		panel.setBackground(myColor);
	
		
		
		this.add(panel);	
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		
		String command = ae.getActionCommand();
		
		if(command.equals(backBtn.getText()))
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
		
		
		else if(command.equals(getAllBtn.getText()))
		{
			String data[][] = or.getAllOrder();
			String head[] = {"OrderID", "Product Name","Quantity", " Total Price"};
			
			panel.remove(ordTableSP);
			
			ordTable = new JTable(data,head);
			ordTable.setEnabled(false);
			ordTableSP = new JScrollPane(ordTable);
			ordTableSP.setBounds(500, 50, 450, 300);
			panel.add(ordTableSP);
			
			panel.revalidate();
			panel.repaint();
		}	
		
		else if(command.equals(deleteBtn.getText()))
		{
			or.deleteOrder(ordIdTF.getText());
			
			
			JOptionPane.showMessageDialog(this,"Deleted");
			
			ordIdTF.setText("");
			ordNameTF.setText("");
			quantityTF.setText("");
			totalPriceTF.setText("");
			
			
			ordIdTF.setEnabled(true);
			ordNameTF.setEnabled(true);
			quantityTF.setEnabled(true);
			totalPriceTF.setEnabled(true);
			
			
			searchBtn.setEnabled(true);
			insertBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
		}
		
		else if(command.equals(searchBtn.getText()))
		{
			if(!ordIdTF.getText().equals("") || !ordIdTF.getText().equals(null))
			{
				Order o = or.searchOrder(ordIdTF.getText());
				
				
				if(o!= null)
				{
					ordIdTF.setText(o.getOrderId());
					ordNameTF.setText(o.getpName());
					quantityTF.setText(o.getQuantity()+"");
					totalPriceTF.setText(o.getTotalPrice()+"");
					
					ordIdTF.setEnabled(false);
					ordNameTF.setEnabled(true);
					quantityTF.setEnabled(true);
					totalPriceTF.setEnabled(true);
					
					
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
			if(!ordIdTF.getText().equals("") || !ordIdTF.getText().equals(null))
			{
				Order o=new Order();
			
			o.setOrderId(ordIdTF.getText());
			o.setPName(ordNameTF.getText());
			o.setQuantity(Integer.parseInt(quantityTF.getText()));
			o.setTotalPrice(Double.parseDouble(totalPriceTF.getText()));
			
			
			
			or.insertOrder(o);
			JOptionPane.showMessageDialog(this, "Inserted successfully");
			
			ordIdTF.setText("");
			ordNameTF.setText("");
			quantityTF.setText("");
			totalPriceTF.setText("");
			
			
			searchBtn.setEnabled(true);
			insertBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Please enter Order ID");
			}
			
		}
		
		
		
		else if(command.equals(refreshBtn.getText()))
		{
			ordIdTF.setText("");
			ordNameTF.setText("");
			quantityTF.setText("");
			totalPriceTF.setText("");
			
			ordIdTF.setEnabled(true);
			totalPriceTF.setEnabled(true);
			
			searchBtn.setEnabled(true);
			insertBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		
		
		else if(command.equals(totalPriceBtn.getText()))
		{
			if(!ordNameTF.getText().equals("") && !quantityTF.getText().equals(null))
			{
				
				//JOptionPane.showMessageDialog(this, "have value");
				
				Product p = pr.priceInfo(ordNameTF.getText());
				if(p!= null)
				{
					
					double x= p.getPrice();
					int a=Integer.parseInt(quantityTF.getText());
					double b=x*a;
					
					totalPriceTF.setText(b+"");
					
									
					deleteBtn.setEnabled(false);
					refreshBtn.setEnabled(false);
					insertBtn.setEnabled(true);
					searchBtn.setEnabled(false);
				}
				/*double x=pr.getPrice(ordNameTF.getText());
				
				int a=Integer.parseInteger(ordQuantityTF.getText());
				
				double b=x*a;
				
				ordTotalPriceTF.setText(b+"");*/
				else
				{
				JOptionPane.showMessageDialog(this, "Please enter Product name or Quantity");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Empty");
			}
			
		}
		
		else{}
		
	}
	
	
}