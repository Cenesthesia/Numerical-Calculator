package com.curswork.numericalcalculator;

import java.net.URL;
import java.util.ResourceBundle;

import com.curswork.numericalmethods.methods.ChordMethod;
import com.curswork.numericalmethods.methods.DichotomyMethod;
import com.curswork.numericalmethods.methods.Method;
import com.curswork.numericalmethods.methods.RootI;
import com.curswork.numericalmethods.parser.EquationParser;
import com.curswork.numericalmethods.parser.EquationPerformer;
import com.curswork.numericalmethods.parser.ExpressionParserI;
import com.curswork.numericalmethods.parser.ExpressionPerformerI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable{
	
	private Method nowMethod;
	
	private ExpressionPerformerI performer;
	
	private ExpressionParserI parser;
	
	private ChordMethod chord;
	
	private DichotomyMethod dichotomy;
	
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
    	try {
    		nowMethod.setExpression(expression.getText());
    		if (leftBorder.getText().isEmpty()) 
    			throw new ArithmeticException("Значение левой границы не может быть пустым.");
    		if (rightBorder.getText().isEmpty()) 
    			throw new ArithmeticException("Значение правой границы не может быть пустым.");
    		if (accuraty.getText().isEmpty()) 
    			throw new ArithmeticException("Значение точности не может быть пустым.");
    		double leftValue  = Double.parseDouble(leftBorder.getText());
    		double rightValue  = Double.parseDouble(rightBorder.getText());
    		nowMethod.setParam(leftValue, rightValue, Double.parseDouble(accuraty.getText()));
			nowMethod.compile();
			RootI root = nowMethod.calculateRoot();
			leftNum.setText(Double.toString(nowMethod.calculate(leftValue)));
			rightNum.setText(Double.toString(nowMethod.calculate(rightValue)));
			if (root.isPresent())
				filedForX.setText(Double.toString(root.getValue()));
			else
				filedForX.setText("В данном уравнении, в данном диапазоне корней нет.");
			decideRes.setVisible(true);
			
		} catch (ArithmeticException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
    }

    @FXML
    void setChordMethod(ActionEvent event) {
		nowMethod = chord;
    }

    @FXML
    void setDichotomyMethod(ActionEvent event) {
    	nowMethod = dichotomy;
    }

    @FXML
    void showHelp(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		parser = new EquationParser();
		performer = new EquationPerformer(parser);
		chord = new ChordMethod(performer);
		dichotomy = new DichotomyMethod(performer);
		nowMethod = chord;
	}

}
