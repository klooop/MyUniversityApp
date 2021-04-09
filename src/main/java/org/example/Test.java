package org.example;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    ResultSet rs;
    private static int count;
    public static int counter = 600;
    Timeline timeline;

    @FXML
    private Label theQuestion;

    @FXML
    private Button buttonAnswer1;

    @FXML
    private Button buttonAnswer2;

    @FXML
    private Button buttonAnswer3;

        @FXML
        private Label timerLabel;

    @FXML
    void initialize() {
        connector();
        timer();
        buttonAnswer1.setOnAction(actionEvent ->{
            checkAnswer(1);
            selectNewRow();
        } );
        buttonAnswer2.setOnAction(actionEvent ->{
            checkAnswer(2);
            selectNewRow();
        } );
        buttonAnswer3.setOnAction(actionEvent ->{
            checkAnswer(3);
            selectNewRow();
        } );

    }

    private void timer() {
        if (User.discipline.equals("Базовый уровень")) counter=1200;
        if (User.discipline.equals("Углубленный уровень")) counter=1800;
        if (User.discipline.equals("Уровень повышенной сложности")) counter=2700;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            if (counter>0) {
                timerLabel.setText("Осталось "+counter+" сек.");
                counter--;
            }
            else{
                timeline.stop();
                DbConnector.disconnect();
                Controller.showNewScene(getClass(), buttonAnswer1, "Results");
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void connector() {
        try {
            DbConnector.connect();
            if (User.discipline.equals("Базовый уровень")){
                rs=DbConnector.selectEasyLevelQuestions();
                System.out.println("выбраны вопросы базовые");
            }
            if (User.discipline.equals("Углубленный уровень")) {
                rs=DbConnector.selectMiddleLevelQuestions();
                System.out.println("выбраны вопросы углубленные");

            }
            if (User.discipline.equals("Уровень повышенной сложности")){
                rs=DbConnector.selectHardLevelQuestions();
                System.out.println("выбраны вопросы хардовые");

            }
            selectNewRow();

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void selectNewRow() {
        try {
            if (count<8) {
                rs.next();
                buttonAnswer1.setText(rs.getString("firstVar"));
                buttonAnswer2.setText(rs.getString("secondVar"));
                buttonAnswer3.setText(rs.getString("thirdVar"));
                theQuestion.setText(rs.getString("question"));
                count++;
            }
            else{
                System.out.println(User.POINTS);
                Controller.showNewScene(getClass(), buttonAnswer1, "PictureTest");
                timeline.stop();
            }
        } catch (SQLException throwables) {
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
