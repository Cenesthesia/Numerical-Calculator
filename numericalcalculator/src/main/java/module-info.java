module com.curswork.numericalcalculator {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.curswork.numericalcalculator to javafx.fxml;
    exports com.curswork.numericalcalculator;
}
