import interfaces.ICommand;
import models.*;

public class Main {
    public static void main(String[] args) {
//        IComponent file1 = new File("fichier 1");
//        IComponent file2 = new File("fichier 2");
//        IComponent file3 = new File("fichier 3");
//
//        Folder folder1 = new Folder("Folder 1");
//        Folder folder2 = new Folder("Folder 2");
//
//        folder1.add(file1);
//        folder1.add(folder2);
//        folder2.add(file2);
//        folder2.add(file3);
//
//        folder1.operation();

        Light light = new Light();
        ICommand lightOn = new LightOnCommand(light);
        ICommand lightOff = new LightOffCommand(light);
        RemoteControl remoteControl = new RemoteControl(lightOn, lightOff);

        remoteControl.pressButton();
        remoteControl.pressButton();
    }
}