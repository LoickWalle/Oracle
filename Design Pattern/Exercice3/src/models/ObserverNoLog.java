package models;

import interfaces.IObserver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ObserverNoLog implements IObserver {
    private final String name;
    private String logFolderPath = "logs/";


    public ObserverNoLog(String name){
        this.name = name;
    }

    @Override
    public void notification(String message) {
        String log = name + " have receive a notification : " + message;
        System.out.println(log);
    }

}
