package AppUser;

import java.util.ArrayList;

import Car_Renting_System.Order;
import Car_Renting_System.Transport;

public class CarOwner extends User{
	
	private ArrayList<Transport> transportList = new ArrayList<Transport>();
	private ArrayList<Order> orders = new ArrayList<Order>();
	
	public CarOwner(String name, String password, String userId) {
		super(name, password, userId);
	}
	
	public ArrayList<Transport> getTransportList(){
		
		return transportList;
	}
	
	public void addTransport(Transport car) {
		transportList.add(car);
	}
	
	public void removeTransport(Transport car) {
		transportList.remove(car);
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
