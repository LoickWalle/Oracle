import interfaces.IEmployee;
import models.*;

public class Main {
    public static void main(String[] args) {
        IEmployee employee1 = new Regular("Bob", 30);
        IEmployee employee2 = new Regular("Greg", 20);
        IEmployee employee3 = new Regular("Ash", 18);

        Manager manager1 = new Manager("Roi Bob", 45);
        Manager manager2 = new Manager("Chef Momo", 50);

        manager1.add(employee1);
        manager1.add(manager2);
        manager2.add(employee2);
        manager2.add(employee3);

        manager1.showDetails();

    }
}