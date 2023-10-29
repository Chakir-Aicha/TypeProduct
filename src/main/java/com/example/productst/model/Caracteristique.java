package com.example.productst.model;

import org.springframework.web.bind.annotation.CrossOrigin;


public class Caracteristique {
    private String nom;
    private String typeDonnees;

    public Caracteristique(String nom, String typeDonnees) {
        this.nom = nom;
        this.typeDonnees = typeDonnees;
    }

    public Caracteristique() {
    }

    public String getNom() {
        return nom;
    }

    public String getTypeDonnees() {
        return typeDonnees;
    }
}
