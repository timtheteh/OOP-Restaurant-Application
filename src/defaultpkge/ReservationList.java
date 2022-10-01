
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.*;

/**
 * The ReservationList class provides and manages data for each individual reservation in the restaurant.
 * @author Timothy Teh
 *
 */



public class ReservationList {

  /**
   * The list of reservations in restaurant.
   */
  private ArrayList<Reservation> listOfReservation = new ArrayList<Reservation>();
  
  /**
   * The list of tables in the restaurant.
   */
  private ListOfTables listOfTables;
  
  /**
   * Constructs the reservation list in the restaurant.
   * @param listOfTables the list of tables in the restaurant
   */
  public ReservationList(ListOfTables listOfTables) {
    this.listOfTables = listOfTables;
  }

  /**
   * Returns the list of reservations in the restaurant.
   * @return the list of reservations in the restaurant
   */
  public ArrayList<Reservation> getListOfReservations() {
    return (ArrayList<Reservation>) listOfReservation;
  }

  /**
   * Creates a new reservation, and adds it to the list of reservations in the restaurant.
   * Returns the table number assigned to the new reservation.
   * @param bookingDateTime       the date and time at which the reservation was made
   * @param name                  the name of the diner who made the reservation
   * @param contactNumber         the contact number of the diner who made the reservation
   * @param numPax                the number of diners included in the reservation
   * @param isMember              the status of whether any of the diners included in the reservation is a member of the restaurant
   * @return                      the table number assigned to the new reservation
   */
  public int createReservation(LocalDateTime bookingDateTime, String name, int contactNumber, int numPax, boolean isMember) {
    Reservation reservation = new Reservation (bookingDateTime, name, contactNumber, numPax, isMember);
    if (reservation.allocateTable(listOfTables) != -1) {
      listOfReservation.add(reservation);
      return reservation.getTableNumber();
    }
    else {
      return 0;
    }
  }

   /**
   * Removes the reservation which corresponds to the specified contact number, from the list of reservations.
   * @param contactNumber  the contact number of the diner who made the reservation
   * @return               the failure or success status of the reservation removal
   */
  public boolean removeReservation(int contactNumber) {
    for (Reservation res : listOfReservation) {
      if (res.getContactNumber() == contactNumber) {
        listOfReservation.remove(res);
        return true;
      }
      else {
        continue;
      }
    }
    return false;
  }
  
  /**
   * Removes all expired reservations from the list of reservations.
   * @return the failure or success status of the expired reservations removal
   */
  public boolean removeAllExpiredReservations() {
    Reservation toRemove = null;
    for (Reservation res : listOfReservation) {
      if (res.isExpired()) {
        toRemove = res;
        break;
      }
      else {
        continue;
      }
    }
    if (toRemove != null) {
      listOfReservation.remove(toRemove);
      return true;
    }
    return false;
  }

  /**
   * Checks if the diner of the specified contact number has an existing reservation with the restaurant.
   * @param contactNumber  the contact number of the diner who made the reservation
   * @return               the reservation which corresponds to the specified contact number
   */
  public Reservation checkReservation(int contactNumber) {
    for (Reservation res : listOfReservation) {
      if (res.getContactNumber() == contactNumber) {
        return res;
      }
      else {
        continue;
      }
    }
    return null;
  }
}