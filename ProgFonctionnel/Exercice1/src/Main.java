import interfaces.Calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Calculator addition = (nbr1, nbr2) -> nbr1 + nbr2;
        Calculator soustraction = (nbr1, nbr2) -> nbr1 - nbr2;
        Calculator multiplication = (nbr1, nbr2) -> nbr1 * nbr2;
        Calculator division = (nbr1, nbr2) -> {
            if (nbr2 == 0) {
                System.out.println("Erreur : Division par zéro impossible.");
                return Double.NaN;
            }
            return nbr1 / nbr2;
        };

        Map<Integer, Calculator> calculatorMap = new HashMap<>();
        calculatorMap.put(0, addition);
        calculatorMap.put(1, soustraction);
        calculatorMap.put(2, multiplication);
        calculatorMap.put(3, division);

        Consumer<String> displayIHM = message -> {
            System.out.println("Quelle opération effectuer ?");
            System.out.println("0. Addition");
            System.out.println("1. Soustraction");
            System.out.println("2. Multiplication");
            System.out.println("3. Division");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");
        };

        int choice;
        double nb1, nb2;

        do {
            displayIHM.accept("");

            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 4) {
                System.out.println("Au revoir !");
                break;
            }

            if (choice < 0 || choice > 3) {
                System.out.println("Choix invalide. Essayez encore.\n");
                continue;
            }

            System.out.print("Nombre 1 : ");
            nb1 = sc.nextDouble();
            System.out.print("Nombre 2 : ");
            nb2 = sc.nextDouble();
            sc.nextLine();

            double result = calculatorMap.get(choice).calculate(nb1, nb2);
            System.out.println("Résultat : " + result + "\n");

        } while (true);

        sc.close();
    }
}