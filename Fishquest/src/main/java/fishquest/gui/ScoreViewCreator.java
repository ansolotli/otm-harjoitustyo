
package fishquest.gui;

import fishquest.dao.ScoreDao;
import fishquest.dao.Score;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Class creates the score view
 */
public class ScoreViewCreator {
    
    ScoreDao dao;
    
    ListView<String> highScoreList;
    
    /**
     * Constructor of the class ScoreView
     * @param dao - a DAO class that handles storing final scores into an SQL database
     */
    public ScoreViewCreator(ScoreDao dao) {
        this.dao = dao;
    }
    
    /**
     * Method creates the score view
     * @return Scene scoreView
     */
    public Scene createScoreView() {
        Text scoreText = new Text("HIGHSCORE");

        highScoreList = new ListView<>();
        ObservableList<String> list = FXCollections.observableArrayList();

        findHighScoreByPoints().stream().forEach(s -> { 
                list.add(s.toString());
                highScoreList.setItems(list);
            });

        GridPane scoreLayout = new GridPane();

        scoreLayout.add(scoreText, 0, 0);
        scoreLayout.add(highScoreList, 0, 2);

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
}
