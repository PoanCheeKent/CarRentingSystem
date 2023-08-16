package Car_Renting_System;

public class Transport {
	
	private static int tid = 1; //every car registered have unique transportId
	private int transportId;
	private String model;
	private String carPlate;
	private double rate;
	private boolean availability; //true means car is available
	private String segment;
	private String category;
	
	public Transport(String model, String carPlate, double rate, String segment, String category) {
		
		this.transportId = tid;
		this.model = model;
		this.carPlate = carPlate;
		this.rate = rate;
		availability = true;
		this.segment = segment;
		this.category = category;
		
		tid++; //increment by 1 each time a new car is registered
	}
	
	public int getTransportId() {
		
		return transportId;
	}
	
	public String getModel() {
		
		return model;
	}
	
	public String getCarPlate() {
		
		return carPlate;
	}
	
	public double getRate() {
		
		return rate;
	}
	
	public boolean getAvailability() {
		
		return availability;
	}
	
	public void setAvailability(boolean availability) {
		
		this.availability = availability;
	}
	
	public String getSegment() {
		
		return segment;
	}
	
	public String getCategory() {
		
		return category;
	}
}
