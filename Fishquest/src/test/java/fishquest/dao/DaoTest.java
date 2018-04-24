
package fishquest.dao;

import fishquest.logics.Score;
import org.junit.Before;
import org.junit.Test;

public class DaoTest {
    
    Database database;
    ScoreDao scoredao;
    Score score;
    
    @Before
    public void setUp() throws Exception {
        database = new Database("jdbc:sqlite:scores.db");
        scoredao = new ScoreDao(database);
        score = new Score(1, "AKK", 10);
        
        database.init();
    }
    
}    