package org.example;

import org.example.Models.Commande;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
    public static void main( String[] args )
    {
        List<Commande> commandes = Arrays.asList(
                new Commande(1, "Alice", Arrays.asList("Ordinateur", "Souris"), 1200.50, true),
                new Commande(2, "Bob", Arrays.asList("Clavier", "Écran"), 300.75, false),
                new Commande(3, "Charlie", Arrays.asList("Imprimante"), 150.00, true),
                new Commande(4, "Alice", Arrays.asList("USB", "Casque"), 75.50, false),
                new Commande(5, "Bob", Arrays.asList("Tablette"), 200.00, true)
        );

        //1
        System.out.println("\nPartie 1 :");
        commandes.forEach(System.out::println);

        //2
        System.out.println("\nPartie 2 :");
        commandes.stream()
                .map(Commande::getClient)
                .distinct()
                .forEach(System.out::println);

        //3
        System.out.println("\nPartie 3 :");
        System.out.println("Montant total : " + commandes.stream()
                .mapToDouble(Commande::getMontantTotal)
                .sum());

        //4
        System.out.println("\nPartie 4 :");
        commandes.forEach(commande -> System.out.println("Articles : " + commande.getArticles() + " - Total : " + commande.getMontantTotal()));

        //5
        System.out.println("\nPartie 5 :");
        commandes.stream()
                .filter(Commande::isEstLivree)
                .forEach(commande -> System.out.println("Commande livrée : " + commande));

        //6
        System.out.println("\nPartie 6 :");
        commandes.stream()
                .collect(Collectors.groupingBy(Commande::getClient))
                .forEach((client, commandesByClient) -> {
                    double sumByClient = commandesByClient.stream()
                            .mapToDouble(Commande::getMontantTotal)
                            .sum();

                    System.out.println(client + " a dépensé : " + sumByClient);
        });

        //7
        System.out.println("\nPartie 7 :");
        commandes.stream()
                .flatMap(commande -> commande.getArticles().stream())
                .distinct()
                .forEach(System.out::println);

        //8
        System.out.println("\nPartie 8 :");
        commandes.stream()
                .collect(Collectors.groupingBy(Commande::getClient))
                .forEach((client, commandesClient) -> {
                    if(commandesClient.stream().allMatch(Commande::isEstLivree))
                        System.out.println(client + " a toutes ses commandes livrées.");
                    else
                        System.out.println(client + " n'a pas toutes ses commandes livrées.");
        });
    }
}
