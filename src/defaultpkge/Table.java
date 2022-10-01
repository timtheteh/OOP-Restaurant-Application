import java.util.*;
import java.time.LocalDateTime;

/**
 * The Table class provides and manages data for each individual table in the restaurant.
 * @author Yong Xuan
 *
 */
public class Table {
	/**
	 * The table number of the table.
	 */
	private int tableNumber;
	
	/**
	 * The seating capacity of the table.
	 */
	private int capacity;
	
	/**
	 * The occupied status of the table.
	 */
	private boolean occupied;
	
	/**
	 * The status of whether the diners seated at the table are members of the restaurant.
	 */
	private boolean isMember;
	
	/**
	 * The list of date and time of future reservations of the table.
	 */
	private ArrayList<LocalDateTime> futureReservationList;

	/**
	 * Creates a new Table with the parameters tableNumber, capacity, occupied, isMember, futureReservation
	 * @param tableNumber             	   the table's table number
	 * @param capacity                     the table's seating capacity
	 * @param occupied                     the table's occupancy status
	 * @param isMember                     the status of whether the diners seated at the table are members of the restaurant
	 * @param futureReservation 		   the list of date and time of future reservations of the table
	 */
	public Table(int tableNumber, int capacity, boolean occupied, boolean isMember, ArrayList<LocalDateTime> futureReservationList) {
		this.tableNumber = tableNumber;
		this.capacity = capacity;
		this.occupied = occupied;
		this.isMember = isMember;
		this.futureReservationList = futureReservationList;
	}

	/**
	 * Returns the table's table number.
	 * @return the table's table number
	 */
	public int getTableNumber() {
		return this.tableNumber;
	}

	/**
	 * Sets the table's table number.
	 * @param tableNumber the table's table number
	 */
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	/**
	 * Returns the table's seating capacity.
	 * @return the table's seating capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Sets the table's seating capacity.
	 * @param capacity the table's seating capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Returns the table's occupancy status.
	 * @return the table's occupancy status
	 */
	public boolean getOccupied() {
		return this.occupied;
	}

	/**
	 * Sets the table's occupancy status.
	 * @param occupied the table's occupancy status
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * Returns the status of whether the diners seated at the table are members of the restaurant.
     * Returns true if any of the table occupants is member, else returns false.
	 * @return the status of whether any of the diners seated at the table is a member of the restaurant
	 */
	public boolean getIsMember() {
		return this.isMember;
	}

	/**
	 * Set the status of whether any of the diners seated at the table is a member of the restaurant.
	 * @param isMember the status of whether any of the diners seated at the table is a member of the restaurant
	 */
	public void setIsMember(boolean isMember) {
		this.isMember = isMember;
	}

	/**
	 * Returns the list of date and time of the future reservations of the table.
	 * @return the list of date and time of the future reservations of the table
	 */
	public ArrayList<LocalDateTime> getFutureReservationList() {
		return this.futureReservationList;
	}

	/**
	 * Set the list of date and time of the future reservations of the table.
	 * @param futureReservationList the list of date and time of the future reservations of the table
	 */
	public void setFutureReservation(ArrayList<LocalDateTime> futureReservationList) {
		this.futureReservationList = futureReservationList;
	}
 
	/**
	 * Resets the table statuses, namely occupancy status and membership possession status, after guests leave the table.
	 */
	public void clearTable() {
		this.occupied = false;
		this.isMember = false;
	}
	
	/**
     * Adds the date and time of a new reservation of the table to the existing list of date and time of the future reservations of the table.
     * @param time the date and time of the reservation to be added
	 */
	public void addReservation(LocalDateTime time) {
		this.futureReservationList.add(time);
	}
	
	/**
     * Removes a specified date and time from the existing list of date and time of the future reservations of the table.
     * @param time the date and time of the reservation to be removed
     */
	public void removeReservation(LocalDateTime time) {
		this.futureReservationList.remove(time);
	}

}