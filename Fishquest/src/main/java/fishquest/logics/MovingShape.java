
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
     * Constructor of the interface MovingShape
     * 
     * @param polygon
     * @param x - x coordinate used for placing the shape on the game field
     * @param y - y coordinate used for placing the shape on the game field
     * 
     * MovingShape has a Point2D point with x and y coordinates that represents movement.
     */
    public MovingShape(Polygon polygon, int x, int y) {
        super(polygon, x, y);
        
        this.motion = new Point2D(0, 0);
    }
    
    /**
     * Method moves shapes in the game by increasing the distance their coordinates are translated along the axes.
     */
    public void move() {
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
