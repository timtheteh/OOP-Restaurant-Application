import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.lang.Math;

/**
 * The RestaurantApp provides the text-based user interface for the RRPSS.
 * @author Yong Xuan
 *
 */
public class RestaurantApp {

	/**
	 * The menu of the restaurant consisting of Item objects.
	 */
	private Menu menu;
	
	/**
	 * The list of staff members in the restaurant.
	 */
	private ArrayList<Staff> allStaff;
	
	/**
	 * All orders, past and current orders which the restaurant has.
	 */
	private AllOrders allOrders;
	
	/**
	 * List of tables in the restaurant.
	 */
	private ListOfTables allTables;
	
	/**
	 * The reservation list containing future bookings of the restaurant.
	 */
	private ReservationList reservationList;
	
	/**
	 * The sales revenue report of the restaurant generated from past orders.
	 */
	private SaleRevenueReport saleRevenueReport;

	/**
	 * Creates a new RestaurantApp by importing the text files which contains the Menu, AllStaff, AllOrders, ListOfTables, ReservationList, and SaleRevenueReport of the restaurant.
	 * @param menu	 menu object
	 * @param allStaff	 list of all staff
	 * @param allOrders	 list of orders
	 * @param allTables	list of tables
	 * @param reservationList	 list of reservations
	 * @param saleRevenueReport	 sales revenue report object
	 */
	public RestaurantApp() {
		this.menu = new Menu();
		this.setAllStaff();
		this.allOrders = new AllOrders();
		this.allTables = new ListOfTables();
		this.reservationList = new ReservationList(allTables);
	}
	
	/**
	 * Sets allStaff according to AllStaff.txt file
	 */
	public void setAllStaff() {
		allStaff = new ArrayList<Staff>();
		
		try (Scanner scanner = new Scanner(Paths.get("AllStaff.txt"))) {
			while (scanner.hasNextLine()) {
				String row = scanner.nextLine();
				String[] parts = row.split(",");
				Staff newStaff = new Staff(parts[0], parts[1], Integer.valueOf(parts[2]), parts[3]);
				allStaff.add(newStaff);
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Updates and prints the updated sales revenue report
	 */
	public void printSaleRevenueReport(LocalDate startPeriod, LocalDate endPeriod) {
		// TODO - implement RestaurantApp.printSaleRevenueReport
		if (startPeriod.isAfter(endPeriod)) {
			System.out.println("End period must be after start period.");
			return;
		}
		this.updateSalesRevenueReport(startPeriod, endPeriod);
		this.saleRevenueReport.printReport();
	}

	/**
	 * Updates sales revenue report of restaurant with the past (completed) orders list. 
	 */
	public void updateSalesRevenueReport(LocalDate startPeriod, LocalDate endPeriod) {
		
		String period = startPeriod.toString() + " to " + endPeriod.toString();
		
		HashMap<Item, Integer> itemHashMap = new HashMap<Item, Integer>();
		
		double revenue = 0;
		if (allOrders.getListOfPastOrders().size() == 0) {
			this.saleRevenueReport = new SaleRevenueReport(revenue, itemHashMap, period);
			return;
		}
		
		for (Order order : allOrders.getListOfPastOrders()) {
			LocalDate dateOfOrder = order.getDateTime().toLocalDate();
			if (dateOfOrder.isAfter(startPeriod.minusDays(1)) && dateOfOrder.isBefore(endPeriod.plusDays(1))) {
				revenue += order.getFinalPrice();
				for (Item item : order.getOrderedItems()) {
					if (itemHashMap.containsKey(item)) {
						itemHashMap.put(item, itemHashMap.get(item) + 1);
					} else {
						itemHashMap.put(item, 1);
					}
				}
			}
		}
		this.saleRevenueReport = new SaleRevenueReport(revenue, itemHashMap, period);
	}
	
	/**
	 * Returns the restaurant's current menu.
	 * @return  the menu
	 */
	public Menu getMenu() {
		return this.menu;
	}
	
	/**
	 * Returns a list of all staff members of the restaurant
	 * @return a list of staff
	 */
	public ArrayList<Staff> getAllStaff() {
		return this.allStaff;
	}
	
	/**
	 * Set the restaurant's new staff members using a text file of the all the staff members.
	 */
	public void setAllStaff(String fileName) {
		
	}
	
	/**
	 * Returns all orders of the restaurant, both past and present.
	 * @return AllOrders
	 */
	public AllOrders getAllOrders() {
		return this.allOrders;
	}
	
	/**
	 * Set the restaurant's (past and present) orders using a text file of all the orders.
	 * @param fileName
	 */
	public void setAllOrders(String fileName) {
		
	}
	
	/**
	 * Returns a list of all the tables of the restaurant.
	 * @return  the list of tables
	 */
	public ListOfTables getAllTables() {
		return this.allTables;
	}
	
	/**
	 * Set the restaurant's list of tables using a text file of all the tables.
	 * @param fileName
	 */
	public void setAllTables(String fileName) {
		
	}
	
	/**
	 * Returns the reservation list of the restaurant.
	 * @return the list of reservations
	 */
	public ReservationList getReservationList() {
		return this.reservationList;
	}
	
	/**
	 * Set the reservation list of the restaurant using a text file of the reservation list.
	 * @param fileName
	 */
	public void setReservationList(String fileName) {
		
	}
	
	/**
	 * Returns the SaleRevenueReport of the restaurant
	 * @return the sales revenue report
	 */
	public SaleRevenueReport getSaleRevenueReport() {
		return this.saleRevenueReport;
	}
	
	
	/**
	 * The main method of the entire app to help run the restaurant
	 */
	public static void main(String[] args) {
		RestaurantApp app = new RestaurantApp();
		Scanner sc = new Scanner(System.in);
		System.out.printf("Restaurant Reservation and Point of Sale System (RRPSS)\n\n");
		while (true) {
			System.out.println("1. Create/Update/Remove menu item\n"
					+ "2. Create/Update/Remove promotion\n"
					+ "3. Create order\n"
					+ "4. View order\n"
					+ "5. Add/Remove order item/s to/from order\n"
					+ "6. Create reservation booking\n"
					+ "7. Check reservation booking\n"
					+ "8. Remove reservation booking\n"
					+ "9. Check table availability\n"
					+ "10. Print order invoice\n"
					+ "11. Print sale revenue report by period");
			int choice;
			do {
				System.out.println("Select your choice (1-11): ");
				while (!sc.hasNextInt()) {
					System.out.println("Please enter a number from 1-11.");
					sc.nextLine();
				}
				choice = sc.nextInt();
				sc.nextLine();
			} while (choice <= 0 || choice > 11);
			
			switch (choice) {
			case 1:
		          // Create/Update/Remove menu item
		          int modifyMenuChoice = -1;
		          while (modifyMenuChoice < 0 || modifyMenuChoice > 4) {
		            try {
		              System.out.println("\nModifying Menu\n"
		                      + "1. Create Menu Item\n"
		                      + "2. Update Menu Item\n"
		                      + "3. Remove Menu Item\n"
		                      + "4. Exit");
		              modifyMenuChoice = sc.nextInt();
		            } catch (Exception e) {
		              System.out.println("Please enter a number.");
		              sc.nextLine();
		            }

		            switch(modifyMenuChoice){
		              case 1:
		                app.menu.createMenuItem();
		                break;
		              case 2:
		                app.menu.updateMenuItem();
		                break;
		              case 3:
		                app.menu.removeMenuItem();
		                break;
		              case 4:
		                break;
		            }
		          }
		          System.out.println("Press ENTER to continue");
                    try
                        {
                            System.in.read();
                        }  
                        catch(Exception e)
                        {}
                    break;
		        case 2: 
		          // Create/Update/Remove menu item
		          int modifyPMenuChoice = -1;
		          while (modifyPMenuChoice < 0 || modifyPMenuChoice > 4) {
		            try {
		              System.out.println("\nModifying Promo Menu\n"
		                      + "1. Create Promo Menu Item\n"
		                      + "2. Update Promo Menu Item\n"
		                      + "3. Remove Promo Menu Item\n"
		                      + "4. Exit");
		              modifyPMenuChoice = sc.nextInt();
		            } catch (Exception e) {
		              System.out.println("Please enter a number.");
		              sc.nextLine();
		            }

		            switch(modifyPMenuChoice){
		              case 1:
		                app.menu.createPromoItem();
						break;
		              case 2:
		                app.menu.updatePromoItem();
		                break;
		              case 3:
		                app.menu.removePromoItem();
						break;
		              case 4:
		                break;
		            }
		          }
		          System.out.println("Press ENTER to continue");
	                try
	                    {
	                        System.in.read();
	                    }  
	                    catch(Exception e)
	                    {}
	                break;
				case 3:
					// Create order
					boolean reserved = false;
					while (true) {
						try {
							System.out.println("Do you have a reservation made? Please type true or false: ");
							reserved = sc.nextBoolean();
							break;
						} catch (Exception e) {
							System.out.println("Please input true or false.");
							sc.nextLine();
						}
					}
					//Has reservation
					if (reserved) {
						int contactNumber;
						Reservation reservation;
						while (true) {
							try {
								System.out.println("Enter your contact number: ");
								contactNumber = sc.nextInt();
								reservation = app.reservationList.checkReservation(contactNumber);
								if (reservation != null) {
									break;
								}
							} catch (Exception e) {
								System.out.println("Please input a contact number.");
								sc.nextLine();
							}
						}
						int tableNumber = reservation.getTableNumber();
						Staff staff = app.allStaff.get((int) (Math.random() * app.allStaff.size()));
						Table table = app.allTables.getTable(tableNumber);
						boolean created = app.allOrders.createOrder(staff, table);
						if (created) {
							table.removeReservation(reservation.getBookingDateTime());
							System.out.println("Order created at table number " + table.getTableNumber() + ".");
							app.reservationList.removeReservation(contactNumber);
						}
						else {
							System.out.println("Failed to create order.");
						}
					}
					//No reservation
					else {
						int numOfPax;
						while (true) {
							try {
								System.out.println("Enter your number of seats required: ");
								numOfPax = sc.nextInt();
								break;
							} catch (Exception e) {
								System.out.println("Please input a number of pax.");
								sc.nextLine();
							}
						}
						LocalDateTime currentDateTime = LocalDateTime.now();
						int tableNumber4;
						tableNumber4 = app.allTables.checkAvailability(numOfPax, currentDateTime);
						Table table = null;
						if (tableNumber4 != -1) {
							table = app.allTables.getTable(tableNumber4);
						}
						if (table == null) {
							System.out.println("No table available. Failed to create order.");
						}
						else {
							Staff staff = app.allStaff.get((int) (Math.random() * app.allStaff.size()));
							boolean created = app.allOrders.createOrder(staff, table);
							if (created) {
								boolean isMember = false;
								while (true) {
									try {
										System.out.println("Are you a member? Please type in true or false.");
										isMember = sc.nextBoolean();
										break;
									} catch (Exception e) {
										System.out.println("Please input true or false.");
										sc.nextLine();
									}
								}
								table.setIsMember(isMember);
								System.out.println("Order created at table number " + table.getTableNumber() + ".");
							}
							else {
								System.out.println("Failed to create order.");
							}
						}
					}
					System.out.println("Press ENTER to continue");
                    try
                        {
                            System.in.read();
                        }  
                        catch(Exception e)
                        {}
                    break;
				case 4:
					// View current order given specific tableNumber
					int tableNumber;
					while (true) {
						try {
							System.out.println("Enter your table number: ");
							tableNumber = sc.nextInt();
							if (tableNumber < 1 || tableNumber > app.allTables.getListOfTables().size()){
								System.out.println("Invalid table number!");
								continue;
							}
							break;
						} catch (Exception e) {
							System.out.println("Please input a table number.");
							sc.nextLine();
						}
					}
					boolean exists = app.allTables.getTable(tableNumber).getOccupied();
					if (exists) {
						app.allOrders.getOrder(tableNumber).viewOrder();
					}
					else {
						System.out.println("Sorry! No existing order for given table number!");
					}
					System.out.println("Press ENTER to continue");
                    try
                        {
                            System.in.read();
                        }  
                        catch(Exception e)
                        {}
                    break;
				case 5:
					// Add/Remove order item/s to/from order
					int tableNumber1 = -1;
					while (true) {
						try {
							System.out.println("Enter your table number: ");
							tableNumber1 = sc.nextInt();
							if (tableNumber1 < 1 || tableNumber1 > app.allTables.getListOfTables().size()){
								System.out.println("Invalid table number!");
								continue;
							}
							break;
						} catch (Exception e) {
							System.out.println("Please input a table number.");
							sc.nextLine();
						}
					}
					boolean exists1 = app.allTables.getTable(tableNumber1).getOccupied();
					if (exists1) {
						char add_remove = 0;
						while (add_remove != 'Q') {
							while (true ) {
								try {
									System.out.println("Enter A to add, R to remove food item from order, Q to return to main menu: ");
									add_remove = sc.next().charAt(0);
									break;
								} catch (Exception e) {
									System.out.println("Please input A or R: ");
									sc.nextLine();
								}
							}
							switch (add_remove) {
								case 'A':
									Item added;
									added = app.allOrders.addItem(tableNumber1, app.menu);
									if (added != null) {
										System.out.println("Successful addition of " + added.getName());
									}
									else{
										System.out.println("Failed to add item.");
									}
									break;
								case 'R':
									Item removed;
									removed = app.allOrders.removeItem(tableNumber1, app.menu);
									if (removed != null) {
										System.out.println("Successful removal of " + removed.getName());
									}
									else{
										System.out.println("Failed to remove item.");
									}
									break;
								case 'Q':
									break;
								default:
									System.out.println("Please input A or R: ");
									break;
							}
						}
					}
					else {
						System.out.println("Sorry! No existing order for given table number!");
					}
					System.out.println("Press ENTER to continue");
                    try
                        {
                            System.in.read();
                        }  
                        catch(Exception e)
                        {}
                    break;
				case 6:
					// Create reservation booking
					while (app.reservationList.removeAllExpiredReservations()) {}
					while (app.allTables.removeExpiredReservations()) {}
					LocalDateTime dateTime = null;
					while (true) {
						System.out.println("Enter the date and time of your reservation e.g yyyy-mm-dd HH:mm");
						DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
						String bookingDateTime = sc.nextLine();
					    try {
					    	dateTime = LocalDateTime.parse(bookingDateTime, dateTimeFormatter);
					        break;
					    } catch(DateTimeParseException ex) {
					        System.out.println("Invalid date and time entered!");
					    }
					}
					System.out.println("Enter your name: ");
					String name = sc.next();
					int contactNumber, numPax;
					boolean isMember;
					while (true) {
						try {
							System.out.println("Enter your contact number: ");
							contactNumber = sc.nextInt();
							break;
						} catch (Exception e) {
							System.out.println("Please input a contact number.");
							sc.nextLine();
						}
					}
					while (true) {
						try {
							System.out.println("Enter the number of pax you are booking for: ");
							numPax = sc.nextInt();
							break;
						} catch (Exception e) {
							System.out.println("Please input a number.");
							sc.nextLine();
						}
					}
					while (true) {
						try {
							System.out.println("Are you a member? Please type in true or false.");
							isMember = sc.nextBoolean();
							break;
						} catch (Exception e) {
							System.out.println("Please input true or false.");
							sc.nextLine();
						}

					}

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.US);

					int createdReservation = app.reservationList.createReservation(dateTime, name, contactNumber, numPax, isMember);
					if (createdReservation != 0) {
						System.out.println("------------------------- Reservation Details -------------------------");
						System.out.println("Reservation created successfully!\n The details of your reservation are as follows:" +
							"\n Name: " + name +
							"\n Table number: " + createdReservation + 
							"\n Date and time of reservation: " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime) +
							"\n Number of pax: " + numPax + ".");
						System.out.println("-----------------------------------------------------------------------");
					}
					else {
						System.out.println("Sorry! No available slot!");
					}
					System.out.println("Press ENTER to continue");
                    try
                        {
                            System.in.read();
                        }  
                        catch(Exception e)
                        {}
                    break;
				case 7:
					// Check reservation booking
					while (app.reservationList.removeAllExpiredReservations()) {}
					while (app.allTables.removeExpiredReservations()) {}
					System.out.println("To check the status of a reservation, please input the relevant contact number: ");
					int contactNumberCheck;
					while (true) {
						try {
							System.out.println("Enter your contact number: ");
							contactNumberCheck = sc.nextInt();
							break;
						} catch (Exception e) {
							System.out.println("Please input a contact number.");
							sc.nextLine();
						}
					}
					Reservation reservation = app.reservationList.checkReservation(contactNumberCheck);
					if (reservation != null) {
						System.out.println("There is a reservation under " + contactNumberCheck + " under the name of " + reservation.getName() + " at " + reservation.getBookingDateTime().toString());
					} else {
						System.out.println("There is no reservation under " + contactNumberCheck + ".");
					}
					System.out.println("Press ENTER to continue");
                    try
                        {
                            System.in.read();
                        }  
                        catch(Exception e)
                        {}
                    break;
				case 8:
					//Remove reservation booking
					while (app.reservationList.removeAllExpiredReservations()) {}
					while (app.allTables.removeExpiredReservations()) {}
					System.out.println("To cancel your reservation, please input the relevant contact number: ");
					int contactNumberRemove;
					while (true) {
						try {
							System.out.println("Enter your contact number: ");
							contactNumberRemove = sc.nextInt();
							break;
						} catch (Exception e) {
							System.out.println("Please input a contact number.");
							sc.nextLine();
						}
					}
					Reservation reservationToRemove = app.reservationList.checkReservation(contactNumberRemove);
					
					if (app.reservationList.removeReservation(contactNumberRemove)) {
						app.allTables.removeFutureReservation(reservationToRemove.getTableNumber(), reservationToRemove.getBookingDateTime());
						System.out.println("Reservation under " + reservationToRemove.getName() + " with phone number " + contactNumberRemove + " has been cancelled.");
					} else {
						System.out.println("There is no reservation under " + contactNumberRemove + ".");
					}
					System.out.println("Press ENTER to continue");
                    try
                        {
                            System.in.read();
                        }  
                        catch(Exception e)
                        {}
                    break;
				case 9:
					// Check table availability
					int tableToCheck;
					int noOfTables = app.allTables.getListOfTables().size();
					do {
						System.out.println("Select table to check availability (1-" + noOfTables + "): ");
						while (!sc.hasNextInt()) {
							System.out.println("Please enter a number from 1-" + noOfTables + ".");
							sc.nextLine();
						}
						tableToCheck = sc.nextInt();
						sc.nextLine();
					} while (tableToCheck <= 0 || tableToCheck > noOfTables);
					
					if (app.allTables.checkAvailabilityOfTable(tableToCheck)) {
						System.out.println("Table " + tableToCheck + " is available!");
					}
					
					System.out.println("Press ENTER to continue");
                    try
                        {
                            System.in.read();
                        }  
                        catch(Exception e)
                        {}
                    break;
				case 10: 
					// Print order invoice
					int tableNumber11;
					while (true) {
						try {
							System.out.println("Enter your table number: ");
							tableNumber11 = sc.nextInt();
							if(tableNumber11<1 || tableNumber11>app.allTables.getListOfTables().size()){
								System.out.println("Table number is invalid!");
								continue;
							}
							break;
						} catch (Exception e) {
							System.out.println("Please input a table number.");
							sc.nextLine();
						}
					}
					boolean exists3 = app.allTables.getTable(tableNumber11).getOccupied();
					if (exists3) {
						app.allOrders.printOrderInvoice(tableNumber11);
					}
					else {
						System.out.println("Sorry! No existing order for given table number!");
					}
					System.out.println("Press ENTER to continue");
                    try
                        {
                            System.in.read();
                        }  
                        catch(Exception e)
                        {}
                    break;
				case 11:
					// Print sale revenue report by period
					LocalDate startDate = null;
					LocalDate endDate = null;

					while (true) {
					    System.out.println("Enter start period e.g yyyy-mm-dd: ");
					    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					    String date = sc.next();
					    try {
					        startDate = LocalDate.parse(date, formatter);
					        break;
					    } catch(DateTimeParseException ex) {
					        System.out.println("Invalid date entered!");
					    }
					}
					
					while (true) {
					    System.out.println("Enter end period e.g yyyy-mm-dd: ");
					    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					    String date = sc.next();
					    try {
					        endDate = LocalDate.parse(date, formatter);
					        break;
					    } catch(DateTimeParseException ex) {
					        System.out.println("Invalid date entered!");
					    }
					}
					System.out.println();
					app.printSaleRevenueReport(startDate, endDate);
					System.out.println();
					System.out.println("Press ENTER to continue");
                    try
                        {
                            System.in.read();
                        }  
                        catch(Exception e)
                        {}
                    break;
			}
		}
	}
}