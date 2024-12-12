import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] tableau = {1,2,3,4,5};

        try{
            System.out.println(tableau[5]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Erreur : index hors limites");
        }
    }
}