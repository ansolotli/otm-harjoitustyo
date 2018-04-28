
package fishquest.dao;

import fishquest.logics.Score;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class DaoTest {
    
    Database database;
    ScoreDao scoredao;
    Score score;
    
    @Before
    public void setUp() throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:tests.db")) {
            if (conn != null) {
                System.out.println("New database created");
                
                PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS HighScore "
                        + "(id integer PRIMARY KEY, name varchar(3), points integer)");
                stmt.execute();
                stmt.close();
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        database = new Database("jdbc:sqlite:tests.db");
        scoredao = new ScoreDao(database);
    }
    
    @Test
    public void savingIsSuccessful() {
        Score score = new Score(-1, "AKK", 10);
        Score score2 = new Score(-1, "", 0);
        
        try {
            scoredao.save(score);
        } catch (Exception e) {
            System.out.println("Saving to database was not successful");
        }
        
        try{
            Connection conn = database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM HighScore WHERE name = 'AKK'");
        
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                score2 = new Score(rs.getInt("id"), rs.getString("name"), rs.getInt("points"));
                rs.close();
                stmt.close();
                conn.close();
            
            } else {
                rs.close();
                stmt.close();
                conn.close();
            }
            
        } catch (Exception e) {
            System.out.println("Connection could not be formed");
        }
        
        assertEquals("AKK: 10", score2.toString());
    }
}    