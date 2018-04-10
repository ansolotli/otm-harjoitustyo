
package fishquest.logics;

import fishquest.gui.GameApplication;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public abstract class MovingShape extends AnyShape {
    
    protected Point2D motion;
    
    public MovingShape(Polygon polygon, int x, int y) {
        super(polygon, x, y);
        
        this.motion = new Point2D(0, 0);
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
}