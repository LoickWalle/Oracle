package reports;

import inventory.BookInventory;

public class InventoryReport {
    private BookInventory bookInventory;

    public InventoryReport(BookInventory bookInventory) {
        this.bookInventory = bookInventory;
    }

    public void generateReport() {
        System.out.println("Rapport d'inventaire :");
        bookInventory.getInventory().forEach((book, quantity) -> {
            System.out.println(book.getTitle() +" de " + book.getAuthor() + " : " + quantity + " exemplaires");
        });
    }
}
