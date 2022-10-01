/**
 * The Reservation object contains information about the reservation as well as some functions
 * such as allocateTable(ListOfTables listOfTables) and isExpired()
 * @author Timothy Teh
 *
 */

import java.time.LocalDateTime;

public class Reservation {
  
  /**
   * bookingDateTime represents the date and time of the reservation.
   */
  private LocalDateTime bookingDateTime;
  
  /**
   * name is the name of the customer who made the reservation.
   */
  private String name;
  
  /**
   * contactNumber is the contact number of the customer who made the reservation.
   */
  private int contactNumber;
  
  /**
   * numPax is the number of people the reservation is intended for.
   */
  private int numPax;
  
  /**
   * tableNumber is the table number the reservation is assigned to.
   */
  private int tableNumber;
  
  /**
   * isMember is a boolean of whether the customer who made the reservation is entitled to 
   * the promotional set package.
   */
  private boolean isMember;
  
  /**
   * Constructs a new Reservation class with variables from user input such as 
   * bookingDateTime, name, contactNumber, numPax, isMember
   * @param bookingDateTime
   * @param name
   * @param contactNumber
   * @param numPax
   * @param isMember
   */
  public Reservation (LocalDateTime bookingDateTime, String name, int contactNumber, int numPax, boolean isMember) {
    this.bookingDateTime = bookingDateTime;
    this.name = name;
    this.contactNumber = contactNumber;
    this.numPax = numPax;
    this.isMember = isMember;
  }
  
  /**
   * allocateTable is a function which sets the tableNumber attribute to the tableAssigned
   * if its numPax and bookingDateTime are valid, these are checked in
   * listOfTables.checkAvailaility().
   * @param listOfTables
   * @return int
   */
  public int allocateTable(ListOfTables listOfTables) {
    int tableAssigned = listOfTables.checkAvailability(this.numPax, this.bookingDateTime);
    if (tableAssigned != -1) {
      setTableNumber(tableAssigned);
      return tableAssigned;
    }
    return -1;
  }

  /**
   * isExpired() checks if the bookingDateTime is expired, here we assumed a holding period of 15 minutes
   * @return boolean
   */
  public boolean isExpired() {
    if (this.bookingDateTime.plusMinutes(15).isBefore(LocalDateTime.now())) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Accessor method to get the bookingDateTIme
   * @return LocalDateTime
   */
  public LocalDateTime getBookingDateTime() {
    return this.bookingDateTime;
  }

  /**
   * Mutator method to set the bookingDateTime
   * @param dateTime
   */
  public void setBookingDateTime(LocalDateTime dateTime) {
    this.bookingDateTime = dateTime;
  }

  /**
   * Accessor method to get the name of the customer who made the reservation
   * @return String
   */
  public String getName() {
    return this.name;
  }

  /**
   * Mutator method to set the name
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Accessor method to get the contactNumber of the customer
   * @return int
   */
  public int getContactNumber() {
    return this.contactNumber;
  }

  /**
   * Mutator method to set the contactNumber
   * @param contactNumber
   */
  public void setContactNumber(int contactNumber) {
    this.contactNumber = contactNumber;
  }

  /**
   * Accessor method to get the number of pax booked for
   * @return int
   */
  public int getNumPax() {
    return this.numPax;
  }

  /**
   * Mutator method to set the numPax
   * @param numPax
   */
  public void setNumPax(int numPax) {
    this.numPax = numPax;
  }

  /**
   * Accessor method to get the tableNumber
   * @return int
   */
  public int getTableNumber() {
    return this.tableNumber;
  }
  
  /**
   * Mutator method to set the tableNumber
   * @param tableNumber
   */
  public void setTableNumber(int tableNumber) {
    this.tableNumber = tableNumber;
  }
  
  /**
  * Accessor method to get the isMember status
  * @return
  */
 public boolean getIsMember() {
   return this.isMember;
 }

 /**
  * Mutator method to set the isMember status
  * @param isMember
  */
 public void setIsMember(boolean isMember) {
   this.isMember = isMember;
 }
}