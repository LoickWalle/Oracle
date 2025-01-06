package inventory;

import bookapi.Book;

import java.util.HashMap;
import java.util.Map;

public class BookInventory {
    private Map<Book, Integer> libraryBooks = new HashMap<>();

    public void addBook(Book book, int quantity) {
        if(libraryBooks.containsKey(book))
            libraryBooks.put(book, libraryBooks.get(book) + quantity);
        else
            libraryBooks.put(book, quantity);

        System.out.println("Ajouté " + quantity + " exemplaires de " + book.getTitle());
    }

    public void removeBook(Book book, int quantity) {
        if (libraryBooks.containsKey(book) && libraryBooks.get(book) >= quantity) {
            libraryBooks.put(book, libraryBooks.get(book) - quantity);
            System.out.println("Retiré " + quantity + " exemplaires de " + book.getTitle());
        } else {
            System.out.println("Pas assez de " + book);
        }
    }

    public Map<Book, Integer> getInventory() {
        return libraryBooks;
    }
}
