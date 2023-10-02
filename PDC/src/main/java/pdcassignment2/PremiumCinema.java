/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * PremiumCinema.Java - Responislbe for the premium ticket bookings
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

public class PremiumCinema extends Cinema {

    private int totalPremiumSeats;
    private int bookedPremiumSeats;

    public PremiumCinema(String cinemaName, int totalStandardSeats, int totalPremiumSeats) {
        super(cinemaName, totalStandardSeats + totalPremiumSeats);
        this.totalPremiumSeats = totalPremiumSeats;
        this.bookedPremiumSeats = 0;
    }

    //getters and setters
    public int getTotalPremiumSeats() {
        return totalPremiumSeats;
    }

    public int getBookedPremiumSeats() {
        return bookedPremiumSeats;
    }

    public int getAvailablePremiumSeats() {
        return totalPremiumSeats - bookedPremiumSeats;
    }

    // Book seat numbers
    public void bookPremiumSeats(int numSeats) throws BookingException {
        if (numSeats <= 0) {
            throw new BookingException("Invalid number of seats.");
        }

        if (bookedPremiumSeats + numSeats <= totalPremiumSeats) {
            bookedPremiumSeats += numSeats;
        } else {
            throw new BookingException("Not enough available premium seats.");
        }
    }

    @Override
    public int getBookingLimit() {
        return 145; // Total Booking limit for premium cinema (C3)
    }

    @Override
    public void bookSeats(int numSeats) throws BookingException {
        if (numSeats <= 0) {
            throw new BookingException("Invalid number of seats.");
        }

        if (bookedSeats + numSeats <= getTotalSeats()) {
            bookedSeats += numSeats;
        } else {
            throw new BookingException("Not enough available seats.");
        }
    }
}
