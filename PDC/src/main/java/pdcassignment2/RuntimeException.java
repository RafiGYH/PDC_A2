/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * RuntimeException.Java - Responsible for the RuntimeException
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

public class RuntimeException extends Throwable {

    private String message;

    public RuntimeException(String message) {
        this.message = message;
    }

    public RuntimeException(String message, Throwable cause) {
        super(cause);  // Pass the cause to the superclass constructor
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
