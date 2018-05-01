
package fishquest.gui;

import static fishquest.gui.GameApplication.gameHEIGHT;
import static fishquest.gui.GameApplication.gameWIDTH;
import fishquest.logics.Boat;
import fishquest.logics.Fish;
import fishquest.logics.Rock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameViewCreator {
    
    Random random;
    
    Pane gameLayout;
    Boat boat;
    List<Fish> listOfFish;
    List<Rock> listOfRocks;
    Text pointCounter;
    
    Map<KeyCode, Boolean> keysPressed;
    
    public GameViewCreator() {
        this.random = new Random();
    }
    
    public Scene createGameView() {
        gameLayout = new Pane();
        gameLayout.setPrefSize(gameWIDTH, gameHEIGHT);

        pointCounter = new Text(15, 30, "Points: 0");
        pointCounter.setFont(new Font(20));
        gameLayout.getChildren().add(pointCounter);

        boat = new Boat(gameWIDTH / 2, gameHEIGHT / 2);
        gameLayout.getChildren().add(boat.getShape());

        listOfFish = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Fish fish = new Fish(random.nextInt(gameWIDTH / 3), random.nextInt(gameHEIGHT));
            listOfFish.add(fish);
        }

        listOfFish.forEach(fish -> gameLayout.getChildren().add(fish.getShape()));

        listOfRocks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Rock rock = new Rock(random.nextInt(gameWIDTH - 10), random.nextInt(gameHEIGHT - 10));
            if (!rock.collidesWith(boat)) {
                listOfRocks.add(rock);
            }
        }
        listOfRocks.forEach(rock -> gameLayout.getChildren().add(rock.getShape()));
        
        keysPressed = new HashMap<>();
        
        Scene gameView = new Scene(gameLayout);
        
        gameView.setOnKeyPressed(e -> {
            keysPressed.put(e.getCode(), Boolean.TRUE);
        });
        gameView.setOnKeyReleased(e -> {
            keysPressed.put(e.getCode(), Boolean.FALSE);
        });
        
        return gameView;
    }
    
    public Boat getBoat() {
        return this.boat;
    }
    
    public List<Fish> getFish() {
        return this.listOfFish;
    }
    
    public List<Rock> getRocks() {
        return this.listOfRocks;
    }
    
    public void setPointCounterText(int points) {
        pointCounter.setText("Points: " + points);
    }
    
    public void removeFish(Fish fish) {
        gameLayout.getChildren().remove(fish.getShape());
    }
    
    public void addFish(Fish fish) {
        gameLayout.getChildren().add(fish.getShape());
    }
    
    public Map<KeyCode, Boolean> getKeysPressed() {
        return this.keysPressed;
    }
}
