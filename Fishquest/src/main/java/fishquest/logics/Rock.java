
package fishquest.logics;

import javafx.scene.shape.Polygon;

/**
 * Class extends AnyShape and creates Rock shapes.
 */
public class Rock extends AnyShape {
    
    /**
     * Constructor of the class Rock
     * @param x - x coordinate used for placing the rock on the game field
     * @param y - y coordinate used for placing the rock on the game field
     */
    public Rock(int x, int y) {
        super(new Polygon(10, 10, 20, 30, 0, 30), x, y);
    }
    
}
