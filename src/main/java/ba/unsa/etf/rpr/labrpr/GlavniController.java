package ba.unsa.etf.rpr.labrpr;
import java.util.concurrent.atomic.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GlavniController {

    @FXML
    private TableView<Grad> tableViewGradovi;
    @FXML
    private TableColumn<Grad, Integer> colGradId;
    @FXML
    private TableColumn<Grad, String> colGradNaziv;
    @FXML
    private TableColumn<Grad, Integer> colGradBrStanovnika;
    @FXML
    private TableColumn<Grad, Drzava> colGradDrzava;
    @FXML
    private TableView<Drzava> tableViewDrzave;
    @FXML
    private GradController gradController;

    @FXML
    private DrzavaController drzavaController;

    private GeografijaDAO geografijaDAO;

    public void initialize() {
        geografijaDAO = GeografijaDAO.getInstance();
        if (geografijaDAO != null) {
            // Postavljanje tipova stupaca i njihovih svojstava
            colGradId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            colGradNaziv.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNaziv()));
            colGradBrStanovnika.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getBrojStanovnika()).asObject());
            colGradDrzava.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getIdDrzava().getNaziv()));
            // Postavljanje podataka u TableView
            tableViewGradovi.setItems(FXCollections.observableArrayList(geografijaDAO.gradovi()));
        }else System.err.println("GeografijaDAO nije inicijaliziran.");
    }

    public void setGeografijaDAO(GeografijaDAO geografijaDAO) {
        this.geografijaDAO = geografijaDAO;

        gradController.setGeografijaDAO(geografijaDAO);
        drzavaController.setGeografijaDAO(geografijaDAO);

        initializeGradoviTable();
        initializeDrzaveTable();
    }

    private void initializeGradoviTable() {
        TableColumn<Grad, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Grad, String> colNaziv = new TableColumn<>("Naziv");
        colNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));

        TableColumn<Grad, Integer> colBrojStanovnika = new TableColumn<>("Broj Stanovnika");
        colBrojStanovnika.setCellValueFactory(new PropertyValueFactory<>("brojStanovnika"));

        TableColumn<Grad, Drzava> colDrzava = new TableColumn<>("Država");
        colDrzava.setCellValueFactory(new PropertyValueFactory<>("drzava"));

        tableViewGradovi.getColumns().addAll(colId, colNaziv, colBrojStanovnika, colDrzava);

        refreshGradoviTable();
    }

    private void initializeDrzaveTable() {
        TableColumn<Drzava, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Drzava, String> colNaziv = new TableColumn<>("Naziv");
        colNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));

        TableColumn<Drzava, Grad> colGlavniGrad = new TableColumn<>("Glavni Grad");
        colGlavniGrad.setCellValueFactory(new PropertyValueFactory<>("glavniGrad"));

        tableViewDrzave.getColumns().addAll(colId, colNaziv, colGlavniGrad);

        //refreshDrzaveTable();
    }

    private void refreshGradoviTable() {
        List<Grad> gradovi = geografijaDAO.gradovi();
        ObservableList<Grad> gradoviObservableList = FXCollections.observableArrayList(gradovi);
        tableViewGradovi.setItems(gradoviObservableList);
    }


    public void handleDodajGrad(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("grad.fxml"));
            Parent root = loader.load();
            GradController gradController = loader.getController();
            gradController.setGeografijaDAO(geografijaDAO);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Grad");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDodajDrzavu(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("drzava.fxml"));
            Parent root = loader.load();
            DrzavaController drzavaController = loader.getController();
            drzavaController.setGeografijaDAO(geografijaDAO);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Država");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleIzmijeniGrad(ActionEvent actionEvent) {
        Grad selectedGrad = tableViewGradovi.getSelectionModel().getSelectedItem();

        if (selectedGrad != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("grad.fxml"));
                Scene scene = new Scene(loader.load());

                // Dobivanje kontrolera za GradController kako bismo postavili podatke o gradu
                GradController gradController = loader.getController();
                gradController.setGeografijaDAO(geografijaDAO);
                gradController.setGrad(selectedGrad);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Izmjena grada");
                stage.setScene(scene);
                stage.showAndWait();

                // Osvježavanje tabele nakon povratka iz GradController-a
                refreshGradoviTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Ako nema označenog grada, prikaži poruku
            showAlert("Nije odabran grad", "Molimo odaberite grad koji želite izmijeniti.");
        }
    }


    public void handleObrisiGrad(ActionEvent actionEvent) {
        Grad selectedGrad = tableViewGradovi.getSelectionModel().getSelectedItem();

        if (selectedGrad != null) {
            // Prikaži dijalog za potvrdu brisanja ili izvrši brisanje odmah
            boolean confirmed = showDeleteConfirmationDialog(selectedGrad);

            if (confirmed) {
                // Obriši grad iz baze podataka
                geografijaDAO.obrisiGrad(selectedGrad.getId());

                // Osvježi prikaz tabele
                refreshGradoviTable();
            }
        } else {
            // Ako nema označenog grada, prikaži poruku
            showAlert("Nije odabran grad", "Molimo odaberite grad koji želite obrisati.");
        }
    }

    private boolean showDeleteConfirmationDialog(Grad grad) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda brisanja");
        alert.setHeaderText("Brisanje grada");
        alert.setContentText("Jeste li sigurni da želite obrisati grad " + grad.getNaziv() + "?");
        alert.initModality(Modality.APPLICATION_MODAL);
        return alert.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}

