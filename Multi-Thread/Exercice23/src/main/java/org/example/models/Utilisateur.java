package org.example.models;

public class Utilisateur {
    private String name;
    private Panier panier;

    public Utilisateur(String name, Panier panier) {
        this.name = name;
        this.panier = panier;
    }

    public String getName() {
        return name;
    }

    public Panier getPanier() {
        return panier;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "name='" + name + '\'' +
                ", panier=" + panier +
                '}';
    }
}
