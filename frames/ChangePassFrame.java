package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;



public class ChangePassFrame extends JFrame implements ActionListener
{
	
	JButton logoutBtn,backbtn,conPassBtn;
	JTextField userTF,oldPasswordTF,newPasswordTF,conPasswordTF;
	JLabel newPassLabel,conPassLabel,oldPassLabel,userIdLabel,imgLabel;
	JPanel panel;
	ImageIcon img;
	
	Font  fnt;
	
	private User user;
	private UserRepo ur;
	
	public ChangePassFrame(User user)
	{
		
		super("Change password");
		this.setSize(1000,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.user = user;
		
		fnt =new Font("Cambria",Font.ITALIC | Font.BOLD,20);
		
		ur = new UserRepo();
		
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		//clr=new Color(140,240,190);
		
		userIdLabel=new JLabel("User Id");
		userIdLabel.setBounds(280,130,100,50);
		panel.add(userIdLabel);
		
		userTF=new JTextField();
		userTF.setBounds(450,130,200,50);
		//userTF.setBackground(clrOrange);
		panel.add(userTF);
		
		
		
		oldPassLabel=new JLabel("Old Password");
		oldPassLabel.setBounds(280,210,100,50);
		panel.add(oldPassLabel);
		
		
		oldPasswordTF=new JTextField();
		oldPasswordTF.setBounds(450,210,200,50);
		//passwordTF.setBackground(clrOrange);
		panel.add(oldPasswordTF);
		
		
		
		
		
		
		
		newPassLabel=new JLabel("New Password");
		newPassLabel.setBounds(280,280,100,50);
		panel.add(newPassLabel);
		
		newPasswordTF=new JTextField();
		newPasswordTF.setBounds(450,280,200,50);
		//passwordTF.setBackground(clrOrange);
		panel.add(newPasswordTF);
		
	
		
		conPassLabel=new JLabel("Confirm Password");
		conPassLabel.setBounds(280,350,120,50);
		panel.add(conPassLabel);
		
		conPasswordTF=new JTextField();
		conPasswordTF.setBounds(450,350,200,50);
		//passwordTF.setBackground(clrOrange);
		panel.add(conPasswordTF);
		
		
		conPassBtn=new JButton("Confirm");
		conPassBtn.setBounds(420,450,100,50);
		conPassBtn.addActionListener(this);
		panel.add(conPassBtn);
		
		backbtn=new JButton("Back");
		backbtn.setBounds(580,450,70,50);
		backbtn.addActionListener(this);
		panel.add(backbtn);
		
		logoutBtn=new JButton("Logout");
		logoutBtn.setBounds(800,50,120,50);
		
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		
		img = new ImageIcon("logout2.jpg");
		imgLabel = new JLabel(img);
		imgLabel.setBounds(0,0,1000,600);
		panel.add(imgLabel);
		
	
	
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
	    if(command.equals(backbtn.getText()))
		{
			EmployeeHome eh = new EmployeeHome(user);
			eh.setVisible(true);
			this.setVisible(false);
		}
		
		else if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lg = new LoginFrame();
			lg.setVisible(true);
			this.setVisible(false);
		}
		
		else if(command.equals(conPassBtn.getText())){
		
		if(newPasswordTF.getText().equals(conPasswordTF.getText()))
		{
			user.setUserId(userTF.getText());
			user.setPassword(newPasswordTF.getText());

			ur.updateUser(user);
			
			userTF.setText("");
			oldPasswordTF.setText("");
			newPasswordTF.setText("");
			conPasswordTF.setText("");
			
			JOptionPane.showMessageDialog(this,"Password changed");
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Again enter password");
			userTF.setText("");
			oldPasswordTF.setText("");
			newPasswordTF.setText("");
			conPasswordTF.setText("");
		}
		
		}
		
	}
}	