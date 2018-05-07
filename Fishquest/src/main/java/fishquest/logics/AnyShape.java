
package fishquest.logics;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Interface creates Polygon shapes and offers a method for detecting collisions.
 */
public abstract class AnyShape {
    
    protected final Polygon shape;
    
    /**
     * Constructor of the interface AnyShape 
     * @param polygon - a polygon shape defined by an array of x and y coordinates
     * @param x - a distance by which coordinates are translated along the X axis
     * @param y - a distance by which coordinates are translated along the Y axis
     */
    public AnyShape(Polygon polygon, int x, int y) {
        this.shape = polygon;
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
    }
    
    public Polygon getShape() {
        return this.shape;
    }
    
    /**
     * Method tells whether or not two shapes collide with each other.
     * @param shape2 
     * @return true if collision happens, otherwise false
     */
    public boolean collidesWith(AnyShape shape2) {
        Shape collisionArea = Shape.intersect(this.shape, shape2.getShape());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }
}