package ba.unsa.etf.rpr.labrpr;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.List;

public class GradController {

    @FXML TextField fieldNaziv;
    @FXML TextField fieldBrStanovnika;
    @FXML ChoiceBox<Drzava> choiceDrzava;
    @FXML private Button btnOk;
    @FXML private Button btnCancel;

    private GeografijaDAO geografijaDAO;
    private Grad grad;

    public void setGeografijaDAO(GeografijaDAO geografijaDAO) {
        this.geografijaDAO = geografijaDAO;
        initialize();
    }
    public void initialize() {
        if (geografijaDAO != null) {
            List<Drzava> drzave = geografijaDAO.drzave();

            choiceDrzava.getItems().clear();
            choiceDrzava.getItems().addAll(drzave);

            choiceDrzava.setConverter(new StringConverter<Drzava>() {
                @Override
                public String toString(Drzava drzava) {
                    return drzava != null ? drzava.getNaziv() : "";
                }

                @Override
                public Drzava fromString(String string) {
                    return null;
                }
            });
        }
    }



    public void handleOk(ActionEvent actionEvent) {
        String nazivValue = fieldNaziv.getText();
        String brStanovnikaValue = fieldBrStanovnika.getText();
        Drzava drzavaValue = choiceDrzava.getValue();

        // Validacija unosa
        if (validateInput(nazivValue, brStanovnikaValue, drzavaValue)) {
            int brStValue = Integer.parseInt(brStanovnikaValue);
            if (geografijaDAO != null) {
                if (grad == null) { // kreiramo novi grad
                    Grad noviGrad = new Grad(nazivValue, brStValue, drzavaValue);
                    noviGrad.setIdDrzava(drzavaValue);
                    geografijaDAO.dodajGrad(noviGrad);
                } else {
                    // Ako grad postoji, ažurirajte podatke o gradu
                    grad.setNaziv(nazivValue);
                    grad.setBrojStanovnika(brStValue);
                    grad.setIdDrzava(drzavaValue);
                    geografijaDAO.izmijeniGrad(grad);
                }
            }
            closeWindow();
        }
    }



    private boolean validateInput(String naziv, String brStanovnika, Drzava drzava) {
        if (naziv.isEmpty() || brStanovnika.isEmpty() || drzava == null) {
            showAlert("Neispravan unos", "Sva polja moraju biti popunjena.");
            return false;
        }

        try {
            int brojStanovnika = Integer.parseInt(brStanovnika);
            if (brojStanovnika < 0) {
                showAlert("Neispravan unos", "Broj stanovnika ne moze biti negativan.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert("Neispravan unos", "Broj stanovnika mora biti cijeli broj.");
            return false;
        }

        return true;
    }

    private void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public void handleCancel(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void setGrad(Grad selectedGrad) {
        if (selectedGrad != null) {
            this.grad = selectedGrad;

            fieldNaziv.setText(grad.getNaziv());
            fieldBrStanovnika.setText(String.valueOf(grad.getBrojStanovnika()));
            choiceDrzava.setValue(grad.getIdDrzava());
        } else {
            fieldNaziv.clear();
            fieldBrStanovnika.clear();
            choiceDrzava.setValue(null);
        }
    }

}
