
package fishquest.logics;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Fish extends MovingShape {
    
    protected boolean alive;

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
    
    public boolean isAlive() {
        return this.alive;
    }
    
    public void setAlive(boolean status) {
        this.alive = status;
    }
}
