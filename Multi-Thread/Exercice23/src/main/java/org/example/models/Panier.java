package org.example.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Panier {
    private HashMap<Article, Integer> produits = new HashMap<>();

    public Panier() {
    }

    public HashMap<Article, Integer> getProduits() {
        return produits;
    }

    public synchronized void ajouterAuPanier(Article article){

        if(!produits.containsKey(article))
            produits.put(article, 1);
        else
            produits.compute(article, (k,v) -> v+1);
    }

    public synchronized void retirerDuPanier(Article article){
        produits.remove(article);
    }

    @Override
    public String toString() {
        return "Panier{" +
                "produits=" + produits +
                '}';
    }
}
