
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
    public void twoThingsAtSamePlaceCollide() {
        assertTrue(boat.collidesWith(fish));
    }
    
    @Test
    public void twoThingsFarApartDontCollide() {
        boat = new Boat(500, 500);
        assertFalse(boat.collidesWith(fish));
    }
    
    @Test
    public void runningIntoRockStopsGame() {
        
    }
}
