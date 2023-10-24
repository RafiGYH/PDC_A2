/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * DatabaseUtilityTest.Java - Responsible for testing the database conection
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created October 2023
 */
package pdcassignment2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DatabaseUtilityTest {

    @Test
    public void testGetInstance() {
        try {
            DatabaseUtility dbUtil = DatabaseUtility.getInstance();
            assertNotNull(dbUtil, "DatabaseUtility instance should not be null");
        } catch (DatabaseException e) {
            fail("Getting DatabaseUtility instance should not throw an exception");
        }
    }

    @Test
    public void testConnection() {
        try {
            DatabaseUtility dbUtil = DatabaseUtility.getInstance();
            assertNotNull(dbUtil, "DatabaseUtility instance should not be null");
            
            assertNotNull(dbUtil.getAllMovies(), "Movies list should not be null");
        } catch (DatabaseException e) {
            fail("Getting movies list should not throw an exception");
        }
    }
}