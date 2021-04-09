package org.example;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUp {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Button beginButton;


    @FXML
    void initialize() {
         beginButton.setOnAction(actionEvent -> {
             User.firstName = firstName.getText();
             User.lastName = lastName.getText();
             Controller.showNewScene(getClass(), beginButton, "Test");

         });
    }
}
