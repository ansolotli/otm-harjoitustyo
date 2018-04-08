
package fishquest.logics;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;


public class Boat extends Shape {
    
    public Boat(int x, int y) {
        //super(new Polygon(25, 15, 10, 10, 0, 0, 50, 0, 40, 10), x, y);
        //super(new Polygon(40, 20, 20, 15, 0, 0, 80, 0, 60, 15), x, y);
        //super(new Polygon(20, 30, 20, 0, 40, 15), x, y); 
        super(new Polygon(0, 30, 0, 35, 30, 40, 60, 35, 60, 30, 50, 30, 30, 0, 30, 30), x, y);
    }
    
    public void right() {
        super.motion = new Point2D(super.motion.getX()+1, super.motion.getY());
    }
    
    public void left() {
        super.motion = new Point2D(super.motion.getX()-1, super.motion.getY());
    }
    
    public void up() {
        super.motion = new Point2D(super.motion.getX(), super.motion.getY()-1);
    }
    
    public void down() {
        super.motion = new Point2D(super.motion.getX(), super.motion.getY()+1);
    }
    
    @Override
    public void move() {
        super.motion = new Point2D(super.motion.getX() * 0.9, super.motion.getY() * 0.9);           
        super.move();
    }
    
}
