package asgn2Restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import asgn2Customers.Customer;
import asgn2Pizzas.Pizza;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.LogHandler; 

/**
 * This class acts as a �model� of a pizza restaurant. It contains an ArrayList of Pizza objects and an ArrayList of  Customer objects.
 *  It contains a method that can populate the ArrayLists,  several methods to retrieve information about the ArrayLists and 
 *  a method to reset the array list. Information about the x and y location of the restaurant and the time that first and last 
 *  orders are accepted are listed in Section 5 of the Assignment Specification. 
 *  
 *  Any exceptions raised by one of the methods called by this class should be passed to asgn2GUIs.PizzaGUI so that it can be shown to
 *  the user.
 * 
 * @author Thomas Shortt (n8854742) and Nicholas Constantine (n9171550)
 *
 */
public class PizzaRestaurant {

	private ArrayList<Customer> customers;
	private ArrayList<Pizza> pizzas;

	/**
	 * Creates an instance of the PizzaRestaurant and sets the customers and pizzas fields to
	 * an appropriate initial empty state. 
	 * 
	 * <P> PRE: TRUE
	 * <P> POST: The customers and pizzas fields are initialized to an empty state
	 * 
	 */
	public PizzaRestaurant() {
		this.customers = new ArrayList<Customer>();
		this.pizzas = new ArrayList<Pizza>();
	}

	/**
	 * This method processes the information contained in the log file and populates the customers and pizzas fields.  
	 * The other classes that the method interacts with are listed in Section 11 of the specification document. 
     *
     * <P> PRE: TRUE
     * <P>POST: If no exception is thrown then the customers and pizzas fields are populated with the details in the log file ordered as they appear in the log file.
     * <P>      If an exception is thrown then the customers and pizzas fields should be empty.
     * 
	 * @param filename The log's filename
	 * @return true if the file was process correctly otherwise false
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above (passed by another class).
     *
	 */
	public boolean processLog(String filename) throws CustomerException, PizzaException, LogHandlerException{
		boolean FileProcessedCorrectly = true;
		try {
			BufferedReader LogToRead = new BufferedReader(new FileReader(filename));
			pizzas = LogHandler.populatePizzaDataset(filename);
			customers = LogHandler.populateCustomerDataset(filename);
			boolean elementsInOrder = true;
			
			String line;
			int i = 0;
			String Type = null;
			while((line = LogToRead.readLine()) != null) {
				String lineArray[] = line.split(",");
				if (!customers.get(i).getName().equals(lineArray[2])
				|| customers.get(i).getLocationX() != Integer.parseInt(lineArray[5]) 
				|| customers.get(i).getLocationY() != Integer.parseInt(lineArray[6])){
					elementsInOrder = false;
				}
				if (!customers.get(i).getMobileNumber().equals(lineArray[3])){
					elementsInOrder = false;
					
				}
				if (customers.get(i).getCustomerType().equals("Pick Up")){
					Type = "PUC";
				} else if (customers.get(i).getCustomerType().equals("Driver Delivery")){
					Type = "DVC";
				} else if (customers.get(i).getCustomerType().equals("Drone Delivery")){
					Type = "DNC";
				} else {
					throw new LogHandlerException("Log file doesn't contain correct customer code.");
				}
				if (!Type.equals(lineArray[4])){
					elementsInOrder = false;
				}
			
				if (pizzas.get(i).getQuantity() != Integer.parseInt(lineArray[8])) {
					elementsInOrder = false;
				}
				if (pizzas.get(i).getPizzaType().equals("Vegetarian")){
					Type = "PZV";
				} else if (pizzas.get(i).getPizzaType().equals("Margherita")){
					Type = "PZM";
				} else if (pizzas.get(i).getPizzaType().equals("Meat Lovers")){
					Type = "PZL";
				} else {
					throw new LogHandlerException("Log file doesn't contain correct pizza code.");
				}
				if (!Type.equals(lineArray[7])){
					elementsInOrder = false;
				}
				i++;
			}
			LogToRead.close();
			if (!elementsInOrder) {
				throw new LogHandlerException("Elements are out of order");
			}

		} catch (IOException e){
			System.out.println(e.getMessage());
        	e.printStackTrace();
        	FileProcessedCorrectly = false;
		}
		return FileProcessedCorrectly;
	}

	/**
	 * Returns the Customer object contained at the specified index of the customers field. The index should be the same as the index in the log file.
	 * @param index - The index within the customers field to retrieve.
	 * @return The Customer object located at the specified index.
	 * @throws CustomerException if index is invalid.
	 */
	public Customer getCustomerByIndex(int index) throws CustomerException{
		if (index < 0 || index >= getNumCustomerOrders()){
			throw new CustomerException("Index is invalid.");
		}
		return this.customers.get(index);
	}
	
	/**
	 * Returns the Pizza object contained at the specified index of the pizzas field. The index should be the same as the index in the log file.
	 * @param index - The index within the pizzas field to retrieve.
	 * @return The Pizza object located at the specified index.
	 * @throws PizzaException if index is invalid.
	 */	
	public Pizza getPizzaByIndex(int index) throws PizzaException{
		if (index < 0 || index >= this.getNumPizzaOrders()) {
			throw new PizzaException("Index is invalid!");
		}
		return this.pizzas.get(index);
	}
	
	/**
	 * Returns the number of objects contained in the pizzas field. This value SHOULD be the same as 
	 * the value returned by getNumCustomerOrders.
	 * 
	 * @return the number of objects contained in the pizzas field.
	 */
	public int getNumPizzaOrders(){
		return this.pizzas.size();
	}

	/**
	 * Returns the number of objects contained in the customers field. This value SHOULD be the same as 
	 * the value returned by getNumPizzaOrders.
	 * 
	 * @return the number of objects contained in the customers field.
	 */
	public int getNumCustomerOrders(){
		return customers.size();
	}

			
	
	/**
	 * Returns the total delivery distance for all of the customers.
	 * 
	 * @return the total delivery distance for all Customers objects in the customers field.
	 */
	public double getTotalDeliveryDistance(){
		double totalDistance = 0.00;
		for (Customer customer : this.customers){
			totalDistance  += customer.getDeliveryDistance();
		}
		return totalDistance;
	}

	/**
	 * Returns the total profit for all of the pizza orders.
	 * 
	 * @return the total profit for all of the Pizza objects in the pizzas field.
	 */	
	public double getTotalProfit(){
		double totalProfit = 0.00;
		for (Pizza pizza : this.pizzas) {
			totalProfit += pizza.getOrderProfit();
		}
		return totalProfit;
	}
	
	/**
	 * Resets the pizzas and customers fields to their initial empty states.
	 * 
	 * <P> PRE: True
	 * <P> POST:  The pizzas and customers fields are set to their initial empty states
	 */
	public void resetDetails(){
		this.customers.clear();
		this.pizzas.clear();
	}

}
