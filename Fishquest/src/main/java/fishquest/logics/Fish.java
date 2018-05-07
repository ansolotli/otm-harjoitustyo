
package fishquest.logics;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class extends MovingShape and creates fish shapes.
 */
public class Fish extends MovingShape {
    
    protected boolean alive;

    /**
     * Constructor of the class Fish
     * 
     * @param x - x coordinate used for placing the fish on the game field
     * @param y - y coordinate used for placing the fish on the game field
     * 
     * Constructor fills a created shape with a random colour ands sets its boolean variable to true
     */
    public Fish(int x, int y) {
        super(new Polygon(0, 0, 0, 20, 20, 10, 40, 0, 60, 10, 40, 20), x, y);
        super.motion = new Point2D(1, 0);
        super.shape.setFill(generateRandomColor());
        this.alive = true;
    }
    
    private Color generateRandomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b);
    }
    
    /**
     * Method checks boolean alive which tells whether or not the fish is alive
     * @return true is fish is alive, otherwise false
     */
    public boolean isAlive() {
        return this.alive;
    }
    
    /**
     * Method sets status of boolean alive
     * @param status - true or false depending on whether or not a fish has died in the game application
     */
    public void setAlive(boolean status) {
        this.alive = status;
    }
}
