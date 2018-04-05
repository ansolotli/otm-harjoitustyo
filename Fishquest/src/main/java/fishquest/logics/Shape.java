
package fishquest.logics;

import fishquest.gui.GameApplication;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;


public class Shape {
    
    private Polygon shape;
    private Point2D motion;
    
    public Shape(Polygon polygon, int x, int y) {
        this.shape = polygon;
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
        
        this.motion = new Point2D(0, 0);
    }
    
    public Polygon getShape() {
        return this.shape;
    }
    
    public void right() {
        this.motion = new Point2D(this.motion.getX()+1, this.motion.getY());
    }
    
    public void left() {
        this.motion = new Point2D(this.motion.getX()-1, this.motion.getY());
    }
    
    public void up() {
        this.motion = new Point2D(this.motion.getX(), this.motion.getY()-1);
    }
    
    public void down() {
        this.motion = new Point2D(this.motion.getX(), this.motion.getY()+1);
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
    
    //liikkeen hidastaminen
    //törmääminen
    
}
