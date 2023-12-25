package com.curswork.numericalcalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * JavaFX App
 */
public class NumericalCalculator extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/numericalcalculator/primary"));
        stage.setScene(scene);
        stage.setTitle("Numercal Calculator");
        stage.setMinHeight(540);
        stage.setMinWidth(870);
        stage.getIcons().add(new Image(NumericalCalculator.class.getResourceAsStream("/numericalcalculator/calculator.png")));
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NumericalCalculator.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}