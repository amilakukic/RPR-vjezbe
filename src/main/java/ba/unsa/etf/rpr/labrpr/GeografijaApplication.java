package ba.unsa.etf.rpr.labrpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GeografijaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GeografijaApplication.class.getResource("glavni.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 240);
        stage.setTitle("Gradovi svijeta");
        stage.setScene(scene);
        stage.show();
    }
//jdbc:sqlite:C:\Users\amila\OneDrive\Desktop\bazaFinal.db
    public static void main(String[] args) {
        launch();
    }
}