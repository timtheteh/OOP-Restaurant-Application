/**
The AllOrder class controls all the orders in the restaurant. 
@author Lim Xin Yi
@version 1.0
@since 2021-11-12
*/

import java.util.*;
import java.io.IOException;


public class AllOrders {

	/**
	 * List of past orders, ie. paid orders.
	 */
	private ArrayList<Order> listOfPastOrders = new ArrayList<Order>();

	/**
	 * List of current orders, ie. unpaid orders.
	 */
	private ArrayList<Order> listOfCurrentOrders = new ArrayList<Order>();

	/**
	 * Returns list of past orders, ie. paid orders.
	 * @return the list of past orders
	 */
	public ArrayList<Order> getListOfPastOrders() {
		return this.listOfPastOrders;
	}

	/**
	 * Set the list of past orders, ie. paid orders.
	 * @param listOfOrders the list of past orders
	 */
	public void setListOfPastOrders(ArrayList<Order> listOfOrders) {
		this.listOfPastOrders = listOfOrders;
	}
	
	/**
	 * Adds an order to the list of past orders.
	 * @param pastOrder the newly paid order
	 */
	public void addPastOrder(Order pastOrder) {
		this.listOfPastOrders.add(pastOrder);
	}

	/**
	 * Removes an order from the list of current orders.
	 * @param currentOrder the newly paid order
	 */
	public void removeCurrentOrder(Order currentOrder) {
		this.listOfCurrentOrders.remove(currentOrder);
	}


	/**
	 * Creates a new Order, defining Staff and Tablenumber in the order.
	 * New order is added to listOfCurrentOrders.
	 * @param staff
	 * @param tableNumber
	 */
	public boolean createOrder(Staff staff, Table table) {
		Order order = new Order(staff, table);
		listOfCurrentOrders.add(order);
		table.setOccupied(true);
		return true;
	}
	
	/**
	 * Returns specific current order coresponding to the specified table number.
	 * @param tableNumber the table number at which this prder was taken
	 * @return            the current order corresponding to the specified table number
	 */
	public Order getOrder(int tableNumber) {
		Order order = null;
		for (int i=0; i<listOfCurrentOrders.size(); i++) {
			order = listOfCurrentOrders.get(i);
			if (order.getTableNumber() == tableNumber) {
				//found existing Order with correpsonding tablenumber
				break;
			}
		}
		return order;
	}
	
	/**
	 * Prints order corresponding to specified table number.
	 * @param tableNumber table number of the order to be viewed
	 */	
	public void viewOrder(int tableNumber) {
		Order order = getOrder(tableNumber);
		order.viewOrder();
	}

	/**
	 * Adds food to order corresponding to given table number.
	 * List of menu items will be displayed, and the user will choose from the list the food to be added.
	 * @param tableNumber the table number of the order to which food is to be added
	 * @param menu        the menu of the restaurant
	 * @return            the item that has been added successfully
	 */
	public Item addItem(int tableNumber, Menu menu) {
		Order order = getOrder(tableNumber);
		ArrayList<Item> menuItems = menu.getListOfFood();
		Scanner sc = new Scanner(System.in);
		Item item;
		Item addedItem = null;
		for (int j=0; j<menuItems.size(); j++) {
			item = menuItems.get(j);
			System.out.printf("%d. %-30s  $%.2f\t\t %s\n", j+1, item.getName(), item.getPrice(), item.getDescription());
		}
		try {
			int opt = -1; 
			while ((opt < 1) || (opt > menuItems.size())) {
				System.out.println("Enter number to add food to order: ");
				opt = sc.nextInt();	
			}
			addedItem = order.addItem(menuItems.get(opt - 1));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return addedItem;
	}

	/**
	 * Removes food from order corresponding to given tableNumber.
     * List of food ordered will be displayed, and the user will choose from the list the food to be removed.		 * @param tableNumber
	 * @param tableNumber the table number of the order to which food is to be added
	 * @param menu        the menu of the restaurant
	 * @return            the item that has been removed successfully
	 */
	public Item removeItem(int tableNumber, Menu menu) {
		Order order = getOrder(tableNumber);
		ArrayList<Item> orderedItems = order.getOrderedItems();
		Scanner sc = new Scanner(System.in);
		Item item;
		Item removedItem = null;
		for (int j=0; j<orderedItems.size(); j++) {
			item = orderedItems.get(j);
			System.out.printf("%d.  %s \n", j+1, item.getName());
		}
		try {
			
			int opt = -1; 
			while ((opt < 1) || (opt > orderedItems.size())) {
				System.out.println("Enter number to remove food from order: ");
				opt = sc.nextInt();
			}
			removedItem = order.removeItem(orderedItems.get(opt - 1).getName());
		}
		catch(Exception exception){
			System.out.println(exception.getMessage());
		}
		return removedItem;
	}

	/**
	 * Prints the order invoice of order of given tablenumber.
	 * Removes the order to the list of current orders.
	 * Adds the order to the list of past orders.
	 * @param tableNumber the table number of the order to be printed
	 */
	public void printOrderInvoice(int tableNumber) {
		Order order = getOrder(tableNumber);
		addPastOrder(order);
		order.printOrderInvoice();
	}

	/**
	 * Returns the list of current orders, ie. unpaid orders.
	 * @return the list of current orders
	 */
	public ArrayList<Order> getListOfCurrentOrders() {
		return this.listOfCurrentOrders;
	}

	/**
	 * Sets the list of current orders, ie. unpaid orders.
	 * @param listOfCurrentOrders the list of current orders
	 */
	public void setListOfCurrentOrders(ArrayList<Order> listOfCurrentOrders) {
		this.listOfCurrentOrders = listOfCurrentOrders;
	}

}