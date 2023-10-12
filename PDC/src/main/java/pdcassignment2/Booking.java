/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * Booking.Java - Responsible for assembly of user input
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

import java.sql.SQLException;

public class Booking {

    private String movieTitle;
    private String showTime;
    private String ticketType;
    private int ticketQuantity;
    private String fullName;
    private String phoneNumber;
    private String email;
    private double totalPrice;

    public Booking() {

    }

    public Booking(Menu menu, Movie movie, Show show, Cinema cinema, BookingCalculator bookingCalculator) throws BookingException {
        this.movieTitle = movie.getTitle();
        this.showTime = show.getTime();
        this.ticketType = bookingCalculator.getTicketType();
        this.ticketQuantity = bookingCalculator.getTotalTickets();
        this.fullName = menu.getFullName();
        this.phoneNumber = menu.getPhoneNumber();
        this.email = menu.getEmail();
        this.totalPrice = bookingCalculator.getTotalPrice();
    }


    public boolean createB() throws SQLException{
        try{
            DatabaseUtility.insertB(this);
            return true;
        }catch(DatabaseException e)
        {
            return false; //WE could add printStacktrace(); maybe
        }
    }

    public Booking readBooking(int id)
    {
        try{
            return DatabaseUtility.getBByID(id);
        }catch(DatabaseException e)
        {
            return null;
        }
    }

    public boolean updateBooking() throws SQLException{
        try{
            DatabaseUtility.updateBooking(this);
            return true;
        }catch(DatabaseException e)
        {
            return false;
        }
    }

    public boolean deleteBooking(int id) throws SQLException
    {
        try{
            DatabaseUtility.deleteBooking(id);
            return true;
        }catch(DatabaseException e)
        {
            return false;
        }
    }


    public String getMovieTitle() {
        return movieTitle;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getTicketType() {
        return ticketType;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setMovieTitle(String movieTitle)
    {
        this.movieTitle = movieTitle;
    }

    public void setShowTime(String showTime)
    {
        this.showTime = showTime;
    }

    public void setTicketQuantity(int ticketQuantity)
    {
        this.ticketQuantity = ticketQuantity;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public void setTicketType(String ticketType)
    {
        this.ticketType = ticketType;
    }

    @Override
    public String toString() {
        return "Here are the details\n ---------------\n"
                + " Name:     " + fullName + "\n "
                + "Number:   " + phoneNumber + "\n "
                + "Email:    " + email + "\n "
                + ticketQuantity + "x " + ticketType + " Tickets to " + movieTitle + " at " + showTime + "\n ";
    }

    public static Booking fromString(String str) {
        String[] parts = str.split(",");
        if (parts.length < 8) {
            return null;
        }
        Booking booking = new Booking();
        booking.fullName = parts[0].trim();
        booking.phoneNumber = parts[1].trim();
        booking.email = parts[2].trim();
        booking.showTime = parts[3].trim();
        booking.movieTitle = parts[4].trim();
        booking.ticketType = parts[5].trim();
        booking.ticketQuantity = Integer.parseInt(parts[6].trim());

        return booking;
    }
}
