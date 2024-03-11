package com.example.mohamed_lamine_niang_devoir1.BD;

public class Compte {
    private int id;
    private String nom;
    private String prenom;
    private String type;
    private String typeCompte;
    private int montant;

    public Compte(int id, String nom, String prenom, String type, String typeCompte, int montant) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.typeCompte = typeCompte;
        this.montant = montant;
    }

    public Compte(String nom, String prenom, String type, String typeCompte, int montant) {
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.typeCompte = typeCompte;
        this.montant = montant;
    }

    public Compte() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
}
