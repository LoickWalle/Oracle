import enums.Message;
import interfaces.Calculator;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        double nb1, nb2;

        Map<Integer, Calculator<Double>> calculatorMap = Map.of(
            0, (nbr1, nbr2) -> nbr1 + nbr2,
            1, (nbr1, nbr2) -> nbr1 - nbr2,
            2, (nbr1, nbr2) -> nbr1 * nbr2,
            3, (nbr1, nbr2) -> nbr2 != 0 ? nbr1 / nbr2 : Double.NaN
        );

        Consumer<Message> displayIHM = input -> {
            switch (input){
                case MENU :
                    displayMenu();
                    break;
                case BYE:
                    System.out.println("Au revoir !");
                    break;
                case INVALID:
                    System.out.println("Choix invalide. Essayez encore.\n");
                    break;
            }
        };

        do {
            displayIHM.accept(Message.MENU);

            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 4) {
                displayIHM.accept(Message.BYE);
                break;
            }

            if (!calculatorMap.containsKey(choice)) {
                displayIHM.accept(Message.INVALID);
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

    private static void displayMenu() {
        System.out.println("Quelle opération effectuer ?");
        System.out.println("0. Addition");
        System.out.println("1. Soustraction");
        System.out.println("2. Multiplication");
        System.out.println("3. Division");
        System.out.println("4. Quitter");
        System.out.print("Votre choix : ");
    }
}