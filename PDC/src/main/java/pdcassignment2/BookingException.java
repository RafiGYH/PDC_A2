/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * BookingException.Java - Extends RuntimeException
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

// BookingException class (Custom exception for booking-related errors)
public class BookingException extends RuntimeException {

    public BookingException(String message) {
        super(message);
    }
}
