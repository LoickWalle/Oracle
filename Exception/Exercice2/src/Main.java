import exceptions.NotPositiveException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double sqrt;
        int input;
        do {
            System.out.print("Merci de saisir un nombre entier positif : ");
            try {
                input = sc.nextInt();
                sqrt = calculateRootSquare(input);
                System.out.println("La racine carrée de "+input+" est : " + sqrt);
                break;
            }catch (InputMismatchException e){
                System.out.println("Désoler mais la saisie n'est pas un nombre entier.");
                sc.nextLine();
            } catch (NotPositiveException e){
                System.out.println(e.getMessage());
            }


        }while (true);
    }

    public static double calculateRootSquare(int input) {
        if(input < 0)
            throw new NotPositiveException("Le nombre n'est pas positif !");

        return Math.sqrt(input);
    }
}