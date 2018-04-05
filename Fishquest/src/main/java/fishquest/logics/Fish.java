
package fishquest.logics;

import javafx.scene.shape.Polygon;


public class Fish extends Shape {

    public Fish(int x, int y) {
        super(new Polygon(0, 0, 0, 20, 20, 10, 40, 0, 60, 10, 40, 20), x, y);
    }
    
}
