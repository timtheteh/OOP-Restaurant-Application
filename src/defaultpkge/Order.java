/**
The Order class provides and manages data for each individual order in the restaurant.
@author Lim Xin Yi
@version 1.0
@since 2021-11-12
*/

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {

	/**
	 * The staff taking the order.
	 */
	private Staff staff;

	/**
	 * The table number of the table at which the order was taken.
	 */
	private int tableNumber;

	/**
	 * The date and time of the order.
	 */
	private LocalDateTime dateTime;

	/**
	 * The list of food items ordered.
	 */
	private ArrayList<Item> orderedItems;

	/**
	 * The table at which the order was taken.
	 */
	private Table table;
	
	/**
	 * Constructs a new Order object with the parameters staff, table.
	 * @param staff the staff taking the order
	 * @param table the table at which the order was taken
	 */
	public Order(Staff staff, Table table) {
		this.staff = staff;
		this.table = table;
		this.tableNumber = table.getTableNumber();
		this.orderedItems = new ArrayList<Item>();
	}

	/**
	 * Returns the staff taking the order.
	 * @return the staff taking the order
	 */
	public Staff getStaff() {
		return this.staff;
	}

	/**
	 * Sets the staff taking this order.
	 * @param staff the staff taking this order
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/**
	 * Returns the table number of the table at which order was taken at.
	 * @return the table number of the table at which order was taken at
	 */
	public int getTableNumber() {
		return this.tableNumber;
	}

	/**
	 * Sets the table number of the table at which this order was taken at.
	 * @param tableNumber the table number of the table at which order was taken at
	 */
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	/**
	 * Returns the list of food items ordered in the order.
	 * @return the list of food items ordered in the order
	 */
	public ArrayList<Item> getOrderedItems() {
		return this.orderedItems;
	}

	/**
	 * Returns the date and time of the order.
	 * @return the date and time of the order
	 */
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}

	/**
	 * Sets the date and time of the order.
	 * @param dateTime the date and time of the order
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Returns the total pre-tax, pre-discount price for all the items ordered in this order.
	 * @return the total pre-tax, pre-discount price for the items ordered
	 */
	public double calculatePrePrice() {
		Item food;
		int preTaxDiscPrice = 0;
		for (int i=0; i<orderedItems.size(); i++) {
			food = orderedItems.get(i);
			preTaxDiscPrice += food.getPrice();
		}
		return preTaxDiscPrice;
	}

	/**
	 * Returns the absolute amount of taxes charged after discounthas been applied,  for all the items ordered in this order.
	 * @return the absolute amount of taxes charged for the items ordered
	 */
	public double calculateTaxes() {		
		double GST = 0.07;
		double SERVICE_TAX = 0.1;
		double preTaxDiscPrice = calculatePrePrice();
		double discounted = calculateDiscount();
		return (preTaxDiscPrice-discounted) * (GST+SERVICE_TAX);
	}

	/**
	 * Returns the absolute amount of discount applied on all the items ordered in this order.
	 * A 10% discount applies if the table of the order possesses membership.
	 * Discount is applied before taxes.
	 * @return the absolute amount of discount applied
	 */
	public double calculateDiscount() {
		double DISCOUNT = 0.1;
		double preTaxDiscPrice = calculatePrePrice();
		double discount_rate = 0;
		if (table.getIsMember() == true) {
			discount_rate = DISCOUNT;
		}
		return preTaxDiscPrice * discount_rate;
	}

	/**
	 * Returns the final price of the items ordered in this order, after accounting for discount and taxes.
	 * @return the final price customer has to pay
	 */
	public double getFinalPrice() {
		double preTaxDiscPrice = calculatePrePrice();
		double discounted = calculateDiscount();
		double taxes = calculateTaxes();
		return preTaxDiscPrice-discounted+taxes;
	}

	/**
	 * Adds the food item into the list of ordered items of this order.
	 * @param item the newly ordered food item
	 */
	public Item addItem(Item item) {
		orderedItems.add(item);
		return item;
	}

	/**
	 * Removes the food item from the list of ordered items of this order.
	 * @param item the newly cancelled food item
	 */
	public Item removeItem(String item) {
		Item food;
		for (int i=0; i<orderedItems.size(); i++) {
			food = orderedItems.get(i);
			if (food.getName() == item) {
				orderedItems.remove(i);
				return food;
			}
		}
		//item not found in OrderedItems
		return null;
	}
	
	/**
	 * Prints the order invoice onto the terminal.
	 * This includes the details below:
	 *  restaurant name,
	 *  restaurant address,
	 *  date and time order was paid,
	 *  the table number,
	 *  the staff who took the order,
	 *  the list of ordered food items, including their indivdual prices,
	 *  the pre-tax pre-discount subtotal,
	 *  the amount of discount applied,
	 *  the amount of taxes charged, and
	 *  the final price.
	 */
	public void viewOrder() {
		dateTime = LocalDateTime.now();
		final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
		System.out.println("--------------- Order Invoice ---------------");
		System.out.println("The Bird");
		System.out.println("32 North Hill, Block 21A");
		System.out.println("Singapore 639798");
		System.out.printf("Table %d \n", tableNumber);
		System.out.printf("Serviced by %s \n", staff.getName());
		
		dateTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
	    System.out.printf("%s \n", dateTime.format(formatter));
		System.out.println();
	    Item food;
		for (int i=0; i<orderedItems.size(); i++) {
			food = orderedItems.get(i);
			System.out.printf("%-13s   %.2f\n", food.getName(), food.getPrice());
		}
		System.out.printf("Subtotal:    %-40.2f\n", calculatePrePrice());
		System.out.printf("Discount:    %-40.2f\n", calculateDiscount());
		System.out.printf("Taxes:       %-40.2f\n", calculateTaxes());
		System.out.printf("Total:       %-40.2f\n", getFinalPrice());
		System.out.println("---------------------------------------------");
	}


	/**
	 * Prints order invoice
	 * Clears up the table at which this order is taken at.
	 * Updates the order's date and time to when it was printed.
	 */
	public void printOrderInvoice() {
		table.clearTable();
		viewOrder();
	}
}