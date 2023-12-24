package ba.unsa.etf.rpr.korisnici.lv7i8;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class KorisniciControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(KorisniciApplication.class.getResource("korisnici-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 400);
        stage.setTitle("Korisnici!");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void testDodajKorisnika() {
        clickOn("#name").write("Amila");
        clickOn("#lName").write("Kukic");
        clickOn("#eMail").write("ak@mail.com");
        clickOn("#kIme").write("akukic");
        clickOn("#passw").write("password");

        clickOn("#dodaj");

        assertEquals(1, lookup("#listaK").queryAs(ListView.class).getItems().size());
    }

}
