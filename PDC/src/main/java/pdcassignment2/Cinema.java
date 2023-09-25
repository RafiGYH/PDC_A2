/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 1 - Cinema Booking System
 * Cinema.Java - Abstract class responsible for the cinemas
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 */
package pdcassignment1;

public abstract class Cinema {

    private String cinemaName;
    private int totalSeats;
    int bookedSeats;

    public Cinema(String cinemaName, int totalSeats) {
        this.cinemaName = cinemaName;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
    }

    // Getters and Setters
    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }

    // Check if fully booked
    public boolean isFullyBooked() {
        return bookedSeats >= totalSeats;
    }

    // Check if seats are available
    public boolean hasAvailableSeats() {
        return getBookedSeats() < getTotalSeats();
    }

    // Get the booking limits
    public abstract int getBookingLimit();

    // Book number of seats
    public void bookSeats(int numSeats) throws BookingException {
        if (numSeats <= 0) {
            throw new BookingException("Invalid number of seats.");
        }

        if (bookedSeats + numSeats <= totalSeats) {
            bookedSeats += numSeats;
        } else {
            throw new BookingException("Not enough seats available.");
        }
    }
}
