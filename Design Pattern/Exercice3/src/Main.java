import models.EventManager;
import models.Observer;
import models.ObserverNoLog;

public class Main {
    public static void main(String[] args) {
        Observer observer1 = new Observer("toto");
        Observer observer2 = new Observer("tata");
        ObserverNoLog observer3 = new ObserverNoLog("titi");

        EventManager eventManager = new EventManager();

        eventManager.addObserver(observer1);
        eventManager.addObserver(observer2);
        eventManager.addObserver(observer3);

        eventManager.sendNotificationToAll("Squalala !");

        eventManager.removeObserver(observer1);

        eventManager.sendNotificationToAll("Scoobidoo-bidou !");

    }
}

//Exercice : Observer - Notifications d'événements
//
//Objectif :
//Utilisez le pattern Observer pour créer un système de notification d'événements.
//        1. Implémentez une classe `EventManager` comme sujet, permettant d'ajouter et de notifier des observateurs.
//        2. Implémentez plusieurs observateurs, chacun ayant une réaction spécifique à une notification.
//        3. Testez en simulant des événements dans une classe principale.