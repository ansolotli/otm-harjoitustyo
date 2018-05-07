
package fishquest.logics;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

/**
 * Class extends interface MovinShape and creates a boat.
 */
public class Boat extends MovingShape {
    
    /**
     * Constructor of the class Boat
     * @param x - x coordinate used for placing the boat on the game field
     * @param y  - y coordinate used for placing the boat on the game field
     */
    public Boat(int x, int y) {
        super(new Polygon(0, 30, 0, 35, 30, 40, 60, 35, 60, 30, 50, 30, 30, 0, 30, 30), x, y);
    }
    
    /**
     * Method moves the boat right on the X axis
     */
    public void right() {
        super.motion = new Point2D(super.motion.getX() + 1, super.motion.getY());
    }
    
    /**
     * Method moves the boat left on the X axis
     */
    public void left() {
        super.motion = new Point2D(super.motion.getX() - 1, super.motion.getY());
    }
    
    /**
     * Method moves the boat up on the Y axis
     */
    public void up() {
        super.motion = new Point2D(super.motion.getX(), super.motion.getY() - 1);
    }
    
    /**
     * Method moves the boat down on the Y axis
     */
    public void down() {
        super.motion = new Point2D(super.motion.getX(), super.motion.getY() + 1);
    }
    
    /**
     * Method overrides extended interface and slows down the boat's movement
     */
    @Override
    public void move() {
        super.motion = new Point2D(super.motion.getX() * 0.9, super.motion.getY() * 0.9);           
        super.move();
    }
}
