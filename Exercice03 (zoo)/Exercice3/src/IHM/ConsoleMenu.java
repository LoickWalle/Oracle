package IHM;

import Models.*;
import interfaces.ZooAction;

import java.util.Scanner;
import java.util.UUID;

public class ConsoleMenu {

    private ZooManager zooManager;
    private Scanner sc;

    public ConsoleMenu(ZooManager zooManager) {
        this.zooManager = zooManager;
        this.sc = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n=== Menu Zoo ===");
            System.out.println("1. Ajouter un animal");
            System.out.println("2. Lister les animaux");
            System.out.println("3. Nourrir les animaux");
            System.out.println("4. Ajouter un enclos");
            System.out.println("5. Quitter");
            System.out.print("Choix : ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addAnimal();
                case 2 -> displayAnimals();
                case 3 -> feedAnimals();
                case 4 -> addEnclosure();
                case 5 -> System.out.println("Merci et au revoir !!");
                default -> System.out.println("Choix invalide !");
            }
        } while (choice != 5);
    }

    private void addEnclosure() {
        System.out.print("Entrez le nom de l'enclos : ");
        String enclosureName = sc.nextLine();
        zooManager.addEnclosure(new Enclosure(zooManager.getEnclosures().size(), enclosureName));
    }

    public void feedAnimals() {
        new ZooAction(){
            @Override
            public void Action() {
                zooManager.getEnclosures().forEach(enclosure ->
                        enclosure.getAnimalList().forEach(Animal::eat)
                );
            }
        };

    }

    private void displayAnimals() {
        int option;
        do {
            System.out.println("1. Voir tous les animaux");
            System.out.println("2. Voir les animaux par enclos");
            System.out.print("Votre choix : ");
            option = sc.nextInt();
            sc.nextLine();
        } while (option != 1 && option != 2);

        if (option == 1) {
            zooManager.getEnclosures().forEach(Enclosure::displayAllAnimals);
        } else {
            zooManager.listAllEnclosures();
            System.out.print("Choix de l'enclos : ");
            int enclosureId = sc.nextInt();
            sc.nextLine();
            Enclosure enclosure = zooManager.getEnclosureById(enclosureId);
            if (enclosure != null) enclosure.displayAllAnimals();
        }
    }

    private void addAnimal() {
        if (zooManager.getEnclosures().isEmpty()) {
            System.out.println("Pas d'enclos disponible...");
            return;
        }

        System.out.println("1. Mammifère\n2. Oiseau");
        System.out.print("Votre choix : ");
        int animalType = sc.nextInt();
        sc.nextLine();

        System.out.print("Entrez le nom de l'animal : ");
        String name = sc.nextLine();
        System.out.print("Entrez l'espèce : ");
        String species = sc.nextLine();

        zooManager.listAllEnclosures();
        System.out.print("Choisissez l'enclos : ");
        int enclosureId = sc.nextInt();
        sc.nextLine();
        Enclosure selectedEnclosure = zooManager.getEnclosureById(enclosureId);

        if (selectedEnclosure != null) {
            Animal animal = (animalType == 1) ?
                    new Mammal(UUID.randomUUID(), name, species) :
                    new Bird(UUID.randomUUID(), name, species);
            selectedEnclosure.addAnimal(animal);
        } else {
            System.out.println("Enclos introuvable.");
        }
    }
}
