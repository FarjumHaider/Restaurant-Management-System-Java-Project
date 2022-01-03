package repository;

import java.lang.*;
import java.util.ArrayList;
import entity.*;
import interfaces.*;

public class ProductRepo implements IProductRepo
{
	DatabaseConnection dbc;
	
	public ProductRepo()
	{
		dbc = new DatabaseConnection();
	
	}


    public void updateProduct(Product p)
	{
		String query = "UPDATE product SET productName='"+p.getProName()+"', price = "+p.getPrice()+" WHERE productId='"+p.getProductId()+"'";
		
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}


	
	public void deleteProduct(String productId)
	{
		String query = "DELETE from product WHERE productId ='"+productId+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}

	
	



	public void insertProduct(Product p)
	{
		String query = "INSERT INTO product VALUES ('"+p.getProductId()+"','"+p.getProName()+"',"+p.getPrice()+");";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}




    public Product searchProduct(String productId)
	{
		Product pd = null;
		String query = "SELECT `productName`, `price` FROM `product` WHERE `productId`='"+productId+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String proName = dbc.result.getString("productName");
				double price = dbc.result.getDouble("price");
				
				
				pd = new Product();
				pd.setProductId(productId);
				pd.setPrice(price);
				pd.setProName(proName);
				
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return pd;
	}
	
	
	public String[][] getAllProduct()
	{
		ArrayList<Product> pr = new ArrayList<Product>();
		String query = "SELECT * FROM product;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String productId = dbc.result.getString("productId");
				String proName = dbc.result.getString("productName");
				double price = dbc.result.getDouble("price");
				
				Product p = new Product(productId,price,proName);
			    pr.add(p);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();
		
		
		Object obj[] = pr.toArray();
		String data[][] = new String [pr.size()][3];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Product)obj[i]).getProductId();
			data[i][1] = ((Product)obj[i]).getProName();
			data[i][2] = (((Product)obj[i]).getPrice())+"";
		}
		return data;
	}
	
	
	public Product priceInfo(String proName)
	{
		Product pr = null;
		String query = "SELECT `productId`,`price` FROM `product` WHERE `productName`='"+proName+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String productId = dbc.result.getString("productId");
				double price = dbc.result.getDouble("price");
				
				
				pr = new Product();
				pr.setProductId(productId);
				pr.setPrice(price);
				pr.setProName(proName);
				
				//error
				
				
				
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return pr;
	}
	
	
	
	
}