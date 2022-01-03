package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;


public class EmployeeFrame extends JFrame implements ActionListener
{
	private JLabel empIdLabel, empNameLabel, empDesignationLabel, empSalaryLabel;
	private JTextField empIdTF, empNameTF, empDesignationTF, empSalaryTF;
	private JButton logoutBtn,loadBtn, insertBtn, updateBtn, deleteBtn, refreshBtn, getAllBtn, backBtn;
	private JPanel panel;
	private JTable empTable;
	private JScrollPane empTableSP;
	
	private User user;
	private EmployeeRepo er;
	private UserRepo ur;
	
	
	public EmployeeFrame(User user)
	{
		super("EmployeeFrame");
		this.setSize(1000,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		er = new EmployeeRepo();
		ur = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		
		String data[][] = {{"", "", "", ""}};
		
		String head[] = {"Id", "Name", "Designation", "Salary"};
		
		empTable = new JTable(data,head);
		empTableSP = new JScrollPane(empTable);
		empTableSP.setBounds(500, 50, 450, 300);
		empTable.setEnabled(false);
		panel.add(empTableSP);
		
		empIdLabel = new JLabel("ID :");
		empIdLabel.setBounds(70,100,110,40);
		panel.add(empIdLabel);
		
		empIdTF = new JTextField();
		empIdTF.setBounds(170,100,110,40);
		panel.add(empIdTF);
		
		empNameLabel = new JLabel("Name :");
		empNameLabel.setBounds(70,170,110,40);
		panel.add(empNameLabel);
		
		empNameTF = new JTextField();
		empNameTF.setBounds(170,170,110,40);
		panel.add(empNameTF);
		
		empDesignationLabel = new JLabel("Designation: ");
		empDesignationLabel.setBounds(70,240,110,40);
		panel.add(empDesignationLabel);
		
		empDesignationTF = new JTextField();
		empDesignationTF.setBounds(170,240,110,40);
		panel.add(empDesignationTF);
		
		empSalaryLabel = new JLabel("Salary: ");
		empSalaryLabel.setBounds(70,310,110,40);
		panel.add(empSalaryLabel);
		
		empSalaryTF = new JTextField();
		empSalaryTF.setBounds(170,310,110,40);
		panel.add(empSalaryTF);
		
		loadBtn = new JButton("Load");
		loadBtn.setBounds(130,450,100,40);
		loadBtn.addActionListener(this);
		panel.add(loadBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(270,450,100,40);
		insertBtn.addActionListener(this);
		panel.add(insertBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(410,450,100,40);
		updateBtn.addActionListener(this);
		updateBtn.setEnabled(false);
		panel.add(updateBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(550,450,100,40);
		deleteBtn.addActionListener(this);
		deleteBtn.setEnabled(false);
		panel.add(deleteBtn);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(685,450,100,40);
		refreshBtn.addActionListener(this);
		refreshBtn.setEnabled(false);
		panel.add(refreshBtn);
		
		getAllBtn = new JButton("Get All");
		getAllBtn.setBounds(685,380,100,40);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(685,515,100,40);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		logoutBtn=new JButton("LogOut");
		logoutBtn.setBounds(550,515,100,40);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		panel.setBackground(Color.CYAN);
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(loadBtn.getText()))
		{
			if(!empIdTF.getText().equals("") || !empIdTF.getText().equals(null))
			{
				Employee e = er.searchEmployee(empIdTF.getText());
				if(e!= null)
				{
					empNameTF.setText(e.getName());
					empDesignationTF.setText(e.getDesignation());
					empSalaryTF.setText(e.getSalary()+"");
					
					empIdTF.setEnabled(false);
					empNameTF.setEnabled(true);
					empDesignationTF.setEnabled(true);
					empSalaryTF.setEnabled(true);
					
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
					refreshBtn.setEnabled(true);
					insertBtn.setEnabled(false);
					loadBtn.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild ID");
				}
			}
		}
		else if(command.equals(insertBtn.getText()))
		{
			Employee e = new Employee();
			User u = new User();
			Random rd = new Random();
			int x = rd.nextInt(9999999)+10000000;
			
			e.setEmpId(empIdTF.getText());
			e.setName(empNameTF.getText());
			e.setDesignation(empDesignationTF.getText());
			e.setSalary(Double.parseDouble(empSalaryTF.getText()));
			
			u.setUserId(empIdTF.getText());
			u.setPassword(x+"");
			
			if(((empDesignationTF.getText()).equals("Manager")) || ((empDesignationTF.getText()).equals("manager")))
			{
				u.setStatus(0);
			}
			else
			{
				u.setStatus(1);
			}
			
			er.insertInDB(e);
			ur.insertUser(u);
			
			JOptionPane.showMessageDialog(this, "Inserted, Id: "+empIdTF.getText()+"and Password: "+x);
			
			empIdTF.setText("");
			empNameTF.setText("");
			empDesignationTF.setText("");
			empSalaryTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
		}
		else if(command.equals(refreshBtn.getText()))
		{
			empIdTF.setText("");
			empNameTF.setText("");
			empDesignationTF.setText("");
			empSalaryTF.setText("");
			
			empIdTF.setEnabled(true);
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(updateBtn.getText()))
		{
			Employee e = new Employee();
			
			e.setEmpId(empIdTF.getText());
			e.setName(empNameTF.getText());
			e.setDesignation(empDesignationTF.getText());
			e.setSalary(Double.parseDouble(empSalaryTF.getText()));
			
			er.updateInDB(e);
			
			JOptionPane.showMessageDialog(this, "Updated");
			
			empIdTF.setText("");
			empNameTF.setText("");
			empDesignationTF.setText("");
			empSalaryTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
			empIdTF.setEnabled(true);
			empNameTF.setEnabled(true);
			empDesignationTF.setEnabled(true);
			empSalaryTF.setEnabled(true);
		}
		else if(command.equals(deleteBtn.getText()))
		{
			er.deleteFromDB(empIdTF.getText());
			ur.deleteUser(empIdTF.getText());
			
			JOptionPane.showMessageDialog(this,"Deleted");
			
			empIdTF.setText("");
			empNameTF.setText("");
			empDesignationTF.setText("");
			empSalaryTF.setText("");
			
			empIdTF.setEnabled(true);
			empNameTF.setEnabled(true);
			empDesignationTF.setEnabled(true);
			empSalaryTF.setEnabled(true);
	
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(getAllBtn.getText()))
		{
			String data[][] = er.getAllEmployee();
			String head[] = {"Id", "Name", "Designation", "Salary"};
			
			panel.remove(empTableSP);
			
			empTable = new JTable(data,head);
			empTable.setEnabled(false);
			empTableSP = new JScrollPane(empTable);
			empTableSP.setBounds(500, 50, 450, 300);
			panel.add(empTableSP);
			
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
			LoginFrame lg = new LoginFrame();
			lg.setVisible(true);
			this.setVisible(false);
		}
		
		else{}
		
	}
}