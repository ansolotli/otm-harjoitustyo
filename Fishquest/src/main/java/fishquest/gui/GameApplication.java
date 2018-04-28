package fishquest.gui;

import fishquest.dao.Database;
import fishquest.dao.ScoreDao;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameApplication extends Application {

    ScoreDao scoreDao;

    @Override
    public void init() throws Exception {
        Database database = new Database("jdbc:sqlite:scores.db");
        database.init();

        scoreDao = new ScoreDao(database);
    }

    public static int gameWIDTH = 800;
    public static int gameHEIGHT = 400;

    Random random = new Random();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FishQuest");

        //StartView
        Text gameText = new Text("FISHQUEST");
        Label nameText = new Label("Enter your intials:");
        TextField nameField = new TextField();

        Button startButton = new Button("Start");

        GridPane startLayout = new GridPane();

        startLayout.add(gameText, 2, 0);
        startLayout.add(nameText, 2, 2);
        startLayout.add(nameField, 2, 3);
        startLayout.add(startButton, 2, 5);

        startLayout.setHgap(10);
        startLayout.setVgap(10);
        startLayout.setPadding(new Insets(10, 10, 10, 10));

        Scene startView = new Scene(startLayout);

        primaryStage.setScene(startView);

        String playersName = nameField.getText();

        //GameView
        Pane gameLayout = new Pane();
        gameLayout.setPrefSize(gameWIDTH, gameHEIGHT);

        Text pointCounter = new Text(15, 30, "Points: 0");
        pointCounter.setFont(new Font(20));
        gameLayout.getChildren().add(pointCounter);
        AtomicInteger points = new AtomicInteger();

        Scene gameView = new Scene(gameLayout);

        Boat boat = new Boat(gameWIDTH / 2, gameHEIGHT / 2);
        gameLayout.getChildren().add(boat.getShape());

        List<Fish> listOfFish = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Fish fish = new Fish(random.nextInt(gameWIDTH / 3), random.nextInt(gameHEIGHT));
            listOfFish.add(fish);
        }

        listOfFish.forEach(fish -> gameLayout.getChildren().add(fish.getShape()));

        List<Rock> listOfRocks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Rock rock = new Rock(random.nextInt(gameWIDTH - 10), random.nextInt(gameHEIGHT - 10));
            if (!rock.collidesWith(boat)) {
                listOfRocks.add(rock);
            }
        }
        listOfRocks.forEach(rock -> gameLayout.getChildren().add(rock.getShape()));

        //ScoreView
        Text scoreText = new Text("HIGHSCORE");

        Button newGameButton = new Button("New game");
        Button changePlayerButton = new Button("Change player");

        ListView<String> highScoreList = new ListView<String>();
        ObservableList<String> list = FXCollections.observableArrayList();

        scoreDao.displayHighScoreByPoints().stream().forEach(s -> {
            list.add(s.toString());
            highScoreList.setItems(list);
        });
        //EI TOIMI!

        GridPane scoreLayout = new GridPane();

        scoreLayout.add(scoreText, 2, 0);
        scoreLayout.add(highScoreList, 2, 2);
        scoreLayout.add(newGameButton, 2, 4);
        scoreLayout.add(changePlayerButton, 2, 6);

        scoreLayout.setHgap(10);
        scoreLayout.setVgap(10);
        scoreLayout.setPadding(new Insets(10, 10, 10, 10));

        Scene scoreView = new Scene(scoreLayout);

        
        
        
        Map<KeyCode, Boolean> keysPressed = new HashMap<>();

        gameView.setOnKeyPressed(e -> {
            keysPressed.put(e.getCode(), Boolean.TRUE);
        });
        gameView.setOnKeyReleased(e -> {
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
                        .forEach(fish -> gameLayout.getChildren().remove(fish.getShape()));
                listOfFish.removeAll(listOfFish.stream()
                        .filter(fish -> !fish.isAlive())
                        .collect(Collectors.toList())
                );

                listOfRocks.forEach(rock -> {
                    if (boat.collidesWith(rock)) {
                        //save score
                        //stop();
                    }
                });

                if (Math.random() < 0.010) {
                    Fish fish = new Fish(random.nextInt(gameWIDTH / 4), random.nextInt(gameHEIGHT));
                    if (!fish.collidesWith(boat)) {
                        listOfFish.add(fish);
                        gameLayout.getChildren().add(fish.getShape());
                    }
                }
            }
        }.start();

//        stage.setOnCloseRequest(e -> {
//            e.consume();
//            stop();
//        });
        startButton.setOnAction((event) -> {
            primaryStage.setScene(gameView);
        });

        newGameButton.setOnAction((event) -> {
            primaryStage.setScene(gameView);
        });

        changePlayerButton.setOnAction((event) -> {
            primaryStage.setScene(startView);
        });

        primaryStage.show();
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
