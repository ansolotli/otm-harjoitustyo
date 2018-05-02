
package fishquest.logics;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class extends MovingShape and creates fish.
 */
public class Fish extends MovingShape {
    
    protected boolean alive;

    /**
     * Constructor parameters extend MovingShape and AnyShape.
     * 
     * @param x
     * @param y 
     * 
     * Constructor fills a shape with a random colour.
     * It also sets boolean alive to be true.
     */
    public Fish(int x, int y) {
        super(new Polygon(0, 0, 0, 20, 20, 10, 40, 0, 60, 10, 40, 20), x, y);
        super.motion = new Point2D(1, 0);
        super.shape.setFill(generateRandomColor());
        this.alive = true;
    }
    
    /**
     * Method generates a colour based on random values for red, green and blue.
     * @return Color
     */
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
