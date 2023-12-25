package com.curswork.numericalcalculator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable{
	
    @FXML
    private TextField accuraty;

    @FXML
    private RadioButton chordRad;

    @FXML
    private Button decideBut;

    @FXML
    private VBox decideRes;

    @FXML
    private RadioButton dichotomyRad;

    @FXML
    private TextField expression;

    @FXML
    private Label filedForX;

    @FXML
    private LineChart<?, ?> functionGraph;

    @FXML
    private Button helpButton;

    @FXML
    private TextField leftBorder;

    @FXML
    private Label leftBorderVal;

    @FXML
    private Label leftNum;

    @FXML
    private TextField rightBorder;

    @FXML
    private Label rightBorderVal;

    @FXML
    private Label rightNum;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
