
package fishquest.logics;

import fishquest.gui.GameApplication;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;


public abstract class ShapeForm {
    
    private final Polygon shape;
    protected Point2D motion;
    
    public ShapeForm(Polygon polygon, int x, int y) {
        this.shape = polygon;
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
        
        this.motion = new Point2D(0, 0);
    }
    
    public Polygon getShape() {
        return this.shape;
    }
    
    public void move() {
        shape.setTranslateX(shape.getTranslateX() + this.motion.getX());
        shape.setTranslateY(shape.getTranslateY() + this.motion.getY());
        
        if (this.shape.getTranslateX() < 0) {
            this.shape.setTranslateX(this.shape.getTranslateX() + GameApplication.WIDTH);
        }

        if (this.shape.getTranslateX() > GameApplication.WIDTH) {
            this.shape.setTranslateX(this.shape.getTranslateX() % GameApplication.WIDTH);
        }

        if (this.shape.getTranslateY() < 0) {
            this.shape.setTranslateY(this.shape.getTranslateY() + GameApplication.HEIGHT);
        }

        if (this.shape.getTranslateY() > GameApplication.HEIGHT) {
            this.shape.setTranslateY(this.shape.getTranslateY() % GameApplication.HEIGHT);
        }
    }
    
    public boolean collidesWith(ShapeForm shape2) {
        Shape collisionArea = Shape.intersect(this.shape, shape2.getShape());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }
    
}
