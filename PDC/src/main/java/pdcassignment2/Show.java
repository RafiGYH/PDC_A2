/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 1 - Cinema Booking System
 * Show.Java - Repsonsible for the creation of the shows, which includes a movie, time, cinema and information on seats
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 */
package pdcassignment1;

public class Show {

    private final Movie movie;
    private final String time;
    private final Cinema cinema;
    private final int totalSeats;
    private int bookedSeats;

    public Show(Movie movie, String time, Cinema cinema, int totalSeats) {
        this.movie = movie;
        this.time = time;
        this.cinema = cinema;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
    }

    // Getter and setters
    public String getTime() {
        return time;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats; // Calculate and return available seats
    }

    public boolean hasAvailableSeats() {
        return bookedSeats < totalSeats; // Check if there are available seats
    }

    // Book a certain number of seats for the show
    public void bookSeats(int numSeats) throws BookingException {
        if (numSeats <= 0) {
            throw new BookingException("Invalid number of seats.");
        }

        if (bookedSeats + numSeats <= totalSeats && cinema.getAvailableSeats() >= numSeats) {
            bookedSeats += numSeats;
            cinema.bookSeats(numSeats); // Book seats in the relevent cinema
        } else {
            throw new BookingException("Not enough seats available.");
        }
    }

    // Get the booking limit based on the cinema
    public int getBookingLimit() throws BookingException {
        return cinema.getBookingLimit();
    }

    // Check if the show is fully booked
    public boolean isFullyBooked() {
        return bookedSeats >= totalSeats || !cinema.hasAvailableSeats();
    }

    @Override
    public String toString() {
        return "Show Time: " + time + "\nCinema: " + cinema
                + "\nTotal Seats: " + totalSeats + "\nBooked Seats: " + bookedSeats
                + "\nAvailable Seats: " + getAvailableSeats();
    }
}
