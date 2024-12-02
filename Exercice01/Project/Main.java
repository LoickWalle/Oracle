package Project; 

import java.util.Scanner;

public class Main { 
    
    static Yearbook yearbook = new Yearbook(); 
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) { 
        int option; 

        do {
            System.out.println("""
            
            Exercice01 : 
                1. Ajouter un contact
                2. Lister les contacts
                3. Quitter
            """);

            option = sc.nextInt();
            sc.nextLine();
            
            switch(option) {
                case 1: 
                    addPerson(); 
                    break; 
                case 2: 
                    displayPersons();
                    break;
                case 3: 
                    quit(); 
                    break; 
                default: 
                    System.out.println("Mauvaise option - Essayez encore !\n"); 
            }

        } while (option != 3);
    }

    public static void addPerson() {
        System.out.print("Nom du contact : "); 
        String name = sc.nextLine(); 

        System.out.print("Numero de telephone du contact : "); 
        String phone = sc.nextLine(); 

        yearbook.addPerson(new Person(name, phone));
        System.out.println("Contact ajouté !\n");
    }

    public static void displayPersons() {
        System.out.println("\nListe des contacts : \n"); 
        if (!yearbook.getAllPersons().isEmpty()) {
            int counter = 1;
            for (Person person : yearbook.getAllPersons()) {
                System.out.println(counter++ + ". " + person);
            }
        } else {
            System.out.println("Aucun contact...\n"); 
        }
    }

    public static void quit() {
        System.out.println("Merci d'avoir utilisé mon App ^^");
    }
}