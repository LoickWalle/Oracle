package checkout;

import bookapi.Book;

import java.util.Map;

public class CheckoutSystem {
    Map<Book, Integer> borrowedBooks;

    public CheckoutSystem(Map<Book, Integer> inventoryBooks) {
        this.borrowedBooks = inventoryBooks;
    }

    public void borrowBook(Book book) {
        if (isAvailable(book)) {
            borrowedBooks.put(book, borrowedBooks.get(book) - 1);
            System.out.println("Le livre "+book.getTitle()+" a été emprunté.");
        } else {
            System.out.println("Le livre "+book.getTitle()+" n'est pas disponible.");
        }
    }

    public void retrieveBook(Book book){
        if(borrowedBooks.containsKey(book)){
            borrowedBooks.put(book, borrowedBooks.get(book) + 1);
            System.out.println("Le livre "+book.getTitle()+" a été rendu.");
        }
        else
            System.out.println("Livre non-reconnu !");

    }

    private boolean isAvailable(Book book) {
        return borrowedBooks.get(book) > 0;
    }
}
