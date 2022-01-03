package entity;

import java.lang.*;

public class Order
{
	private String orderId;
	private int  quantity;
	private String pName;
	private double totalPrice;
	
	
	public Order(){}
	public Order(String orderId,int quantity,String pName, double totalPrice)
	{
		this.orderId =orderId;
		this.quantity = quantity;
		this.pName = pName;
		this.totalPrice=totalPrice;
	}
	
	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}
	public void setQuantity(int quantity)
	{
		this.quantity=quantity;
	}
	public void setPName(String pName)
	{
		this.pName = pName;
	}
	
	public void setTotalPrice(double totalPrice)
	{
		this.totalPrice=totalPrice;
	}
	
	
	public String getOrderId(){return orderId;}
	public int  getQuantity(){return quantity;}
	public String getpName(){return pName;}
	public double getTotalPrice(){return totalPrice;}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
