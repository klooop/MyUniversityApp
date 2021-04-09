package org.example;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondLevelTest {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnButton;

    @FXML
    private Button theoryButton;

    @FXML
    private Button startButton;

    @FXML
    void initialize() {
        theoryButton.setOnAction(event -> {
            Controller.capturedWindow="SecondLevelTest";
            Controller.showNewScene( getClass(), theoryButton, "Theory");
        });
        returnButton.setOnAction(event ->Controller.showNewScene(getClass(),returnButton,"TestMenu"));
        startButton.setOnAction(event->{
            User.discipline="Углубленный уровень";
            Controller.showNewScene(getClass(),returnButton,"SignUp");
        });
    }
}