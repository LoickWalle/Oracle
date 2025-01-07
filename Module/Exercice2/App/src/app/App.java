package app;

import bookapi.Book;
import checkout.CheckoutSystem;
import inventory.BookInventory;
import reports.InventoryReport;
import service.impl.email.EmailNotification;


public class App {
    public static void main(String[] args) {
        Book book1 = new Book( "The witcher - The last wish", "Sapkowski Andrzej");
        Book book2 = new Book( "The witcher - Sword of destiny", "Sapkowski Andrzej");
        Book book3 = new Book( "The witcher - Blood of elves", "Sapkowski Andrzej");

        BookInventory inventory = new BookInventory();
        inventory.addBook(book1, 10);
        inventory.addBook(book2, 5);
        inventory.addBook(book3, 1);

        CheckoutSystem checkoutSystem = new CheckoutSystem(inventory.getInventory());
        checkoutSystem.borrowBook(book1);
        checkoutSystem.borrowBook(book3);
        checkoutSystem.retrieveBook(book1);

        InventoryReport report = new InventoryReport(inventory);
        report.generateReport();

        EmailNotification emailNotification = new EmailNotification();
        emailNotification.sendNotification("je suis la notification !");
    }
}
