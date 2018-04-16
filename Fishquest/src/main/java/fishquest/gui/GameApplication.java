package fishquest.gui;

import fishquest.logics.Boat;
import fishquest.logics.Fish;
import fishquest.logics.Rock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameApplication extends Application {

    @Override
    public void init() {
        //tänne tietokannan alustus, daot yms.
    }

    public static int WIDTH = 800;
    public static int HEIGHT = 400;
    
    Random random = new Random();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Catch the fish!");
        Pane layout = new Pane();
        layout.setPrefSize(WIDTH, HEIGHT);
        
        Text pointCounter = new Text(15, 30, "Points: 0");
        pointCounter.setFont(new Font(20));
        layout.getChildren().add(pointCounter);
        AtomicInteger points = new AtomicInteger();
        
        Scene scene = new Scene(layout);
        
        Boat boat = new Boat(WIDTH / 2, HEIGHT / 2);
        layout.getChildren().add(boat.getShape());

        List<Fish> listOfFish = new ArrayList<>();
        for (int i = 0; i < 10; i++) { //10 kalaa testimäärä
            Fish fish = new Fish(random.nextInt(WIDTH / 3), random.nextInt(HEIGHT));
            listOfFish.add(fish);
        }
         //randomoi kalojen luominen
        listOfFish.forEach(fish -> layout.getChildren().add(fish.getShape()));
        
        List<Rock> listOfRocks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Rock rock = new Rock(random.nextInt(WIDTH-10), random.nextInt(HEIGHT-10));
            if (!rock.collidesWith(boat)) {
                listOfRocks.add(rock);
            }
        }
        listOfRocks.forEach(rock -> layout.getChildren().add(rock.getShape()));

        Map<KeyCode, Boolean> keysPressed = new HashMap<>();

        scene.setOnKeyPressed(e -> {
            keysPressed.put(e.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(e -> {
            keysPressed.put(e.getCode(), Boolean.FALSE);
        });

        new AnimationTimer() {

            private long lastUpdate = 0;

            @Override
            public void handle(long now) {

                if (now - lastUpdate < 8000000) {
                    return;
                }
                lastUpdate = now;
                
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
                listOfFish.forEach(fish -> fish.move());

                listOfFish.forEach(fish -> {
                    if (boat.collidesWith(fish)) {
                        fish.setAlive(false);
                    }
                    if (!fish.isAlive()) {
                        pointCounter.setText("Points: " + points.incrementAndGet());
                    }
                });
                
                listOfFish.stream()
                        .filter(fish -> !fish.isAlive())
                        .forEach(fish -> layout.getChildren().remove(fish.getShape()));
                listOfFish.removeAll(listOfFish.stream()
                        .filter(fish -> !fish.isAlive())
                        .collect(Collectors.toList())
                );
                
                listOfRocks.forEach(rock -> {
                    if (boat.collidesWith(rock)) {
                        stop();
                    }
                });

                if (Math.random() < 0.010) {
                    Fish fish = new Fish(random.nextInt(WIDTH / 4), random.nextInt(HEIGHT));
                    if (!fish.collidesWith(boat)) {
                        listOfFish.add(fish);
                        layout.getChildren().add(fish.getShape());
                    }
                }
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
