package repository;

import java.lang.*;
import java.util.ArrayList;
import entity.*;
import interfaces.*;


public class OrderRepo implements IOrderRepo
{
	DatabaseConnection dbc;
	
	public OrderRepo()
	{
		dbc = new DatabaseConnection();
	}


	public void insertOrder(Order o)
	{
		String query = "INSERT INTO orderInfo VALUES ('"+o.getOrderId()+"','"+o.getpName()+"','"+o.getQuantity()+"',"+o.getTotalPrice()+");";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}




		
	public Order searchOrder(String orderId)
		{
			Order or = null;
			String query = "SELECT `productName`, `quantity`, `totalPrice` FROM `orderInfo` WHERE `orderId`='"+orderId+"';";     
			try
			{
			
				dbc.openConnection();
				dbc.result = dbc.st.executeQuery(query);
			
				while(dbc.result.next())
				{
					
					
					String  pName = dbc.result.getString("productName");
					double totalPrice = dbc.result.getDouble("totalPrice");
					int  quantity=dbc.result.getInt("quantity");
					
					or = new Order();
					or.setOrderId(orderId);
					or.setQuantity(quantity);
                    or.setPName(pName);
					or.setTotalPrice(totalPrice);
					
				}
				
			}
			catch(Exception ex){System.out.println(ex.getMessage());}
			dbc.closeConnection();
			return or;
	
		}
	public void deleteOrder(String orderId)
	{
		String query = "DELETE from orderInfo WHERE orderId='"+orderId+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}

	
	public String[][] getAllOrder()
	{
		ArrayList<Order> or = new ArrayList<Order>();
		String query = "SELECT * FROM orderInfo;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String orderId = dbc.result.getString("orderId");
				String pName = dbc.result.getString("productName");
				int  quantity = dbc.result.getInt("quantity");
				double totalPrice = dbc.result.getDouble("totalPrice");
				
				Order o = new Order(orderId,quantity,pName,totalPrice);
				or.add(o);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();
		
		
		Object obj[] = or.toArray();
		String data[][] = new String [or.size()][4];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Order)obj[i]).getOrderId();
			data[i][1] = ((Order)obj[i]).getpName();
			data[i][2] = (((Order)obj[i]).getQuantity())+"";
			data[i][3] = (((Order)obj[i]).getTotalPrice())+"";
		}
		return data;
	}
}	