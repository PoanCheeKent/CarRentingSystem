package Car_Renting_System;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import AppUser.CarOwner;
import AppUser.CarRenter;
import AppUser.User;

public class Application {

	public static void main(String[] args) throws IOException{

		Scanner input = new Scanner(System.in);
		ArrayList<User> users = new ArrayList<>();
		int renteroption;
		int owneroption = -1;
		boolean loginflag = false;
		int choice;
		
		do {
			choice = displayMenu(); //whether user wants to login or register account
			
			switch(choice) {
			
			case 1:  //login option
				int index = userLogin(users);  //check whether user is registered or not
				
				if (index == -1) {
					break;
				}
				
				if(users.get(index) instanceof CarRenter) {
					
					User currentUser = users.get(index);
					
					do {
						renteroption = displayCarRenterMenu(); //prompt car renter to specific function based on his option
						
						if(renteroption == 0) {
						
							System.out.println("** Logged out **");
						}
					
						else if(renteroption == 1) {
						
							viewAvailableCar(users); //display available cars for rent
						}
						
						else if (renteroption == 2) {
							
							bookCar(currentUser, users); //place order for car he wants to rent
						}
						
						else if(renteroption == 3) {
							
							viewRenterOrder(currentUser);  //view past and current orders
						}
						
						else if(renteroption == 4) {
							
							returnCar(currentUser, users);  //return a previously rented car
						}
						
						else if(renteroption == 5) {
							
							renterCancelOrder(currentUser, users);  //return a previously rented car
						}
						else if (renteroption == 6) {
							modifyDuration(currentUser,users);
						}
						
						else if (renteroption == 7) {
							searchCar(currentUser,users);
						}
						
						else
							
							System.out.println("** Please enter a valid option!! **");
						
					} while(renteroption != 0);
					
					break;
					
				}
				
				else if(users.get(index) instanceof CarOwner) {
					
					do {	
						owneroption = displayCarOwnerMenu(); //prompt car owner to specific function based on his option
						
						if(owneroption == 0) {
							
							System.out.println("** Logged out **");
						}
						
						else if(owneroption == 1) {
							
							addCarToRent((CarOwner)users.get(index)); //register and add a car to be rented to the renting list
						}
						
						else if (owneroption == 2) {
							
							removeCarFromRent((CarOwner)users.get(index));  //remove a registered car from the renting list
						}
						
						else if(owneroption == 3) {
							
							viewOwnerOrder((CarOwner)users.get(index));  //view past and current orders
						}
					
						else if(owneroption == 4) {
							viewTransportList((CarOwner)users.get(index));
						}
					
						else
						
							System.out.println("** Please enter a valid option!! **");
					
					} while (owneroption != 0);
				
				break;
			}
					
			case 2: //register account option
				registerUser(users);
				break;
				
			default:
				System.out.println("** Please enter a valid option!! **");
				break;
			}
		} while(choice != 0);
	}

	public static int displayMenu() {
		
		Scanner scanner = new Scanner(System.in);
		
		int choice;
		
		do {
			System.out.println("\n			Car Renting System");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("-----------------------------------------------------------------");
			System.out.print("Choice : ");
			choice = scanner.nextInt();
			
			if (choice != 1 && choice != 2) {
				
				System.out.println("** Invalid Choice, please try again !! **");
			}
			
		} while (choice != 1 && choice != 2);

		return choice;
	}
	
	public static void registerUser(ArrayList<User> users) throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		String type;

		do {
			System.out.println("\n			Register User ");
			System.out.println("\n\tPlease enter your details to register your account");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Account type ");
			System.out.println("1. Car Renter ");
			System.out.println("2. Car Owner");
			System.out.print("Enter your account type : ");
			type = scanner.nextLine();
	
	
			if(!type.equals("1") && !type.equals("2")) {
			
				System.out.println("** Please enter a valid option!! **");
			}
		
		} while (!type.equals("1") && !type.equals("2"));
		
		
		System.out.print("Name : ");
		String name = scanner.nextLine();
		System.out.print("Password : ");
		String password = scanner.nextLine();
		System.out.print("ID : ");
		String id = scanner.nextLine();
		
		for(int i=0; i<users.size();i++) {
			while(id.equals(users.get(i).getUserId())){
				System.out.println("ID registered, please use another ID to register");
				System.out.print("ID : ");
				id = scanner.nextLine();
			}
		}
		
		if(Integer.parseInt(type) == 1) {
			
			users.add(new CarRenter(name, password, id));
		}
		else if(Integer.parseInt(type) == 2) {
			
			users.add(new CarOwner(name, password, id));
		}
		
		System.out.println("** Account registered! **");
	}
	
	public static int userLogin(ArrayList<User> users) {
		
		Scanner input = new Scanner(System.in);
		int index = -1;
		
		
			System.out.println("\n			  User Login ");
			System.out.println("\n\t\tPlease enter your details to login");
			System.out.println("-----------------------------------------------------------------");
			System.out.print("Name: ");
			String name = input.nextLine();
			System.out.print("Password: ");
			String pw = input.nextLine();
			
			for(int i = 0; i < users.size(); i++) {
				if(name.equals(users.get(i).getName()) && pw.equals(users.get(i).getPassword())) {
					
					System.out.println("** Successful login **");
					
					index = i; 
				}
			}
		
			if(index == -1) {
				
				System.out.println("** User not registered! **");
	
			}
		
		
		return index;
	}
	
	public static int displayCarRenterMenu() {
		
		Scanner scanner = new Scanner(System.in);
		int option;
		
		do {
			System.out.println("\n			Car Renter Menu");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("1. View Car Available For Renting");
			System.out.println("2. Book A Car");
			System.out.println("3. View Orders");
			System.out.println("4. Return A Car");
			System.out.println("5. Cancel A Order");
			System.out.println("6. Change Booking Duration");
			System.out.println("7. Search Car");
			System.out.println("0. Log out");
			System.out.println("-----------------------------------------------------------------");
			System.out.print("Choice : ");
			option = scanner.nextInt();
			
			if (option < 0 || option > 7) {
				
				System.out.println("** Invalid option, please try again !! **");
			}
		} while (option < 0 || option > 7);
		
		return option;
	}
	
	public static int displayCarOwnerMenu() {
		
		Scanner scanner = new Scanner(System.in);
		int option;
		
		do {
			System.out.println("\n			Car Owner Menu");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("1. Add A Car To Renting List");
			System.out.println("2. Remove A Car From Renting List");
			System.out.println("3. View Orders");
			System.out.println("4. View Transport List");
			System.out.println("0. Log out");
			System.out.println("-----------------------------------------------------------------");
			System.out.print("Choice : ");
			option = scanner.nextInt();
			
			if (option < 0 || option > 4) {
				
				System.out.println("** Invalid option, please try again !! **");
			}
		} while (option < 0 || option > 4);
		
		return option;
	}
	
	/*Car renter function*/
	public static void viewAvailableCar(ArrayList<User> users) {
		
		ArrayList<Transport> alltransports = new ArrayList<Transport>();
		
		for(int i = 0; i < users.size(); i++) {
		
			if(users.get(i) instanceof CarOwner) {
				
				ArrayList<Transport> transports = new ArrayList<Transport>();
				
				transports = ((CarOwner) users.get(i)).getTransportList();
				
				for(int j = 0; j < transports.size(); j++) {
						
					alltransports.add(transports.get(j));
				}
			}
		}
		
		if (alltransports.size() == 0) {
			System.out.println("Sorry ! Temperory no car available. ");
		}
		
		else {
			System.out.println("\t\tAvailable Car For Rent");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("TransportID\tModel\tCarPlate\tRate/h\tAvailable\tSegment\tCategory");
			
			for(int i = 0; i < alltransports.size(); i++) {
				
				if(alltransports.get(i).getAvailability() == true){
					
					System.out.println(alltransports.get(i).getTransportId() + "\t\t" + alltransports.get(i).getModel() +
									   "\t" + alltransports.get(i).getCarPlate() + "\t\t" + alltransports.get(i).getRate() +
									   "\t" + alltransports.get(i).getAvailability() + "\t\t" + alltransports.get(i).getSegment() +
									   "\t" + alltransports.get(i).getCategory());
				}
			}
		}
		
			
	}
	
	public static void bookCar(User currentUser, ArrayList<User> users) {
		
		LocalTime currentTime = LocalTime.now();
		String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter TransportID of the car you wish to rent: ");
		int carrentid = Integer.parseInt(input.nextLine());
		
		int duration =0;
		
		for(int i = 0; i < users.size() ; i++) {

			if(users.get(i) instanceof CarOwner) {
				
				int errorHandler = 0;
				
				for(int j = 0; j < ((CarOwner)users.get(i)).getTransportList().size(); j++){
				
					if(carrentid == ((CarOwner)users.get(i)).getTransportList().get(j).getTransportId() && ((CarOwner)users.get(i)).getTransportList().get(j).getAvailability() == true) {
						
						((CarOwner)users.get(i)).getTransportList().get(j).setAvailability(false); // car cannot to be rented for the moment
						
						System.out.print("Enter the duration of renting (in hours): ");
						duration = Integer.parseInt(input.nextLine());
						
						double price = duration * ((CarOwner)users.get(i)).getTransportList().get(j).getRate();
						
						Order order = new Order(((CarOwner)users.get(i)).getTransportList().get(j), price, duration,
								formattedTime, "In rental");
						
						((CarRenter)currentUser).addOrder(order);  //add new order to car renter's order list 
						
						((CarOwner)users.get(i)).addOrder(order); //add new order to car owner's order list
						
						System.out.println("** Car booked successfully! **");
						
						j =  ((CarOwner)users.get(i)).getTransportList().size();
						
						errorHandler +=1;
					}
					
				}if (errorHandler == 0) {
					System.out.println("** TransportID not found or car not available for rent! **");
				}	
			}
		}
	}
	
	public static void viewRenterOrder(User currentUser) {
		
		ArrayList<Order> orders = ((CarRenter)currentUser).getOrders();
		
		System.out.println("\t\t\t\t\tYour Orders");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("OrderID\tModel\tCarPlate\tRate\tStartTime\tDuration\tTotal\tStatus");
		
		if (orders.size() == 0) {
			System.out.println("There are no order yet ! ");
		}
		
		else {
		
			for(int i = 0; i < orders.size(); i++) {
				
				System.out.println(orders.get(i).getOrderId() + "\t" + orders.get(i).getTransport().getModel() + 
								   "\t" + orders.get(i).getTransport().getCarPlate() + "\t\t" + orders.get(i).getTransport().getRate() +
								   "\t" + orders.get(i).getStartTime() + "\t" + orders.get(i).getDuration() + "\t" + 
								   "\t" + orders.get(i).getTotal() + "\t" + orders.get(i).getStatus());
			}
		}
	}
	
	public static void returnCar(User currentUser, ArrayList<User> users) {
		
		Scanner input = new Scanner(System.in);
		int errorHandle = 0;
		
		if(((CarRenter)currentUser).getOrders().isEmpty()) {
			System.out.println("No car to be returned! ");
		}
		else {
			System.out.print("Enter OrderID of the car you would like to return: ");
			int carreturnid = Integer.parseInt(input.nextLine());
			
			for(int i = 0; i < ((CarRenter)currentUser).getOrders().size(); i++) {
				
				if(carreturnid == ((CarRenter)currentUser).getOrders().get(i).getOrderId()){
					
					((CarRenter)currentUser).getOrders().get(i).getTransport().setAvailability(true);  //car can be rented again
					((CarRenter)currentUser).getOrders().get(i).setStatus("Returned");
					
					System.out.println("** Car returned successfully! **");
					
					errorHandle += 1;
				}
			}
			
			for(int j = 0; j < users.size(); j++) {
				
				if(users.get(j) instanceof CarOwner) {
					
					for(int k = 0; k < ((CarOwner)users.get(j)).getOrders().size(); k++) {
						
						if(carreturnid == ((CarOwner)users.get(j)).getOrders().get(k).getOrderId()) {
							
							((CarOwner)users.get(j)).getOrders().get(k).setStatus("Returned");
						}
					}
				}
			}
			if(errorHandle == 0) {
				System.out.println("** OrderID not found! **");
			}
		}
	}
	
	public static void renterCancelOrder(User currentUser, ArrayList<User> users) {
		
		Scanner input = new Scanner(System.in);
		int errorHandle = 0;
		
		if(((CarRenter)currentUser).getOrders().isEmpty()) {
			System.out.println("No order to be cancelled! ");
		}
		
		else System.out.print("Enter OrderID of the car you would like to cancel: ");
		int carreturnid = Integer.parseInt(input.nextLine());
		
		for(int i = 0; i < ((CarRenter)currentUser).getOrders().size(); i++) {
			
			if(carreturnid == ((CarRenter)currentUser).getOrders().get(i).getOrderId()){
				
				((CarRenter)currentUser).getOrders().get(i).getTransport().setAvailability(true);  //car can be rented again
				((CarRenter)currentUser).getOrders().get(i).setStatus("Cancelled");
				
				System.out.println("** Order cancelled successfully! **");
				
				errorHandle += 1;
			}
		}
		
		for(int j = 0; j < users.size(); j++) {
			
			if(users.get(j) instanceof CarOwner) {
				
				for(int k = 0; k < ((CarOwner)users.get(j)).getOrders().size(); k++) {
					
					if(carreturnid == ((CarOwner)users.get(j)).getOrders().get(k).getOrderId()) {
						
						((CarOwner)users.get(j)).getOrders().get(k).setStatus("Cancelled");
					}
				}
			}
		}
		if(errorHandle == 0) {
			System.out.println("** OrderID not found! **");
			}
			
		
	}
	
	private static void modifyDuration(User currentUser, ArrayList<User> users) {
		int errorHandle =0;
		if(((CarRenter)currentUser).getOrders().isEmpty()) {
			System.out.println("No order to be modify ! ");
		}
		else {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter OrderID of the car you would like to modify : ");
			int id = Integer.parseInt(input.nextLine());
			System.out.println("Renting hours : ");
			int duration = Integer.parseInt(input.nextLine());
			for(int i = 0; i < users.size() ; i++) {
				
				if(users.get(i) instanceof CarRenter) {
					
					for(int j = 0; j < ((CarRenter)users.get(i)).getOrders().size(); j++){
					
						if(id == ((CarRenter)users.get(i)).getOrders().get(j).getOrderId()) {
							
							Order order=((CarRenter)users.get(i)).getOrders().get(j);
							
							((CarRenter)users.get(i)).getOrders().get(j).setDuration(duration); // car cannot to be rented for the moment
							
							double rate = ((CarRenter)users.get(i)).getOrders().get(j).getTransport().getRate();
							
							((CarRenter)users.get(i)).getOrders().get(j).setRate(duration*rate);
							
							System.out.println("** Order modified successfully! **");
							
							errorHandle += 1;
						}
					}	
				}
			}
			if (errorHandle == 0) {
				System.out.println("** Order ID not found ! **");
			}
		}
	}
	
	private static void searchCar(User currentUser, ArrayList<User> users) {
		Scanner input = new Scanner(System.in);
		int choice;
		String category="";
		
		do {
			System.out.println("Enter category of car to be search");
			System.out.println("1. Sedan");
			System.out.println("2. SUV");
			System.out.println("3. MPV");
			System.out.println("Choice: ");
			choice = Integer.parseInt(input.nextLine());
			
			
			if (choice < 0 || choice > 3) {
				
				System.out.println("** Invalid option, please try again !! **");
			}
			else if(choice==1) {
				category="Sedan";
			}
			else if(choice==2) {
				category="SUV";
			}
			else if(choice==3) {
				category="MPV";
			}
		} while (choice < 0 || choice > 3);
		
		
		ArrayList<Transport> searchResult = new ArrayList<Transport>();
		for(int i = 0; i < users.size() ; i++) {
			if(users.get(i) instanceof CarOwner) {
				
				for(int j = 0; j < ((CarOwner)users.get(i)).getTransportList().size(); j++){
					
					if(category.equals(((CarOwner)users.get(i)).getTransportList().get(j).getCategory()) && ((CarOwner)users.get(i)).getTransportList().get(j).getAvailability()==true) {
						searchResult.add(((CarOwner)users.get(i)).getTransportList().get(j));		
					}
				}
				if(searchResult.size()==0) {
					System.out.println("Sorry! No car was found");
				}
				else {
					System.out.println("\t\tSearch Result");
					System.out.println("-----------------------------------------------------------------");
					System.out.println("TransportID\tModel\tCarPlate\tRate/h\tAvailable\tSegment\tCategory");
					for(int k = 0; k < searchResult.size(); k++) {
							
						System.out.println(searchResult.get(k).getTransportId() + "\t\t" + searchResult.get(k).getModel() +
								"\t" + searchResult.get(k).getCarPlate() + "\t\t" + searchResult.get(k).getRate() +
								"\t" + searchResult.get(k).getAvailability() + "\t\t" + searchResult.get(k).getSegment() +
								"\t" + searchResult.get(k).getCategory());
						}
					}
				
			}
		}
	}
	
	
	/*Car owner function*/
	public static void addCarToRent(CarOwner carowner) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n		Add Car To Renting List");
		System.out.println("\n\t       Please enter car details");
		System.out.println("-----------------------------------------------------------------");
		System.out.print("Car Model: ");
		String model = input.nextLine();
		System.out.print("Car Plate: ");
		String carplate = input.nextLine();
		System.out.print("Renting Rate Per Hour: Rm");
		double rate = Double.parseDouble(input.nextLine());
		System.out.print("Segment (A/B/C/D): ");
		String segment = input.nextLine();
		System.out.print("Category: ");
		String category = input.nextLine();
		
		carowner.addTransport(new Transport(model, carplate, rate, segment, category));
		
		System.out.println();
		System.out.println("** Car Registered Successfully! **");
	}
	
	public static void removeCarFromRent(CarOwner carowner) {
		
		Scanner input = new Scanner(System.in);
		
		if (carowner.getTransportList().size() == 0) {
			System.out.println("Please add a transport to renting list first ! ");
		}
		
		else {
			System.out.println("TransportID\tModel\tCarPlate\tRate/h\tAvailabile\tSegment\tCategory");
			System.out.println();
			for(int i = 0; i < carowner.getTransportList().size(); i++) {

				System.out.println(carowner.getTransportList().get(i).getTransportId() + "\t\t" + carowner.getTransportList().get(i).getModel() + 
								   "\t" + carowner.getTransportList().get(i).getCarPlate() + "\t\t" + carowner.getTransportList().get(i).getRate() + 
								   "\t" + carowner.getTransportList().get(i).getAvailability() + "\t\t" + carowner.getTransportList().get(i).getSegment() +
								   "\t" + carowner.getTransportList().get(i).getCategory());
			}
			
			System.out.print("Enter Transport ID of the car you wish to remove (0 to exit) : ");
			int carremoveid = Integer.parseInt(input.nextLine());
			
			for(int i = 0; i < carowner.getTransportList().size(); i++) {
				
				if(carremoveid == carowner.getTransportList().get(i).getTransportId()) {
					
					carowner.removeTransport(carowner.getTransportList().get(i));
				}
			}
		}	
	}
	
	public static void viewOwnerOrder(CarOwner carowner) {
		
		if (carowner.getOrders().size()==0) {
			System.out.println("No order has been made yet ! ");
		}
		
		else {
			System.out.println("\t\t\t\tYour Orders");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("OrderID\tTransportID\tModel\tCarPlate\tRate\tStartTime\tDuration\tTotal\tStatus");
			
			for(int i = 0; i < ((CarOwner)carowner).getOrders().size(); i++) {
				
				System.out.println((carowner.getOrders().get(i)).getOrderId() + "\t" + carowner.getOrders().get(i).getTransport().getTransportId() +
								   "\t\t" + carowner.getOrders().get(i).getTransport().getModel() + "\t" + carowner.getOrders().get(i).getTransport().getCarPlate() +
								   "\t\t" + carowner.getOrders().get(i).getTransport().getRate() + "\t" + carowner.getOrders().get(i).getStartTime() + 
								   "\t" + carowner.getOrders().get(i).getDuration() + "\t\t" + carowner.getOrders().get(i).getTotal() + "\t" + carowner.getOrders().get(i).getStatus());
			}
		}
	}
	
	public static void viewTransportList(CarOwner carowner) {
		String availability = "Available";
		if (carowner.getTransportList().size() == 0) {
			System.out.println("No Transport is added to rent");
		}
		else {
			for (int i=0; i<carowner.getTransportList().size();i++) {
				if(carowner.getTransportList().get(i).getAvailability()==true) {
					availability = "Available";
				}
				else {
					availability = "Unavailable";
				}
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("ID: "+carowner.getTransportList().get(i).getTransportId());
				System.out.println("Model: "+carowner.getTransportList().get(i).getModel());
				System.out.println("Car Plate: "+carowner.getTransportList().get(i).getCarPlate());
				System.out.println("Rental rate: Rm"+carowner.getTransportList().get(i).getRate());
				System.out.println("Availability: "+availability);
				System.out.println("Segment: "+carowner.getTransportList().get(i).getSegment());
				System.out.println();
			}
			System.out.println("-----------------------------------------------------------------------");
		}
	}
}
