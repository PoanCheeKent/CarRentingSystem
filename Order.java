package Car_Renting_System;

public class Order {
	
	private static int oid = 1; //every order has its unique id
	private int orderId;
	private Transport transport;
	private double total;
	private int duration;
	private String startTime;
	private String status; //in rental or returned
	
	public Order(Transport transport, double total, int duration, String startTime, String status) {
		
		this.orderId = oid;
		this.transport = transport;
		this.total = total;
		this.duration = duration;
		this.startTime = startTime;
		this.status = status;
		
		oid++; //increment by 1 each time an order is created
	}
	
	public int getOrderId() {
		
		return orderId;
	}
	
	public Transport getTransport() {
		
		return transport;
	}
	
	public double getTotal() {
		
		return total;
	}
	
	public int getDuration() {
		
		return duration;
	}
	
	public String getStartTime() {
		
		return startTime;
	}	
	
	public String getStatus() {
		
		return status;
	}
	
	public void setStatus(String status) {
		
		this.status = status;
	}
	
	public void setDuration (int duration) {
		this.duration = duration;
	}
	
	public void setRate (double total) {
		this.total = total;
	}
	
}