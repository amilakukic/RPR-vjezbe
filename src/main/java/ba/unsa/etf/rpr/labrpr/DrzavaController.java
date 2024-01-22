
package ba.unsa.etf.rpr.labrpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.List;
import java.util.stream.Collectors;

public class DrzavaController {

    @FXML private TextField fieldNaziv;
    @FXML ChoiceBox<Grad> choiceGrad;
    @FXML Button btnOk;
    @FXML Button btnCancel;

    private GeografijaDAO geografijaDAO;

    public void setGeografijaDAO(GeografijaDAO geografijaDAO) {
        this.geografijaDAO = geografijaDAO;
        initialize();
    }

    public void initialize() {
        if (geografijaDAO != null) {
            List<Grad> gradovi = geografijaDAO.gradovi();

            choiceGrad.getItems().clear();
            choiceGrad.getItems().addAll(gradovi);

            choiceGrad.setConverter(new StringConverter<Grad>() {
                @Override
                public String toString(Grad grad) {
                    return grad != null ? grad.getNaziv() : "";
                }

                @Override
                public Grad fromString(String string) {
                    return null;
                }
            });
        }
    }



    public void handleOk(ActionEvent actionEvent) {
        if (validateInput()) {
            String nazivValue = fieldNaziv.getText().trim();
            Grad glGradValue = choiceGrad.getValue();

            if (geografijaDAO != null) {
                // Provjerite je li država već postoji
                Drzava existingDrzava = geografijaDAO.nadjiDrzavuIme(nazivValue);

                if (existingDrzava == null) {
                    // Ako država ne postoji, dodajte novu
                    Drzava novaDrzava = new Drzava(nazivValue, glGradValue);
                    geografijaDAO.dodajDrzavu(novaDrzava);
                }
            }

            closeWindow();
        }
    }


    public void handleCancel(ActionEvent actionEvent) {
        Platform.exit();
    }
    private void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    private boolean validateInput() {
        String nazivValue = fieldNaziv.getText().trim();

        if (nazivValue.isEmpty()) {
            showAlert("Naziv države ne smije biti prazan!");
            return false;
        }

        return true;
    }
    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}