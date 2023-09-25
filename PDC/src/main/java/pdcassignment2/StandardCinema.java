/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 1 - Cinema Booking System
 * StandardCinema.Java - Responsible for extending the abstract class CInema and implementing the standard cinema option
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 */
package pdcassignment1;

public class StandardCinema extends Cinema {

    public StandardCinema(String cinemaName, int totalStandardSeats) {
        super(cinemaName, totalStandardSeats);
    }

    @Override
    public int getBookingLimit() {
        if (getCinemaName().equalsIgnoreCase("Cinema 1")) {
            return 60;
        } else if (getCinemaName().equalsIgnoreCase("Cinema 2")) {
            return 85;
        } else {
            throw new IllegalArgumentException("Unknown cinema: " + getCinemaName());
        }
    }

    @Override
    public void bookSeats(int numSeats) throws BookingException {
        if (numSeats <= 0) {
            throw new BookingException("Invalid number of seats.");
        }

        if (getBookedSeats() + numSeats <= getTotalSeats()) {
            setBookedSeats(getBookedSeats() + numSeats);
        } else {
            throw new BookingException("Not enough seats available.");
        }
    }
}
