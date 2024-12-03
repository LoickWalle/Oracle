import models.Book;
import models.BorrowRecord;
import models.LibraryItem;
import models.Magazine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<LibraryItem> library = new ArrayList<>();
    private static final List<BorrowRecord> borrowRecordList = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initLibrary();
        System.out.println("\nWelcome to the Jungle Library!");

        while (true) {
            displayMainMenu();

            int option = getUserNumberInput();

            if (option == 5) {
                System.out.println("Thanks for using the library!");
                break;
            }

            menuOption(option);
        }
    }

    private static void displayMainMenu() {
        System.out.print("""
            
            Select an option:
            1) Add an element
            2) Display all elements
            3) Borrow an element
            4) Display item status
            5) Quit
            Your choice: """);
    }

    private static int getUserNumberInput() {
        int option = sc.nextInt();
        sc.nextLine();  // Consume the newline
        return option;
    }

    private static void menuOption(int option) {
        switch (option) {
            case 1 -> createLibraryItem();
            case 2 -> displayAllElements();
            case 3 -> borrowElement();
            case 4 -> displayItemStatus();
        }
    }

    private static void displayAllElements() {
        System.out.println("\nAll elements:");
        for (LibraryItem libraryItem : library) {
            System.out.println(libraryItem.getDetails());
        }
    }

    private static void displayItemStatus() {
        displayAvailableItems();
        displayBorrowedItems();
    }

    private static void displayAvailableItems() {
        System.out.println("\nAvailable items:");
        for (LibraryItem libraryItem : library) {
            if (isItemAvailable(libraryItem)) {
                System.out.println(libraryItem.getDetails());
            }
        }
    }

    private static boolean isItemAvailable(LibraryItem libraryItem) {
        for (BorrowRecord record : borrowRecordList) {
            if (libraryItem.getId() == record.itemId()) {
                return false;
            }
        }
        return true;
    }

    private static void displayBorrowedItems() {
        System.out.println("\nBorrowed items:");
        for (BorrowRecord record : borrowRecordList) {
            LibraryItem libraryItem = getItemInLibraryById(record.itemId());
            if (libraryItem != null) {
                System.out.println(libraryItem.getDetails());
            }
        }
    }

    private static void borrowElement() {
        displayAvailableItems();

        System.out.println("Which item do you want to borrow?");
        int idChoose = sc.nextInt();
        sc.nextLine();

        System.out.println("What's the borrower's name?");
        String borrowerName = sc.nextLine();

        borrowRecordList.add(new BorrowRecord(idChoose, borrowerName, LocalDate.now().toString()));
    }

    private static void createLibraryItem() {
        System.out.print("""
            What do you want to create:
            1) Book
            2) Magazine
            Your choice: """);

        int option = getUserNumberInput();
        System.out.println("Title: ");
        String title = sc.nextLine();

        System.out.println("Publication year: ");
        int publicationYear = sc.nextInt();
        sc.nextLine();

        LibraryItem libraryItem = null;

        switch (option) {
            case 1 -> libraryItem = createBook(title, publicationYear);
            case 2 -> libraryItem = createMagazine(title, publicationYear);
        }

        if (libraryItem != null) {
            addItemToLibrary(libraryItem);
        }
    }

    private static Book createBook(String title, int publicationYear) {
        System.out.println("Author: ");
        String author = sc.nextLine();

        System.out.println("Genre: ");
        String genre = sc.nextLine();

        return new Book(title, publicationYear, author, genre);
    }

    private static Magazine createMagazine(String title, int publicationYear) {
        System.out.println("Number: ");
        int number = sc.nextInt();
        sc.nextLine();  // Consume newline

        return new Magazine(title, publicationYear, number);
    }

    private static void addItemToLibrary(LibraryItem libraryItem) {
        libraryItem.setId(library.size() + 1);
        library.add(libraryItem);
        System.out.println("Element added to the library...");
    }

    private static LibraryItem getItemInLibraryById(int id) {
        return library.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private static void initLibrary() {
        Book book = new Book("Coder pour les nuls", 2024, "Pr.Hashtag", "SF");
        Magazine magazine = new Magazine("Coder pour les nuls", 2024, 32);
        addItemToLibrary(book);
        addItemToLibrary(magazine);
    }
}
