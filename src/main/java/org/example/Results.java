package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Results {
    private int mark;
    private String PATH="results";


    @FXML
    private Label result;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
       checkTheMark();
       result.setText(String.format("Вы ответили верно на %d вопроса(ов)\nВаша оценка %d",User.POINTS,mark ));
       exitButton.setOnAction(event->{
           Stage stage = (Stage) exitButton.getScene().getWindow();
           stage.close();
       });
    }

    private void checkTheMark() {
        if (User.POINTS==10) mark = 5;
        else if (User.POINTS>=8) mark =4;
        else if(User.POINTS>=6) mark = 3;
        else mark=2;

        File file = new File(PATH);
        String absolutePath = file.getAbsolutePath()+".txt";
        try (BufferedWriter fileWriter= new BufferedWriter(new FileWriter(absolutePath,true)) ) {
            String str = String.format("Студент %s %s прошел тест \"%s\" и получил оценку %d\n",User.lastName,User.firstName,User.discipline,mark);
            fileWriter.write(str);
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}

