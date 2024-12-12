import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Merci de saisir un nombre entier : ");
            try {
                sc.nextInt();
                System.out.println("C'est un nombre entier, Merci !");
                break;
            }catch (InputMismatchException e){
                System.out.println("Désoler mais la saisie n'est pas un nombre entier.");
                sc.nextLine();
            }
        }while (true);
    }
}