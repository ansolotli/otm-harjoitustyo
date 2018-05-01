
package fishquest.logics;

import fishquest.gui.GameApplication;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

/**
 * Interface extends AnyShape and creates Polygon shapes that are moving.
 */
public abstract class MovingShape extends AnyShape {
    
    protected Point2D motion;
    
    /**
     * 
     * @param polygon
     * @param x
     * @param y 
     * 
     * Point2D shittii
     */
    public MovingShape(Polygon polygon, int x, int y) {
        super(polygon, x, y);
        
        this.motion = new Point2D(0, 0);
    }
    
    /**
     * Method jotain
     */
    public void move() { //tsekkaa rajojen ylitys
        shape.setTranslateX(shape.getTranslateX() + this.motion.getX());
        shape.setTranslateY(shape.getTranslateY() + this.motion.getY());
        
        if (this.shape.getTranslateX() < 0) {
            this.shape.setTranslateX(this.shape.getTranslateX() + GameApplication.gameWIDTH);
        }

        if (this.shape.getTranslateX() > GameApplication.gameWIDTH) {
            this.shape.setTranslateX(this.shape.getTranslateX() % GameApplication.gameWIDTH);
        }

        if (this.shape.getTranslateY() < 0) {
            this.shape.setTranslateY(this.shape.getTranslateY() + GameApplication.gameHEIGHT);
        }

        if (this.shape.getTranslateY() > GameApplication.gameHEIGHT) {
            this.shape.setTranslateY(this.shape.getTranslateY() % GameApplication.gameHEIGHT);
        }
    }
}
