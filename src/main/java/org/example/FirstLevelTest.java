package org.example;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FirstLevelTest {

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
            Controller.capturedWindow="FirstLevelTest";
            Controller.showNewScene( getClass(), theoryButton, "Theory");
        });
        returnButton.setOnAction(event ->Controller.showNewScene(getClass(),returnButton,"TestMenu"));
        startButton.setOnAction(exvent->{
            User.discipline="Базовый уровень";
            Controller.showNewScene(getClass(),returnButton,"SignUp");
        });
    }
}