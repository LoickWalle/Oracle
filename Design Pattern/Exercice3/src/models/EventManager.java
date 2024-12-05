package models;

import interfaces.IObserver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    List<IObserver> observerList = new ArrayList<>();

    public EventManager(){
    }

    public void addObserver(IObserver observer){
        observerList.add(observer);
    }

    public void removeObserver(IObserver observer){
        observerList.remove(observer);
    }

    public void sendNotificationToAll(String message){
        for(IObserver observer : observerList){
            observer.notification(message);
        }
    }
}
