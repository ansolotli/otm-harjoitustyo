
package fishquest.logics;

import javafx.scene.shape.Polygon;


public class Boat extends Shape {
    
    public Boat(int x, int y) {
        super(new Polygon(25, 15, 10, 10, 0, 0, 50, 0, 40, 10), x, y);
    }
    
}
