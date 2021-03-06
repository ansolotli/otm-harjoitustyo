package fishquest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class creates an SQL-database used in the game application.
 */
public class Database {

    private final String databaseAddress;

    /**
     * Constructor of the class Database
     * @param databaseAddress
     * @throws Exception 
     */
    public Database(String databaseAddress) throws Exception {
        this.databaseAddress = databaseAddress;
    }

    public String getDatabaseAddress() {
        return this.databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    /**
     * Method initialises the database and creates a database table HighScore if it does not already exist
     */
    public void init() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:scores.db")) {
            if (conn != null) {
                System.out.println("New database created");

                PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS HighScore "
                        + "(id integer PRIMARY KEY, name varchar(3), points integer)");
                stmt.execute();
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
