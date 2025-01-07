package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IHM {
    private final String journalNameFile = "journal.txt";
    private final String journalBinaryNameFile = "journal_backup.dat";

    public void startProgram(){
        init();
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        do {
            displayIHM();

            System.out.print("Choisissez une option : ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1 :
                    System.out.print("Votre entrée : ");
                    JournalText.addEntry(sc.nextLine(), journalNameFile);
                    break;
                case 2 :
                    System.out.println("Affichage du journal : ");
                    JournalText.displayJournal(journalNameFile);
                    break;
                case 3 :
                    JournalBinary.copyJournalToBinary(journalNameFile, journalBinaryNameFile);
                    break;
                case 4 :
                    System.out.println("Affichage du journal binaire : ");
                    JournalBinary.displayBinaryJournal(journalBinaryNameFile);
                default:
                    break;
            }
        }while (choice != 5);
    }

    public void init(){
        File file = new File(journalNameFile);

        if (!file.exists()) {
            try(FileWriter writer = new FileWriter(journalNameFile)){
                writer.write("");
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void displayIHM(){
        System.out.println("""
                --- Menu ---
                1. Ajouter une activité
                2. Afficher les activités
                3. Sauvegarder en binaire
                4. Lire le fichier binaire
                5. Quitter
                """);
    }

}
