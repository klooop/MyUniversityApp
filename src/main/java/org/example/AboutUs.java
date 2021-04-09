package org.example;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutUs {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button returnButton;

    @FXML
    void initialize() {
        returnButton.setOnAction(event -> {
            Controller.showNewScene( getClass(), returnButton, "TestMenu");
        });
    }
}
