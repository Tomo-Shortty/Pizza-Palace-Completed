package asgn2Pizzas;

import java.time.LocalTime;

import asgn2Exceptions.PizzaException;


/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant. 
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and MeatLoversPizza. 
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification. 
 * 
 * @author Thomas Shortt (n8854742)
 *
 */
public abstract class Pizza  {
	
	private int quantity;
	private LocalTime orderTime;
	private LocalTime deliveryTime;
	private LocalTime openingTime = LocalTime.of(19, 00);
	private LocalTime closingTime = LocalTime.of(23, 00);
	private String type;
	private double price;
	private double cost = 0;
	
	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1. 
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated. 
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 * 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if quantity is less than 1 or greater than 10, orderTime is before 7pm or after 11pm, deliveryTime is before 10 minutes after ordering or 1 hour after ordering, type is not Margherita, Vegetarian or Meat Lovers, or price does not match the correct price of the specified pizza type
	 * 
	 */
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException{
		if (quantity < 1 || quantity > 10) {
			throw new PizzaException("Invalid quantity!");
		} else if (orderTime.isBefore(openingTime) == true || orderTime.isAfter(closingTime) == true) {
			throw new PizzaException("Order time is invalid!");
		} else if (deliveryTime.isBefore(orderTime.plusMinutes(10)) == true || deliveryTime.isAfter(orderTime.plusHours(1)) == true) {
			throw new PizzaException("Delivery time is invalid!");
		} else if (type.equals("Margherita") == false && type.equals("Vegetarian") == false && type.equals("Meat Lovers") == false) {
			throw new PizzaException("Pizza type is invalid!");
		} else if ((type.equals("Margherita") && price != 8.00) || (type.equals("Vegetarian") && price != 10.00) || (type.equals("Meat Lovers") && price != 12.00)) {
			throw new PizzaException("Price is invalid!");
		}
		this.quantity = quantity;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.type = type;
		this.price = price;
	}

	/**
	 * Calculates how much a pizza would could to make calculated from its toppings.
	 *  
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza(){
		if (this.getPizzaType().equals("Margherita") == true) {
			this.cost = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost();
		} else if (this.getPizzaType().equals("Vegetarian") == true) {
			this.cost = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost() + PizzaTopping.EGGPLANT.getCost() + PizzaTopping.MUSHROOM.getCost() + PizzaTopping.CAPSICUM.getCost();
		} else if (this.getPizzaType().equals("Meat Lovers") == true) {
			this.cost = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost() + PizzaTopping.BACON.getCost() + PizzaTopping.PEPPERONI.getCost() + PizzaTopping.SALAMI.getCost();
		}
	}
	
	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		this.calculateCostPerPizza();
		return this.cost;
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		return this.price;
	}

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderCost(){
		return this.getCostPerPizza() * this.getQuantity();
	}
	
	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderPrice(){
		return this.getPricePerPizza() * this.getQuantity();
	}
	
	
	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost. 
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		return this.getOrderPrice() - this.getOrderCost();
	}
	

	/**
	 * Indicates if the pizza contains the specified pizza topping or not. 
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
		if (this.getPizzaType().equals("Margherita")) {
			if (topping == PizzaTopping.TOMATO || topping == PizzaTopping.CHEESE){
				return true;
			}
		}
		if (this.getPizzaType().equals("Vegetarian")) {
			if (topping == PizzaTopping.TOMATO || topping == PizzaTopping.CHEESE || topping == PizzaTopping.EGGPLANT || topping == PizzaTopping.MUSHROOM || topping == PizzaTopping.CAPSICUM){
				return true;
			}
		}
		if (this.getPizzaType().equals("Meat Lovers")) {
			if (topping == PizzaTopping.TOMATO || topping == PizzaTopping.CHEESE || topping == PizzaTopping.BACON || topping == PizzaTopping.PEPPERONI || topping == PizzaTopping.SALAMI){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the quantity of pizzas ordered. 
	 * @return the quantity of pizzas ordered. 
	 */
	public final int getQuantity(){
		return this.quantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type. 
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification. 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		return this.type;
	}


	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *  
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for 	
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza() 
	 * and getQuantity().
	 *   
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&				
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}

	
}
