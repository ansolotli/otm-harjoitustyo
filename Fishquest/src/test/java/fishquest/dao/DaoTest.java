
package fishquest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.After;
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
        score = new Score(-1, "", 0);
        Score newScore = new Score(-1, "AKK", 10);
        
        try {
            score = scoredao.save(newScore);
        } catch (Exception e) {
            System.out.println("Saving to database was not successful" + e.getMessage());
        }
        
        assertEquals("AKK: 10", score.toString());
    }
    
    @Test
    public void highScoreListIsCorrectSize() {
        
    }
    
    @After
    public void tearDown() {
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:tests.db");
            PreparedStatement stmt = conn.prepareStatement("DROP TABLE IF EXISTS HighScore");
            stmt.execute();
            stmt.close();
        } catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}    