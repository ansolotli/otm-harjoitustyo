
package fishquest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        score = new Score(-1, "AKK", 10);
        Score score2 = new Score(-1, "VPK", 19);
        Score score3 = new Score(-1, "EVE", 6);
        
        try {
            scoredao.save(score);
            scoredao.save(score2);
            scoredao.save(score3);
        } catch (Exception e) {
            System.out.println("Saving to database was not successful" + e.getMessage());
        }
        
        List<Score> testlist = new ArrayList<>();
        
        try {
            testlist = scoredao.displayHighScoreByPoints();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        assertEquals(testlist.size(), 3);
    }
    
    @Test
    public void highScoreListIsInCorrectOrder() {
        score = new Score(-1, "AKK", 10);
        Score score2 = new Score(-1, "VPK", 19);
        Score score3 = new Score(-1, "EVE", 6);
        
        try {
            scoredao.save(score);
            scoredao.save(score2);
            scoredao.save(score3);
        } catch (Exception e) {
            System.out.println("Saving to database was not successful" + e.getMessage());
        }
        
        List<Score> testlist = new ArrayList<>();
        
        try {
            testlist = scoredao.displayHighScoreByPoints();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        Score testScore = testlist.get(0);
        
        assertEquals("VPK: 19", testScore.toString());
    }
    
    @Test
    public void HighScoreListIsInCorrectOrder2() {
        score = new Score(-1, "AKK", 10);
        Score score2 = new Score(-1, "VPK", 19);
        Score score3 = new Score(-1, "EVE", 6);
        
        try {
            scoredao.save(score);
            scoredao.save(score2);
            scoredao.save(score3);
        } catch (Exception e) {
            System.out.println("Saving to database was not successful" + e.getMessage());
        }
        
        List<Score> testlist = new ArrayList<>();
        
        try {
            testlist = scoredao.displayHighScoreByPoints();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        Score testScore = testlist.get(2);
        
        assertEquals("EVE: 6", testScore.toString());
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