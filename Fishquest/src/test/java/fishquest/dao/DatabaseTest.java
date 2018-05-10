package fishquest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {

    Database database;

    @Before
    public void SetUp() throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:tests.db")) {
            if (conn != null) {
                System.out.println("New database created");
                
                PreparedStatement stmt = conn.prepareStatement("DROP TABLE IF EXISTS HighScore");
                stmt.execute();
                
                PreparedStatement stmt2 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS HighScore "
                        + "(id integer PRIMARY KEY, name varchar(3), points integer)");
                stmt2.execute();
                stmt.close();
                stmt2.close();
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        database = new Database("jdbc:sqlite:tests.db");
    }
    
    @Test
    public void getConnectionDoesNotReturnNull() {
        try {
            assertFalse(database.getConnection().equals(null));
        } catch(Exception e) {
            System.out.println("Could not contact database: " + e.getMessage());
        }
    }
    
    @Test
    public void databaseAddressIsCorrect() {
        assertEquals("jdbc:sqlite:tests.db", database.getDatabaseAddress());
    }
}
