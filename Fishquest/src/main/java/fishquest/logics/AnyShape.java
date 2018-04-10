
package fishquest.logics;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class AnyShape {
    
    protected final Polygon shape;
    
    public AnyShape(Polygon polygon, int x, int y) {
        this.shape = polygon;
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
    }
    
    public Polygon getShape() {
        return this.shape;
    }
    
    public boolean collidesWith(AnyShape shape2) {
        Shape collisionArea = Shape.intersect(this.shape, shape2.getShape());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }
}