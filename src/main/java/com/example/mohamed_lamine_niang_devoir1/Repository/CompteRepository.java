package com.example.mohamed_lamine_niang_devoir1.Repository;

import com.example.mohamed_lamine_niang_devoir1.BD.BD;
import com.example.mohamed_lamine_niang_devoir1.BD.Compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompteRepository {
    private Connection connection;

    public CompteRepository() {
        this.connection = new BD().getConnection();
    }

    public ObservableList<Compte> getAllComptes() {
        ObservableList<Compte> comptes = null;
        try {
            comptes = FXCollections.observableArrayList();
            String sql = "SELECT * FROM comptes";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Compte compte = new Compte();
                compte.setId(resultSet.getInt("id"));
                compte.setNom(resultSet.getString("nom"));
                compte.setPrenom(resultSet.getString("prenom"));
                compte.setType(resultSet.getString("type"));
                compte.setTypeCompte(resultSet.getString("typeCompte"));
                compte.setMontant(resultSet.getInt("montant"));
                comptes.add(compte);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return comptes;
    }

    public void addCompte(Compte compte) {
        try {
            String sql = "INSERT INTO comptes (nom, prenom, type, typeCompte, montant) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, compte.getNom());
            statement.setString(2, compte.getPrenom());
            statement.setString(3, compte.getType());
            statement.setString(4, compte.getTypeCompte());
            statement.setInt(5, compte.getMontant());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCompte(Compte compte) {
        try {
            String sql = "UPDATE comptes SET nom = ?, prenom = ?, type = ?, typeCompte = ?, montant = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, compte.getNom());
            statement.setString(2, compte.getPrenom());
            statement.setString(3, compte.getType());
            statement.setString(4, compte.getTypeCompte());
            statement.setInt(5, compte.getMontant());
            statement.setInt(6, compte.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // recherche dynamique dans la table en fonction du nom ou du prenom
    public ObservableList<Compte> searchCompte(String search) {
        ObservableList<Compte> comptes = null;
        try {
            comptes = FXCollections.observableArrayList();
            String sql = "SELECT * FROM comptes WHERE nom LIKE ? OR prenom LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + search + "%");
            statement.setString(2, "%" + search + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Compte compte = new Compte();
                compte.setId(resultSet.getInt("id"));
                compte.setNom(resultSet.getString("nom"));
                compte.setPrenom(resultSet.getString("prenom"));
                compte.setType(resultSet.getString("type"));
                compte.setTypeCompte(resultSet.getString("typeCompte"));
                compte.setMontant(resultSet.getInt("montant"));
                comptes.add(compte);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return comptes;
    }
}
