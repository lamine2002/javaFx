package com.example.mohamed_lamine_niang_devoir1;

import com.example.mohamed_lamine_niang_devoir1.BD.BD;
import com.example.mohamed_lamine_niang_devoir1.BD.Compte;
import com.example.mohamed_lamine_niang_devoir1.Repository.CompteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private CompteRepository compteRepository;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colMontant;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colPrenom;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colTypeCompte;

    @FXML
    private ComboBox<String> comboTypeCompte;

    @FXML
    private TextField saiMontant;

    @FXML
    private TextField saiNom;

    @FXML
    private TextField saiPrenom;

    @FXML
    private TextField saiType;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchFild;

    @FXML
    private TableView<Compte> tableCompte;

    @FXML
    void addCompte(ActionEvent event) {
        String nom = saiNom.getText();
        String prenom = saiPrenom.getText();
        String typeCompte = comboTypeCompte.getValue();
        String type = saiType.getText();
        int montant = Integer.parseInt(saiMontant.getText());
        Compte compte = new Compte(nom, prenom, type, typeCompte, montant);
        compteRepository.addCompte(compte);
        afficher();
        clear(event);
    }

    @FXML
    void clear(ActionEvent event) {
        saiMontant.setText("");
        saiNom.setText("");
        saiPrenom.setText("");
        comboTypeCompte.setValue("");
        saiType.setText("");
    }

    @FXML
    void editCompte(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Compte compte = tableCompte.getSelectionModel().getSelectedItem();
            saiNom.setText(compte.getNom());
            saiPrenom.setText(compte.getPrenom());
            comboTypeCompte.setValue(compte.getTypeCompte());
            saiType.setText(compte.getType());
            saiMontant.setText(String.valueOf(compte.getMontant()));
        }
    }

    @FXML
    void searchCompte(ActionEvent event) {

    }

    @FXML
    void typeCompte(ActionEvent event) {
        String typeCompte = comboTypeCompte.getValue();
        if (Objects.equals(typeCompte, "Courant")) {
            saiType.setText("Decouvert");
        } else {
            saiType.setText("Taux");
        }
    }

    @FXML
    void updateCompte(ActionEvent event) {
        int id = tableCompte.getSelectionModel().getSelectedItem().getId();
        String nom = saiNom.getText();
        String prenom = saiPrenom.getText();
        String typeCompte = comboTypeCompte.getValue();
        String type = saiType.getText();
        int montant = Integer.parseInt(saiMontant.getText());
        Compte compte = new Compte(id, nom, prenom, type, typeCompte, montant);
        compteRepository.updateCompte(compte);
        afficher();
        clear(event);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        compteRepository = new CompteRepository();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Epargne", "Courant");
        comboTypeCompte.setItems(list);
        afficher();

        // Attachez l'événement chercher au champ de recherche (searchField)
        searchFild.textProperty().addListener((observable, oldValue, newValue) -> chercher(new ActionEvent()));
    }

    public void afficher() {
        ObservableList<Compte> list = compteRepository.getAllComptes();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colTypeCompte.setCellValueFactory(new PropertyValueFactory<>("typeCompte"));
        colMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        tableCompte.setItems(list);
    }

    @FXML
    void chercher(ActionEvent event) {
        String recherche = searchFild.getText().trim().toLowerCase();
        ObservableList<Compte> listeComplete = compteRepository.getAllComptes();
        ObservableList<Compte> listeFiltree = FXCollections.observableArrayList();

        for (Compte compte : listeComplete) {
            if (compte.getNom().toLowerCase().contains(recherche) ||
                    compte.getPrenom().toLowerCase().contains(recherche) ||
                    compte.getType().toLowerCase().contains(recherche) ||
                    compte.getTypeCompte().toLowerCase().contains(recherche) ||
                    String.valueOf(compte.getMontant()).contains(recherche)) {
                listeFiltree.add(compte);
            }
        }

        tableCompte.setItems(listeFiltree);
    }
}