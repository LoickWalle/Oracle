package org.example;

import org.example.models.Book;
import org.example.models.Library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = getUserChoice(sc);

            switch (choice) {
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    displayLibrary();
                    break;
                case 3:
                    System.out.println("Fin du programme...");
                    break;
                default:
                    System.out.println("Choix invalide ! Veuillez entrer un choix valide.");
            }
        } while (choice != 3);
    }

    private static void displayMenu() {
        System.out.println("""
                === Gestion de la bibliothèque ===
                1. Ajouter un livre à la bibliothèque
                2. Afficher la bibliothèque
                3. Quitter le programme
                """);
        System.out.print("Votre choix : ");
    }

    private static int getUserChoice(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Veuillez entrer un numéro valide.");
            sc.next();
        }
        return sc.nextInt();
    }

    private static void addBook(Scanner sc) {
        sc.nextLine();

        String title = getBookTitle(sc);
        String author = getBookAuthor(sc);
        Library library = getLibrary();

        library.addBook(new Book(title, author));
    }

    private static Library getLibrary() {
        Library library = Library.loadLibrary();
        if (library == null) {
            library = new Library();
        }
        return library;
    }

    private static String getBookTitle(Scanner sc) {
        System.out.print("Titre du livre : ");
        return sc.nextLine();
    }

    private static String getBookAuthor(Scanner sc) {
        System.out.print("Auteur du livre : ");
        return sc.nextLine();
    }

    private static void displayLibrary() {
        Library library = Library.loadLibrary();
        if (library == null) {
            System.out.println("Pas de livres dans la bibliothèque.");
        } else {
            System.out.println(library);
        }
    }
}
