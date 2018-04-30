
package fishquest.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class StartViewCreator {
    TextField nameField;
    Button startButton;
    
    public StartViewCreator() {
    }
    
    public Scene createStartView() {
        Text gameText = new Text("FISHQUEST");
        Label nameText = new Label("Enter your intials:");
        nameField = new TextField();

        startButton = new Button("Start");

        GridPane startLayout = new GridPane();

        startLayout.add(gameText, 2, 0);
        startLayout.add(nameText, 2, 2);
        startLayout.add(nameField, 2, 3);
        startLayout.add(startButton, 2, 5);

        startLayout.setHgap(10);
        startLayout.setVgap(10);
        startLayout.setPadding(new Insets(10, 10, 10, 10));

        Scene startView = new Scene(startLayout);
        return startView;
    }
    
    public String getPlayersName() {
        return this.nameField.getText();
    }
    
    public Button getStartButton() {
        return this.startButton;
    }
}
