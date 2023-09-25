/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 1 - Cinema Booking System
 * FileIOReadFile.Java - Responsible for reading the files contents and display it to the user
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 *///
package pdcassignment1;

import java.io.*;
import java.util.*;

public class FileIORead {

// Find a booking by phone number
    public static Booking findBookingByPhoneNumber(String phoneNumberInput) {
        List<Booking> bookings = new ArrayList<>();
        try (
                 FileReader read = new FileReader("fileInput.txt");  BufferedReader readerFile = new BufferedReader(read)) {
            String fileText;
            while ((fileText = readerFile.readLine()) != null) {
                Booking booking = Booking.fromString(fileText);
                if (booking != null) {
                    bookings.add(booking);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        }

        for (Booking booking : bookings) {
            if (booking.getPhoneNumber().equals(phoneNumberInput)) {
                return booking;
            }
        }
        return null;
    }
}
