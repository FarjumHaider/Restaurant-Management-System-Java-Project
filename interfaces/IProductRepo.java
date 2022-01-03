package interfaces;

import java.lang.*;

import entity.*;
//entity.Product;

public interface IProductRepo
{
	public void insertProduct(Product p);
	public void deleteProduct(String productId);
	public void updateProduct(Product p);
	public Product searchProduct(String productId);
	public Product priceInfo(String proName);
	public String[][] getAllProduct();
}