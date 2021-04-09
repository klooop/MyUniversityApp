package org.example;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.Test.counter;

public class PictureTest  {
    Timeline timeline;

    @FXML
    private Label theQuestion;

    @FXML
    private ImageView thePicture;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Label timerLabel;

//    public static String PATH = "pictures";
    public static String PATH = "pictures";
    private ResultSet rs;
    private static int count = 0;

    @FXML
    void initialize()  {
        timer();
        sendRequest();
        button1.setOnAction(actionEvent ->{
            checkAnswer(1);
            selectNewRow();
        } );
        button2.setOnAction(actionEvent ->{
            checkAnswer(2);
            selectNewRow();
        } );
        button3.setOnAction(actionEvent ->{
            checkAnswer(3);
            selectNewRow();
        } );
    }

    private void timer() {

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            if (counter>0) {
                timerLabel.setText("Осталось "+counter+" сек.");
                counter--;
            }
            else{
                timeline.stop();
                DbConnector.disconnect();
                Controller.showNewScene(getClass(), button1, "Results");
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private void sendRequest()  {
        try {
            if (User.discipline.equals("Базовый уровень")){
                rs=DbConnector.selectEasyLevelQuestionsPic();
                System.out.println("выбраны вопросы базовые");
            }
            if (User.discipline.equals("Углубленный уровень")){
                rs=DbConnector.selectMiddleLevelQuestionsPic();
                System.out.println("выбраны вопросы углубленные");
            }
            if (User.discipline.equals("Уровень повышенной сложности")){
                rs=DbConnector.selectHardLevelQuestionsPic();
                System.out.println("выбраны вопросы хардовые");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        selectNewRow();
    }

    private void selectNewRow() {

        try {
            if (count<2) {
                rs.next();
                File file = new File(PATH);
                String absolutePath = file.getAbsolutePath();
                button1.setText(rs.getString("firstVar"));
                button2.setText(rs.getString("secondVar"));
                button3.setText(rs.getString("thirdVar"));
                theQuestion.setText(rs.getString("question"));
                System.out.println(rs.getString("url"));
                thePicture.setImage(new Image(new FileInputStream(absolutePath+"/"+
                        rs.getString("url"))));
                count++;
            }
            else{
                DbConnector.disconnect();
                Controller.showNewScene(getClass(), button1, "Results");
                timeline.stop();
            }
          }
        catch (SQLException | FileNotFoundException throwables) {
        throwables.printStackTrace();
         }
    }
    private void checkAnswer(int i){
        try {
            if (i==rs.getInt("answer")) User.POINTS++;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
