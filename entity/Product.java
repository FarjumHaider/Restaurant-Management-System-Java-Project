package entity;

import java.lang.*;

public class Product
{
	private String productId;
	private double price;
	private String proName;
	
	
	public Product(){}
	public Product(String productId, double price,String proName)
	{
		this.productId = productId;
		this.price = price;
		this.proName  = proName;
	}
	
	public void setProductId(String productId)
	{
		this.productId = productId;
	}
	public void setPrice(double price)
	{
		this.price=price;
	}
	public void setProName(String proName)
	{
		this.proName = proName;
	}
	
	
	public String  getProductId(){return productId;}
	public double getPrice(){return price;}
	public String getProName(){return proName;}
	
}