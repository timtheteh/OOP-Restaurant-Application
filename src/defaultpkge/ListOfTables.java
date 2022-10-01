import java.util.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The ListOfTables class provides the methods used on the list of tables of the restaurant
 * @author Yong Xuan
 *
 */
public class ListOfTables {

	/**
	 * The list of tables of a restaurant
	 */
	private ArrayList<Table> listOfTables;
	
	/**
	 * Creates a ListOfTables object from the ListOfTables.txt file
	 */
 	public ListOfTables() {
		this.listOfTables = new ArrayList<Table>();
		try (Scanner scanner = new Scanner(Paths.get("ListOfTables.txt"))) {
			while (scanner.hasNextLine()) {
				String row = scanner.nextLine();
				// index 0: table num, index 1: capacity, index 2: isOccupied, index 3: isMember, index 4: LocalDateTime values separated with '|'
				String[] parts = row.split(",");
				int tableNumber = Integer.valueOf(parts[0]);
				int capacity = Integer.valueOf(parts[1]);
				boolean occupied = Boolean.valueOf(parts[2]);
				boolean isMember = Boolean.valueOf(parts[3]);
				ArrayList<LocalDateTime> futureReservation = new ArrayList<LocalDateTime>();
				if (!parts[4].equals("null")) {
					String[] dateTimes = parts[4].split("\\|");
					
					for (String str : dateTimes) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
						LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
						futureReservation.add(dateTime);
					}
				}
				listOfTables.add(new Table(tableNumber, capacity, occupied, isMember, futureReservation));
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		listOfTables.sort(Comparator.comparing(Table::getCapacity));
	}

 	/**
 	 * Returns the list of tables
 	 * @return ArrayList<Table>
 	 */
	public ArrayList<Table> getListOfTables() {
		return listOfTables;
	}

	/**
	 * Sets the list of tables.
	 * @param listOfTables
	 */
	public void setListOfTables(ArrayList<Table> listOfTables) {
		this.listOfTables = listOfTables;
	}

	/**
	 * Checks the availability of a table at a specific datetime for a specific number of guests.
	 * Returns the table number if a table is available at that timing without clashes with previous reservations.
	 * Returns -1 if table was not assigned.
	 * @param numberOfPax	The number of people at a table
	 * @param dateTime		The date and time in localdatetimeformat
	 * @return				Returns the avaliability of a table given the specified number of people and the date time
	 */
	public int checkAvailability(int numberOfPax, LocalDateTime dateTime) {
		LocalTime time = dateTime.toLocalTime();
		int tableNumberAssigned = -1;
		
		// if (time.plusHours(2).isAfter(LocalTime.of(23, 0)) || time.isBefore(LocalTime.of(9, 0))) {
		// 	return -1;
		// }
		
		if (!dateTime.isAfter(LocalDateTime.now().minusMinutes(1))) {
			return -1;
		}
		
		for (int i = 0; i < listOfTables.size(); i++) {
			if (listOfTables.get(i).getCapacity() >= numberOfPax) {
				boolean available = true;
				Table table = listOfTables.get(i);
				
				if (table.getOccupied() == true && dateTime.isBefore(LocalDateTime.now().plusHours(2))) {
					available = false;
				} else {
					for (LocalDateTime timing : table.getFutureReservationList()) {
						// e.g. timing = 14:00, we block out 12:01 to 15:59
						// this grace period of 2h before and after quite long, might want to change it to 1h.
						
						if (dateTime.isAfter(timing.minusHours(2)) && dateTime.isBefore(timing.plusHours(2))) {
							available = false;
							break;
						}
					}
				}
				if (available) {
					tableNumberAssigned = listOfTables.get(i).getTableNumber();
					listOfTables.get(i).addReservation(dateTime);
					break;
				}
			}
		}
		return tableNumberAssigned;
	}
	
	/**
	 * Checks the availability of a table at the current datetime.
	 * @param tableNumber	specified table number
	 * @return 				whether a table is available
	 */
	public boolean checkAvailabilityOfTable(int tableNumber) {
		LocalDateTime dateTime = LocalDateTime.now();
		LocalTime time = dateTime.toLocalTime();
		
		// if (time.plusHours(2).isAfter(LocalTime.of(21, 0)) || time.isBefore(LocalTime.of(9, 0))) {
		// 	System.out.println("Current time is outside of operating hours of restaurant. (09:00 to 21:00)");
		// 	return false;
		// }
		
		if (tableNumber < 1 || tableNumber > listOfTables.size()) {
			System.out.println("Table number " + tableNumber + " does not exist!");
			return false;
		}
		
		Table tableToCheck = null;
		for (Table table : listOfTables) {
			if (table.getTableNumber() == tableNumber) {
				tableToCheck = table;
				break;
			}
		}
		
		if (tableToCheck == null) {
			return false;
		}
		
		if (tableToCheck.getOccupied() == true) {
			System.out.println("Table " + tableNumber + " is currently occupied!");
			return false;
		}

		for (LocalDateTime timing : tableToCheck.getFutureReservationList()) {
			if (dateTime.isAfter(timing.minusHours(2)) && timing.isAfter(dateTime)) {
				System.out.println("Table is unavailable due to an upcoming booking.");
				return false;
			}
		}
		return true;
	}

	/**
	 * Removes future reservation at a certain datetime for a specified table.
	 * @param tableNumber	a specific table number
	 * @param dateTime		a given date and time
	 * @return 				whether the function was a success
	 */
	public boolean removeFutureReservation(int tableNumber, LocalDateTime dateTime) {
		// TODO - implement ListOfTables.removeFutureRservation
		for (Table table : listOfTables) {
			if (table.getTableNumber() == tableNumber) {
				table.removeReservation(dateTime);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes reservations on tables after 15 minute grace period.
	 * @return 					whether the function was a success
	 */
	public boolean removeExpiredReservations() {
		boolean anyExpired = false;
		LocalDateTime dateTimeToRemove = null;
		Table tableToRemoveFrom = null;
		for (Table table : listOfTables) {
			for (LocalDateTime dateTime : table.getFutureReservationList()) {
				if (dateTime.plusMinutes(15).isBefore(LocalDateTime.now())) {
					dateTimeToRemove = dateTime;
					tableToRemoveFrom = table;
					anyExpired = true;
					break;
				}
			}
			if (anyExpired) {
				break;
			}
		}
		if (anyExpired) {
			tableToRemoveFrom.removeReservation(dateTimeToRemove);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Retrieves table corresponding to specified tableNumber.
	 * @param tableNumber	the table number wanted
	 * @return 				returns the table object
	 */
	public Table getTable(int tableNumber) {
		Table table = null;
		for (int i=0; i<listOfTables.size(); i++) {
			table = listOfTables.get(i);
			if (table.getTableNumber() == tableNumber) {
				return table;
			}
		}
		return null;
	}
}
