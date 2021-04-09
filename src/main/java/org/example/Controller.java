package org.example;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public  class Controller {
    public static String capturedWindow;

    @FXML
    private Button aboutUsButton;

    @FXML
    private Button firstLevelButton;

    @FXML
    private Button secondLevelButton;

    @FXML
    private Button thirdLevelButton;

    @FXML
    void initialize() {
        aboutUsButton.setOnAction(event ->showNewScene(getClass(),aboutUsButton,"AboutUs"));
        firstLevelButton. setOnAction(event ->showNewScene(getClass(),firstLevelButton,"FirstLevelTest") );
        secondLevelButton.setOnAction(event ->showNewScene(getClass(),secondLevelButton,"SecondLevelTest") );
        thirdLevelButton.setOnAction(event ->showNewScene(getClass(),thirdLevelButton,"ThirdLevelTest") );
    }


    public static void showNewScene(Class cl, Button button, String window){
        Stage forClose = (Stage) button.getScene().getWindow();
        forClose.close();
        FXMLLoader fx = new FXMLLoader();
        fx.setLocation(cl.getResource(window+".fxml"));
        try {
            fx.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fx.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
