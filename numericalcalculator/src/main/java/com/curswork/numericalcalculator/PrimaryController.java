package com.curswork.numericalcalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PrimaryController {

    @FXML
    private TextField accuraty;

    @FXML
    private RadioButton chordRad;

    @FXML
    private Button decideBut;

    @FXML
    private RadioButton dichotomyRad;

    @FXML
    private TextField expression;

    @FXML
    private LineChart<?, ?> functionGraph;

    @FXML
    private Button helpButton;

    @FXML
    private TextField leftBorder;

    @FXML
    private TextField rightBorder;

    @FXML
    private ToggleGroup usingMethod;

    @FXML
    void decideExpression(ActionEvent event) {

    }

    @FXML
    void setChordMethod(ActionEvent event) {

    }

    @FXML
    void setDichotomyMethod(ActionEvent event) {

    }

    @FXML
    void showHelp(ActionEvent event) {

    }

}
