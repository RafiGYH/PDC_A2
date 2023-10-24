/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * ConfigLoader.Java - Repsonsible for configuring the database using the database.properties file
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created October 2023
 */
package pdcassignment2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {

    private Properties properties;

    public ConfigLoader(String fileName) throws IOException {
        properties = new Properties();
        try ( FileInputStream input = new FileInputStream(Paths.get("src/main", fileName).toFile())) {
            properties.load(input);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
