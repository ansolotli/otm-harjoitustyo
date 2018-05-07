
package fishquest.dao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreTest {
    
   Score score;
   
   @Before
   public void setUp() {
       score = new Score(1, "AKK", 10);
   }
   
   @Test
   public void getIdReturnsCorrectId() {
       assertEquals(1, (int) score.getId());
   }
   
   @Test
   public void getNameReturnsCorrectName() {
       assertEquals("AKK", score.getName());
   }
   
   @Test
   public void getPointsReturnsCorrectNumber() {
       assertEquals(10, (int) score.getPoints());
   }
   
   @Test
   public void toStringReturnsCorrectString() {
       assertEquals("AKK: 10", score.toString());
   }
}
