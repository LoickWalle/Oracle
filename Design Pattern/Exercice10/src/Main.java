import interfaces.IPrestation;
import models.Event;
import models.ComplexPrestation;
import models.SimplePrestation;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Event japanExpo = new Event.Builder().name("Japan Expo").date(LocalDate.now()).location("Paris").build() ;
        System.out.println(japanExpo);

        IPrestation prestation1 = new SimplePrestation("Vente de goodies");
        IPrestation prestation2 = new SimplePrestation("Vente de manga");
        IPrestation prestation3 = new SimplePrestation("Stand de signature");

        ComplexPrestation complexPrestation1 = new ComplexPrestation("Stand");
        ComplexPrestation complexPrestation2 = new ComplexPrestation("Vente");

        complexPrestation1.addSimplePrestation(prestation3);
        complexPrestation1.addSimplePrestation(complexPrestation2);
        complexPrestation2.addSimplePrestation(prestation1);
        complexPrestation2.addSimplePrestation(prestation2);

        complexPrestation1.showDetails(0);

    }
}