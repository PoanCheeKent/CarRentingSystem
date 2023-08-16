package AppUser;

import java.util.ArrayList;

import Car_Renting_System.Order;

public class CarRenter extends User{
	
	private ArrayList<Order> orders = new ArrayList<Order>();
	
	public CarRenter(String name, String password, String userId) {
		super(name, password, userId);
	}
	
	public ArrayList<Order> getOrders(){
		return orders;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void cancelOrder(Order order) {
		orders.remove(order);
	}

}
