/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * DatabaseUtilityTest.Java - Responsible for testing the database conection
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created October 2023
 */
package pdcassignment2;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DatabaseUtilityTest {

    // Checks if a database connection can be established.
    @Test
    public void testConnection() {
        DatabaseUtility dbUtil = new DatabaseUtility();
        try {
            assertNotNull(DatabaseUtility.connect(), "Database connection should not be null");
        } catch (DatabaseException ex) {
            Logger.getLogger(DatabaseUtilityTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}