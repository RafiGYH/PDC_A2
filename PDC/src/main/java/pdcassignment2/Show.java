/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * Show.Java - Repsonsible for the creation of the shows, which includes a movie, time, cinema and information on seats
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

public class Show {

    private int showID; // Add this attribute
    private Movie movie;
    private String time;
    private Cinema cinema;
    private String cinemaType; // Add this attribute
    private int totalSeats;
    private int bookedSeats;

    public Show(Movie movie, String time, Cinema cinema, int totalSeats) {
        this.movie = movie;
        this.time = time;
        this.cinema = cinema;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
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

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public int getShowID() {
        return showID;
    }

    public void setCinemaType(String cinemaType) {
        this.cinemaType = cinemaType;
    }

    public String getCinemaType() {
        return cinemaType;
    }

    public int getAvailableTickets() {
        return totalSeats - bookedSeats;
    }

    public boolean hasAvailableSeats() {
        return bookedSeats < totalSeats; // Check if there are available seats
    }

    // Book a certain number of seats for the show
    public void bookSeats(int numSeats) throws BookingException {
        if (numSeats > cinema.getBookingLimit()) {
            throw new BookingException("Booking exceeds limit for this cinema.");
        }

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
        return this.bookedSeats >= this.cinema.getBookingLimit();
    }

    @Override
    public String toString() {
        return "Show Time: " + time + "\nCinema: " + cinema
                + "\nTotal Seats: " + totalSeats + "\nBooked Seats: " + bookedSeats
                + "\nAvailable Seats: " + getAvailableSeats();
    }
}
