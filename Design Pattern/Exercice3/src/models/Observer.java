package models;

import interfaces.IObserver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Observer implements IObserver {
    private final String name;
    private String logFolderPath = "logs/";


    public Observer(String name){
        this.name = name;
    }

    @Override
    public void notification(String message) {
        String log = name + " have receive a notification : " + message;
        System.out.println(log);
        createLogFile();
        addLog(message);
    }

    public void addLog(String message){
        try {
            FileWriter myWriter = new FileWriter(logFolderPath + name + "_log.txt", true);
            myWriter.write(message+"\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void createLogFile(){
        try {
            File myObj = new File(logFolderPath + name + "_log.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}
