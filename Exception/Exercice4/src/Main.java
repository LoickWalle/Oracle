import exceptions.NotPositiveException;
import models.Student;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Bonjour dans notre gestionnaire d'étudiant !");

        int choice;
        do {
            displayMenu();
            choice = getUserChoice();
            handleUserChoice(choice);
        } while (choice != 0);
    }

    private static void displayMenu() {
        System.out.println("\n1. Ajouter un étudiant");
        System.out.println("2. Afficher les étudiants");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static int getUserChoice() {
        do {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Choix invalide. Veuillez entrer un nombre entier.");
            }finally {
                sc.nextLine();
            }
        }while (true);
    }

    private static void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                displayStudents();
                break;
            case 0:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Choix non valide, veuillez réessayer.");
        }
    }

    private static void addStudent() {
        System.out.print("Nom : ");
        String name = sc.nextLine();

        int age = getStudentAge();

        students.add(new Student(name, age));
        System.out.println("L'étudiant a été ajouté avec succès.");
    }

    private static int getStudentAge() {
        int age;
        do {
            System.out.print("Age : ");
            try {
                age = sc.nextInt();
                if (age < 0)
                    throw new NotPositiveException("L'age doit être positif !");

                return age;

            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Vous devez entrer un nombre entier pour l'age.");
            } catch (NotPositiveException e) {
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("Aucun étudiant n'est enregistré.");
        } else {
            System.out.println("\nListe des étudiants :");
            students.forEach(student ->
                    System.out.println("Nom : " + student.getName() + ", Age : " + student.getAge()));
        }
    }
}
