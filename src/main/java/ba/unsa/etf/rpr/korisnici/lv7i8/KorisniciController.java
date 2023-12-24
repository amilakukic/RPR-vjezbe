package ba.unsa.etf.rpr.korisnici.lv7i8;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KorisniciController {
    @FXML
    private ListView<Korisnik> listaK;
    @FXML
    private TextField name;
    @FXML
    private TextField lName;
    @FXML
    private TextField eMail;
    @FXML
    private TextField kIme;
    @FXML
    private PasswordField passw;

    private final KorisniciModel model = new KorisniciModel();

    public void initialize() {
        listaK.setCellFactory(param -> new ListCell<Korisnik>() {
            @Override
            protected void updateItem(Korisnik korisnik, boolean empty) {
                super.updateItem(korisnik, empty);

                if (empty || korisnik == null) {
                    setText(null);
                } else {
                    setText(korisnik.getKorisnickoIme());
                }
            }
        });

        listaK.setItems(model.getSviKorisnici());

        listaK.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {

            model.setTrIzabraniKorisnik(newKorisnik);

            if (newKorisnik != null) {
                name.textProperty().bindBidirectional(model.getTrIzabraniKorisnik().imeProperty());
                lName.textProperty().bindBidirectional(model.getTrIzabraniKorisnik().prezimeProperty());
                eMail.textProperty().bindBidirectional(model.getTrIzabraniKorisnik().emailProperty());
                kIme.textProperty().bindBidirectional(model.getTrIzabraniKorisnik().korisnickoImeProperty());
                passw.textProperty().bindBidirectional(model.getTrIzabraniKorisnik().lozinkaProperty());
            } else {
                ocistiFormu();
            }
        });


        model.getSviKorisnici().addListener((ListChangeListener<? super Korisnik>) observable -> {
            listaK.refresh();

        });
    }

    @FXML
    public void dodajKorisnika() {
        String imeValue = name.getText();
        String prezimeValue = lName.getText();
        String emailValue = eMail.getText();
        String korisnickoImeValue = kIme.getText();
        String lozinkaValue = passw.getText();

        model.napuni(imeValue, prezimeValue, emailValue, korisnickoImeValue, lozinkaValue);

        listaK.refresh();
        name.clear();
        lName.clear();
        eMail.clear();
        kIme.clear();
        passw.clear();
    }

    @FXML
    public void handleButtonClick(ActionEvent actionEvent) {
        dodajKorisnika();
    }

    public void handleKrajClick(ActionEvent actionEvent) {

        Stage stage=(Stage) listaK.getScene().getWindow();
        stage.close();
    }

    public void handleAzurirajClick(ActionEvent actionEvent) {
        if (model.getTrIzabraniKorisnik() != null) {
            Korisnik izabraniKorisnik = model.getTrIzabraniKorisnik();
            izabraniKorisnik.setIme(name.getText());
            izabraniKorisnik.setPrezime(lName.getText());
            izabraniKorisnik.setEmail(eMail.getText());
            izabraniKorisnik.setKorisnickoIme(kIme.getText());
            izabraniKorisnik.setLozinka(passw.getText());
            int index = model.getSviKorisnici().indexOf(izabraniKorisnik);
            listaK.refresh();
            ocistiFormu();
        }
    }
    private void ocistiFormu() {
        if (model.getTrIzabraniKorisnik() != null) {

            name.textProperty().unbindBidirectional(model.getTrIzabraniKorisnik().imeProperty());
            lName.textProperty().unbindBidirectional(model.getTrIzabraniKorisnik().prezimeProperty());
            eMail.textProperty().unbindBidirectional(model.getTrIzabraniKorisnik().emailProperty());
            kIme.textProperty().unbindBidirectional(model.getTrIzabraniKorisnik().korisnickoImeProperty());
            passw.textProperty().unbindBidirectional(model.getTrIzabraniKorisnik().lozinkaProperty());


            Korisnik izabraniKorisnik = model.getTrIzabraniKorisnik();
            izabraniKorisnik.setIme(name.getText());
            izabraniKorisnik.setPrezime(lName.getText());
            izabraniKorisnik.setEmail(eMail.getText());
            izabraniKorisnik.setKorisnickoIme(kIme.getText());
            izabraniKorisnik.setLozinka(passw.getText());

            name.clear();
            lName.clear();
            eMail.clear();
            kIme.clear();
            passw.clear();
        }
    }

}