package org.example;

import org.example.models.Book;
import org.example.models.Library;
import org.example.utils.SerializeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            displayIHM();

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1 :
                    String title;
                    String author;
                    System.out.print("Titre du livre : ");
                    title = sc.nextLine();
                    System.out.print("Author du livre : ");
                    author = sc.nextLine();
                    SerializeUtils.addBookToLibrary(new Book(title,author));
                    break;
                case 2 :
                    Library libraryDeserialized = SerializeUtils.deserialiseLibrary();
                    if(libraryDeserialized == null)
                        System.out.println("Pas de livres dans la bibliothèque.");
                    else
                        System.out.println(libraryDeserialized);
                    break;
                case 3 :
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }while (choice != 3);
    }

    public static void displayIHM(){
        System.out.println("""
                === Gestion de la bibliothèque ===
                1. Ajouter un livre à la bibliothèque
                2. Afficher la bibliothèque
                3. Quitter le programme
                """);
        System.out.print("Votre choix : ");
    }
}