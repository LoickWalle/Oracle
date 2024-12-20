package org.example;

import org.example.models.Article;
import org.example.models.Commande;

import java.util.ArrayList;
import java.util.List;

public class ECommercePlatform {
    private static List<Article> catalog = new ArrayList<>();
    private static List<Commande> commandes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}