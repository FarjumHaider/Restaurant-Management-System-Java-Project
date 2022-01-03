package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

import entity.*;
import repository.*;

public class LoginFrame extends JFrame implements ActionListener, MouseListener
{
	JLabel title, userLabel, passLabel,welcmLabel,imgLabel;
	JTextField userTF;
	JPasswordField passPF;
	JButton loginBtn, exitBtn,  showPassBtn;
	JPanel panel;
	Font  fnt;
	Color clr,clrOrange;
	ImageIcon img;
	
	
	public LoginFrame()
	{
		super("Restaurants Management System - Login Window");
		
		this.setSize(1000,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		fnt =new Font("Cambria",Font.ITALIC | Font.BOLD,20);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		
		
		clr=new Color(140,240,190);
		clrOrange=new Color(255,160,65);
		
		userLabel = new JLabel("User ID : ");
		userLabel.setBounds(50,200,200,50);
		panel.add(userLabel);
		
		passLabel = new JLabel("Password : ");
		passLabel.setBounds(50,280,200,50);
		panel.add(passLabel);
		
		
		welcmLabel = new JLabel("WELCOME",JLabel.CENTER);
		welcmLabel.setBounds(0,0,1000,60);
		welcmLabel.setOpaque(true); 
		welcmLabel.setBackground(clrOrange);
		welcmLabel.setForeground(Color.WHITE);
		welcmLabel.setFont(fnt);
		panel.add(welcmLabel);
		
		
		userTF = new JTextField();
		userTF.setBounds(150,200,200,50);
		userTF.setBackground(clrOrange);
		panel.add(userTF);
		
		
		
		
		passPF = new JPasswordField();
		passPF.setBounds(150,280,200,50);
		passPF.setEchoChar('*');
		passPF.setBackground(clrOrange);
		panel.add(passPF);
		
		loginBtn = new JButton("Login");
		loginBtn.setBounds(150,360,70,50);
		loginBtn.setBackground(clrOrange);
		loginBtn.addActionListener(this);
		panel.add(loginBtn);
		
		exitBtn = new JButton("Exit");
		exitBtn.setBounds(280,360,70,50);
        exitBtn.setBackground(clrOrange);
		exitBtn.addActionListener(this);
		panel.add(exitBtn);
		
		showPassBtn = new JButton("Show");
		showPassBtn.setBounds(355,280,70,50);
        showPassBtn.setBackground(clrOrange);
		showPassBtn.addMouseListener(this);
		panel.add(showPassBtn);
		
		
		img = new ImageIcon("aaa3.png");
		imgLabel = new JLabel(img);
		imgLabel.setBounds(500,100,400,350);
		panel.add(imgLabel);
		
		
		
		
		
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(loginBtn.getText()))
		{
			UserRepo ur = new UserRepo();
			User user = ur.getUser(userTF.getText(), passPF.getText());
			
			if(user != null)
			{
				if(user.getStatus() == 0 || user.getStatus() == 1)
				{
					EmployeeHome eh = new EmployeeHome(user);
					eh.setVisible(true);
					this.setVisible(false);
				}
				else if(user.getStatus() == 2)
				{
					
				}
				else{}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Invaild Id or Password");
			}
			
		}
		else if(command.equals(exitBtn.getText()))
		{
			System.exit(0);
		}
		
		else{}
	}
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me)
	{
		passPF.setEchoChar((char)0);
	}
	public void mouseReleased(MouseEvent me)
	{
		passPF.setEchoChar('*');
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
}



















