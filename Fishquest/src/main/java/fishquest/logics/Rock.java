
package fishquest.logics;

import javafx.scene.shape.Polygon;

public class Rock extends AnyShape {
    
    public Rock(int x, int y) {
        super(new Polygon(10, 10, 20, 30, 0, 30), x, y);
    }
    
}
