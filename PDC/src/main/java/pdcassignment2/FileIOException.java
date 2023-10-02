/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * FileIOException.Java - Class responsible for the IOException extension
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

import java.io.IOException;

public class FileIOException extends IOException {

    public FileIOException(String message) {
        super(message);
    }
}
