package asgn2Tests;

import java.time.LocalTime;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.VegetarianPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Nicholas Constantine (n9171550)
 *
 */
public class PizzaTests {
	
	//asgn2Pizzas.MargheritaPizza, 
	
	//asgn2Pizzas.VegetarianPizza, 
	
	//asgn2Pizzas.MeatLoversPizza classes. 
	
	//asgn2Pizzas.MeatLoversPizza
	
	private int quantityA = 3;
	private int quantityB = 2;
	private int quantityC = 8;
	private LocalTime orderTimeA = LocalTime.of(19, 10);
	private LocalTime orderTimeB = LocalTime.of(20, 19);
	private LocalTime orderTimeC = LocalTime.of(22, 05);
	private LocalTime deliveryTimeA = LocalTime.of(19, 25);
	private LocalTime deliveryTimeB = LocalTime.of(20, 30);
	private LocalTime deliveryTimeC = LocalTime.of(22, 20);
	private String typeA = "Margherita";
	private String typeB = "Vegetarian";
	private String typeC = "Meat Lovers";
	private Pizza PizzaA;
	private Pizza PizzaB;
	private Pizza PizzaC;
	
	@Before
	public void InitialisePizzas() throws PizzaException{
		PizzaA = new MargheritaPizza(quantityA, orderTimeA, deliveryTimeA);
		PizzaB = new VegetarianPizza(quantityB, orderTimeB, deliveryTimeB);
		PizzaC = new MeatLoversPizza(quantityC, orderTimeC, deliveryTimeC);	
	}
	
	//GetCostPerPizza
	@Test
	public void TestGetCostPerPizzaMargherita(){
		PizzaA.calculateCostPerPizza();
		assertEquals(1.5, PizzaA.getCostPerPizza(), 0);
	}
	
	@Test
	public void TestGetCostPerPizzaVegetarian(){
		PizzaB.calculateCostPerPizza();
		assertEquals(5.5, PizzaB.getCostPerPizza(), 0);
	}
	
	@Test
	public void TestGetCostPerPizzaMeatLovers(){
		PizzaC.calculateCostPerPizza();
		assertEquals(5, PizzaC.getCostPerPizza(), 0);
	}
	
	//GetPricePerPizza
	@Test
	public void TestGetPricePerPizzaMargherita(){
		assertEquals(8, PizzaA.getPricePerPizza(), 0);
	}
	
	@Test
	public void TestGetPricePerPizzaVegetarian(){
		assertEquals(10, PizzaB.getPricePerPizza(), 0);
	}
	
	@Test
	public void TestGetPricePerPizzaMeatLovers(){
		assertEquals(12, PizzaC.getPricePerPizza(), 0);
	}
	
	//GetOrderCost
	@Test
	public void TestGetOrderCostMargherita(){
		PizzaA.calculateCostPerPizza();
		assertEquals(1.5*quantityA, PizzaA.getOrderCost(), 0);
	}
	
	@Test
	public void TestGetOrderCostVegetarian(){
		PizzaB.calculateCostPerPizza();
		assertEquals(5.5*quantityB, PizzaB.getOrderCost(), 0);
	}
	
	@Test
	public void TestGetOrderCostMeatLovers(){
		PizzaC.calculateCostPerPizza();
		assertEquals(5*quantityC, PizzaC.getOrderCost(), 0);
	}
	
	//GetOrderPrice
	@Test
	public void TestGetOrderPriceMargherita(){
		assertEquals(8*quantityA, PizzaA.getOrderPrice(), 0);
	}
	
	@Test
	public void TestGetOrderPriceVegetarian(){
		assertEquals(10*quantityB, PizzaB.getOrderPrice(), 0);
	}
	
	@Test
	public void TestGetOrderPriceMeatLovers(){
		assertEquals(12*quantityC, PizzaC.getOrderPrice(), 0);
	}
	
	//GetOrderProfit
	@Test
	public void TestGetOrderProfitMargherita(){
		PizzaA.calculateCostPerPizza();
		assertEquals((8*quantityA)-(1.5*quantityA), PizzaA.getOrderProfit(), 0);
	}
	
	@Test
	public void TestGetOrderProfitVegetarian(){
		PizzaB.calculateCostPerPizza();
		assertEquals((10*quantityB)-(5.5*quantityB), PizzaB.getOrderProfit(), 0);
	}
	
	@Test
	public void TestGetOrderProfitMeatLovers(){
		PizzaC.calculateCostPerPizza();
		assertEquals((12*quantityC)-(5*quantityC), PizzaC.getOrderProfit(), 0);
	}
	
	//ContainsTopping
	@Test
	public void TestContainsToppingMargherita(){
		assertTrue(PizzaA.containsTopping(PizzaTopping.TOMATO));
		assertTrue(PizzaA.containsTopping(PizzaTopping.CHEESE));
	}
	
	@Test
	public void TestContainsToppingVegetarian(){
		assertTrue(PizzaB.containsTopping(PizzaTopping.TOMATO));
		assertTrue(PizzaB.containsTopping(PizzaTopping.CHEESE));
		assertTrue(PizzaB.containsTopping(PizzaTopping.EGGPLANT));
		assertTrue(PizzaB.containsTopping(PizzaTopping.MUSHROOM));
		assertTrue(PizzaB.containsTopping(PizzaTopping.CAPSICUM));
	}
	
	@Test
	public void TestContainsToppingMeatLovers(){
		assertTrue(PizzaC.containsTopping(PizzaTopping.TOMATO));
		assertTrue(PizzaC.containsTopping(PizzaTopping.CHEESE));
		assertTrue(PizzaC.containsTopping(PizzaTopping.BACON));
		assertTrue(PizzaC.containsTopping(PizzaTopping.PEPPERONI));
		assertTrue(PizzaC.containsTopping(PizzaTopping.SALAMI));
	}
	
	//GetQuantity
	@Test
	public void TestGetQuantityMargherita(){
		assertEquals(quantityA, PizzaA.getQuantity());
	}
		
	@Test
	public void TestGetQuantityVegetarian(){
		assertEquals(quantityB, PizzaB.getQuantity());
	}
		
	@Test
	public void TestGetQuantityMeatLovers(){
		assertEquals(quantityC, PizzaC.getQuantity());
	}
	
	//GetPizzaType
	@Test
	public void TestGetPizzaTypeMargherita(){
		assertEquals(typeA, PizzaA.getPizzaType());
	}
			
	@Test
	public void TestGetPizzaTypeVegetarian(){
		assertEquals(typeB, PizzaB.getPizzaType());
	}
	
	@Test
	public void TestGetPizzaTypeMeatLovers(){
		assertEquals(typeC, PizzaC.getPizzaType());
	}
	
	//Exceptions
	@Test(expected = PizzaException.class)
	public void ExceptionQuantityLessThanOne() throws PizzaException {
		Pizza InvalidPizza = new MargheritaPizza(0, orderTimeA, deliveryTimeA);
	}
	
	@Test(expected = PizzaException.class)
	public void ExceptionQuantityGreaterThanTen() throws PizzaException {
		Pizza InvalidPizza = new MargheritaPizza(11, orderTimeA, deliveryTimeA);
	}
	
	@Test(expected = PizzaException.class)
	public void ExceptionOrderTimeBeforeOpening() throws PizzaException {
		Pizza InvalidPizza = new MargheritaPizza(quantityA, LocalTime.of(18, 50), deliveryTimeA);
	}
	
	@Test(expected = PizzaException.class)
	public void ExceptionOrderTimeAfterClosing() throws PizzaException {
		Pizza InvalidPizza = new MargheritaPizza(quantityA, LocalTime.of(23, 10), deliveryTimeA);
	}
	
	@Test(expected = PizzaException.class)
	public void ExceptionDeliveryTimeBeforeTenMinutes() throws PizzaException {
		Pizza InvalidPizza = new MargheritaPizza(quantityA, LocalTime.of(20, 10), LocalTime.of(20, 19));
	}
	
	@Test(expected = PizzaException.class)
	public void ExceptionDeliveryTimeAfterOneHour() throws PizzaException {
		Pizza InvalidPizza = new MargheritaPizza(quantityA, LocalTime.of(20, 10), LocalTime.of(21, 11));
	}
	
}
