
package fishquest.gui;

import fishquest.logics.Boat;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameApplication extends Application {
    
    @Override
    public void init() {
        //tänne tietokannan alustus, daot yms.
    }
    
    public static int WIDTH = 800;
    public static int HEIGHT = 400;
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Catch the fish!");
        Pane layout = new Pane();
        layout.setPrefSize(WIDTH, HEIGHT);
        Scene scene = new Scene(layout);
        
        Boat boat = new Boat(WIDTH/2, HEIGHT/2);
        layout.getChildren().add(boat.getShape());
        
        //luo karit
        //luo kalat
        
        Map<KeyCode, Boolean> keysPressed = new HashMap<>();
        
        scene.setOnKeyPressed(e -> {
            keysPressed.put(e.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(e -> {
            keysPressed.put(e.getCode(), Boolean.FALSE);
        });
        
        new AnimationTimer() {
            
            @Override
            public void handle(long now) {
                
                if (keysPressed.getOrDefault(KeyCode.LEFT, false)) {
                    boat.left();
                }
                if (keysPressed.getOrDefault(KeyCode.RIGHT, false)) {
                    boat.right();
                }
                if (keysPressed.getOrDefault(KeyCode.UP, false)) {
                    boat.up();
                }
                if (keysPressed.getOrDefault(KeyCode.DOWN, false)) {
                    boat.down();
                }
                
                boat.move();
                
            }
        }.start();
        
//        stage.setOnCloseRequest(e -> {
//            e.consume();
//            stop();
//        });
        
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() {
        //tietokannan tallennus
        //ohjelman sulkeminen
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
