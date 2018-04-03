
package fishquest.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameApplication extends Application {
    
    @Override
    public void init() {
        //tänne tietokannan alustus, daot yms.
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Catch the fish - but not too soon...");
        
        Pane layout = new Pane();
        
        
        //luo vene
        //luo karit
        //luo kalat
        
        //painetut napit
        
        //animation timer & public void handle - 
        //jos jotain nappia painetaan, jotain tapahtuu
        //shape-metodit: käänny, liiku
        
        Scene scene = new Scene(layout, 800, 400);
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
