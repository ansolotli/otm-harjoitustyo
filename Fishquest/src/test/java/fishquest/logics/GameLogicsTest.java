
package fishquest.logics;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameLogicsTest {
    
    Boat boat;
    Fish fish;
    Rock rock;

    @Before
    public void setUp() {
        boat = new Boat(5, 5);
        fish = new Fish(5, 5);
        rock = new Rock(5, 5);
    }
    
    @Test
    public void twoShapesAtTheSamePlaceCollide() {
        assertTrue(boat.collidesWith(fish));
    }
    
    @Test
    public void twoShapesFarApartDontCollide() {
        boat = new Boat(500, 500);
        assertFalse(boat.collidesWith(fish));
    }
    
    //liikkuva ja liikkumaton hahmo; aluks ei törmää, lopulta törmää
}
