package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Theory {
    private String PATH;
    @FXML
    private ResourceBundle resources;



    @FXML
    private Button returnButton;

    @FXML
    private Button theoryButton1;

    @FXML
    private Button theoryButton2;

    @FXML
    private Button theoryButton3;

    @FXML
    private Button theoryButton4;

    @FXML
    private Button theoryButton5;

    @FXML
    private Button theoryButton6;

    @FXML
    private Button theoryButton7;

    @FXML
    void initialize() {
        returnButton.setOnAction(event->{
            Controller.showNewScene( getClass(), returnButton, Controller.capturedWindow);
        });
        theoryButton1.setOnAction(event -> showDOCXDocument("Виды модуляции"));
        theoryButton2.setOnAction(event -> showDOCXDocument("Основные элементы и устройства РЭА"));
        theoryButton3.setOnAction(event -> showDOCXDocument("Основы электродинамики"));
        theoryButton4.setOnAction(event -> showDOCXDocument("Принципы построения и основные характеристики рст"));
        theoryButton5.setOnAction(event -> showDOCXDocument("Принципы построения радиостанций"));
        theoryButton6.setOnAction(event -> showDOCXDocument("Распространение радиоволн"));
        theoryButton7.setOnAction(event -> showDOCXDocument("Характеристики радиостанций"));
    }

    private void showDOCXDocument(String fileName) {
        try {
            if (Desktop.isDesktopSupported()) {
                 fileName = new File("theory").getAbsolutePath()+"\\"+fileName+".docx";
                System.out.println(fileName);
                Desktop.getDesktop().open(new File(fileName));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
