package interfaces;

import java.lang.*;

import entity.*;
//entity.Order;

public interface IOrderRepo
{
	public void insertOrder(Order o);
	public void deleteOrder(String orderId);
	
	public Order searchOrder(String orderId);
	public String[][] getAllOrder();
}