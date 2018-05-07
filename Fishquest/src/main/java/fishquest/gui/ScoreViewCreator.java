
package fishquest.gui;

import fishquest.dao.ScoreDao;
import fishquest.dao.Score;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ScoreViewCreator {
    
    ScoreDao dao;
//    Button newGameButton;
//    Button newPlayerButton;
    
    ListView<String> highScoreList;
    
    public ScoreViewCreator(ScoreDao dao) {
        this.dao = dao;
    }
    
    public Scene createScoreView() {
        Text scoreText = new Text("HIGHSCORE");

//        newGameButton = new Button("New game");
//        newPlayerButton = new Button("New player");

        highScoreList = new ListView<>();
        ObservableList<String> list = FXCollections.observableArrayList();

        findHighScoreByPoints().stream().forEach(s -> { 
                list.add(s.toString());
                highScoreList.setItems(list);
            });

        GridPane scoreLayout = new GridPane();

        scoreLayout.add(scoreText, 0, 0);
        scoreLayout.add(highScoreList, 0, 2);
//        scoreLayout.add(newGameButton, 0, 4);
//        scoreLayout.add(newPlayerButton, 0, 5);

        scoreLayout.setHgap(10);
        scoreLayout.setVgap(10);
        scoreLayout.setPadding(new Insets(10, 10, 10, 10));

        Scene scoreView = new Scene(scoreLayout);
        return scoreView;
    }
    
    private List<Score> findHighScoreByPoints() {
        List<Score> results = new ArrayList<>();
        
        try{
            results = dao.displayHighScoreByPoints();
        } catch(Exception e) {
            System.out.println("Could not search or did not find results: " + e.getMessage());
        }
        
        return results;
    }
    
//    public Button getNewGameButton() {
//        return this.newGameButton;
//    }
//    
//    public Button getNewPlayerButton() {
//        return this.newPlayerButton;
//    }
}
