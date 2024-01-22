module ba.unsa.etf.rpr.labrpr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ba.unsa.etf.rpr.labrpr to javafx.fxml;
    exports ba.unsa.etf.rpr.labrpr;
}