/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 1 - Cinema Booking System
 * FileIOWriteintoFile.Java - Responsible for writing the user's input data into a set file
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 */
package pdcassignment1;

import java.io.*;
import java.util.*;

public class FileIOWrite {

    private final String bookingFilePath = "fileInput.txt";

    // Write a new booking to the file method, use buffer and fileW
    public void makeBooking(Booking bookings) {

        int convertedPrice = (int) bookings.getTotalPrice();

        try ( BufferedWriter buffer = new BufferedWriter(new FileWriter(bookingFilePath, true))) {
            buffer.write(bookings.getFullName() + " , ");
            buffer.write(bookings.getPhoneNumber() + " , ");
            buffer.write(bookings.getEmail() + " , ");
            buffer.write(bookings.getShowTime() + " , ");
            buffer.write(bookings.getMovieTitle() + " , ");
            buffer.write(bookings.getTicketType() + " , ");
            buffer.write(bookings.getTicketQuantity() + " , ");
            buffer.write(convertedPrice + "$");
            buffer.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("File not written, error occured");
        }
    }

}
