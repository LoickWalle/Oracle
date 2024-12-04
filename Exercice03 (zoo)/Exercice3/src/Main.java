import IHM.ConsoleMenu;
import Models.ZooManager;

public class Main {
    public static void main(String[] args) {
        ZooManager zooManager = new ZooManager();
        ConsoleMenu consoleMenu = new ConsoleMenu(zooManager);

        consoleMenu.displayMenu();
    }
}
