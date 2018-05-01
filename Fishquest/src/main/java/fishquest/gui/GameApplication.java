package fishquest.gui;

import fishquest.dao.Database;
import fishquest.dao.ScoreDao;
import fishquest.logics.Boat;
import fishquest.logics.Fish;
import fishquest.logics.Rock;
import fishquest.logics.Score;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class GameApplication extends Application {

    //pelinäkymä pitää nollata, kun peli päättyy
    //aloitusnäkymässä Enterin painaminen ei aloita peliä
    //Peliruutu liian sivussa
    
    ScoreDao scoreDao;

    @Override
    public void init() throws Exception {
        Database database = new Database("jdbc:sqlite:scores.db");
        database.init();

        scoreDao = new ScoreDao(database);
    }

    public static int gameWIDTH = 800;
    public static int gameHEIGHT = 400;

    Scene startView;
    Scene scoreView;
    Scene gameView;

    Random random = new Random();

    String playersName;
    int points = 0;

    Boat boat;
    List<Fish> listOfFish;
    List<Rock> listOfRocks;

    Map<KeyCode, Boolean> keysPressed;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FishQuest");

        StartViewCreator startViewCreator = new StartViewCreator();
        startView = startViewCreator.createStartView();

        ScoreViewCreator scoreViewCreator = new ScoreViewCreator(scoreDao);
        scoreView = scoreViewCreator.createScoreView();

        GameViewCreator gameViewCreator = new GameViewCreator();
        gameView = gameViewCreator.createGameView();

        boat = gameViewCreator.getBoat();
        listOfFish = gameViewCreator.getFish();
        listOfRocks = gameViewCreator.getRocks();

        keysPressed = gameViewCreator.getKeysPressed();

        primaryStage.setScene(startView);

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
                        points++;
                    }
                    if (!fish.isAlive()) {
                        gameViewCreator.setPointCounterText(points);
                    }
                });

                listOfFish.stream()
                        .filter(fish -> !fish.isAlive())
                        .forEach(fish -> gameViewCreator.removeFish(fish));
                listOfFish.removeAll(listOfFish.stream()
                        .filter(fish -> !fish.isAlive())
                        .collect(Collectors.toList())
                );

                listOfRocks.forEach(rock -> {
                    if (boat.collidesWith(rock)) {
                        playersName = startViewCreator.getPlayersName();
                        Score score = new Score(-1, playersName, points);

                        try {
                            if (points != 0) {
                                scoreDao.save(score);
                            }
                        } catch (Exception e) {
                            System.out.println("Something went wrong while saving");
                        }

                        stop();

                        points = 0;
                        scoreView = scoreViewCreator.createScoreView();
                        primaryStage.setScene(scoreView);
                    }
                });

                if (Math.random() < 0.010) {
                    Fish fish = new Fish(random.nextInt(gameWIDTH / 4), random.nextInt(gameHEIGHT));
                    if (!fish.collidesWith(boat)) {
                        listOfFish.add(fish);
                        gameViewCreator.addFish(fish);
                    }
                }
            }
        }.start();

//        stage.setOnCloseRequest(e -> {
//            e.consume();
//            stop();
//        });
        startViewCreator.getStartButton().setOnAction((event) -> {

            gameView = gameViewCreator.createGameView();
            boat = gameViewCreator.getBoat();
            listOfFish = gameViewCreator.getFish();
            listOfRocks = gameViewCreator.getRocks();

            keysPressed = gameViewCreator.getKeysPressed();

            primaryStage.setScene(gameView);
        });

        scoreViewCreator.getNewGameButton().setOnAction((event) -> {
            
            gameView = gameViewCreator.createGameView();
            boat = gameViewCreator.getBoat();
            listOfFish = gameViewCreator.getFish();
            listOfRocks = gameViewCreator.getRocks();

            keysPressed = gameViewCreator.getKeysPressed();

            primaryStage.setScene(gameView);
        });

        scoreViewCreator.getChangePlayerButton().setOnAction((event) -> {
            
            startView = startViewCreator.createStartView();
            
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
