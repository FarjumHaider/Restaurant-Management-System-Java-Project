package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;


public class EmployeeHome extends JFrame implements ActionListener
{
	JButton logoutBtn, manageEmpBtn, manageProductBtn,manageOrderBtn, changePasswordBtn;
	JPanel panel;
	ImageIcon img;
	JLabel imgLabel;
	
	User user;
	
	public EmployeeHome(User user)
	{
		super("Welcome Employee");
		this.setSize(1010,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		panel = new JPanel();
		panel.setLayout(null);
		
		manageEmpBtn = new JButton("Manage Employee");
		manageEmpBtn.setBounds(0,40, 200, 80);
		manageEmpBtn.addActionListener(this);
		panel.add(manageEmpBtn);
		
		manageProductBtn = new JButton("Manage Product");
		manageProductBtn.setBounds(200,40, 200, 80);
		manageProductBtn.addActionListener(this);
		panel.add(manageProductBtn);
		
		manageOrderBtn = new JButton("Manage Order");
		manageOrderBtn.setBounds(400,40, 200, 80);
		manageOrderBtn.addActionListener(this);
		panel.add(manageOrderBtn);
		
		changePasswordBtn = new JButton("Change Password");
		changePasswordBtn.setBounds(600,40, 200, 80);
		changePasswordBtn.addActionListener(this);
		panel.add(changePasswordBtn);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(800,40, 200, 80);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		
		img = new ImageIcon("home3.png");
		imgLabel = new JLabel(img);
		imgLabel.setBounds(0,0,1020,600);
		panel.add(imgLabel);
		
		
		
		
		
		
		
		this.add(panel);
	
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(manageEmpBtn.getText()))
		{
			if(user.getStatus()==0)
			{
				EmployeeFrame ef = new EmployeeFrame(user);
				ef.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Access Denied");
			}
		}
		else if(command.equals(manageProductBtn.getText()))
		{
			if(user.getStatus()==0 || user.getStatus()==1)	
			{
				ProductFrame pf=new ProductFrame(user);
				pf.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Access Denied");
			}
		}
		else if(command.equals(manageOrderBtn.getText()))
		{
			if(user.getStatus()==0 || user.getStatus()==1)
			{
				OrderFrame or=new OrderFrame(user);
				or.setVisible(true);
				this.setVisible(false);  
			}	
			else
			{
				JOptionPane.showMessageDialog(this, "Access Denied");
			}	
			    
		}
		
		else if(command.equals(changePasswordBtn.getText()))
		{
			if(user.getStatus()==0 || user.getStatus()==1)
			{
				ChangePassFrame cp=new ChangePassFrame(user);
				cp.setVisible(true);
				this.setVisible(false);  
			}	
			else
			{
				JOptionPane.showMessageDialog(this, "Access Denied");
			}	
			    
		}
		else{}
	}
}































