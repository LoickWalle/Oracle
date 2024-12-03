import models.Book;
import models.BorrowRecord;
import models.LibraryItem;
import models.Magazine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<LibraryItem> library = new ArrayList<>();
    static List<BorrowRecord> borrowRecordList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        initLibrary();
        System.out.println("\nWelcome to the jungle library !");
        do{
            System.out.print("""
            Select an option :
            1) Add an element
            2) Display all elements
            3) Borrow an element
            4) Display available book
            5) Quit
            
            Your choice :
            """);

            int option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1 -> createLibraryItem();
                case 2 -> displayAllElements();
                case 3 -> borrowElement();
                case 4 -> displayItemStatus();
            }

            if(option == 5){
                System.out.println("Thanks for using the library !");
                break;
            }

        }while(true);
    }

    public static void displayAllElements(){
        System.out.println("\nAll elements : ");
        for(LibraryItem libraryItem : library){
            System.out.println(libraryItem.getId() + " - " + libraryItem.getDetails());
        }
        System.out.println();
    }

    public static void displayItemStatus(){
        displayAvailableItems();
        displayBorrowedItems();
    }

    public static void displayAvailableItems(){
        System.out.println("Available items : ");
        for(LibraryItem libraryItem : library){
            LibraryItem libraryAvailable = null;

            for(BorrowRecord record : borrowRecordList){
                LibraryItem libraryItemFound = getItemInLibraryById(record.itemId());
                if(libraryItemFound == null)
                    System.out.println(libraryItem.getDetails());
            }

        }
    }

    public static void displayBorrowedItems(){
        System.out.println("Borrowed items : ");
        for(BorrowRecord record : borrowRecordList){
            LibraryItem libraryItem = getItemInLibraryById(record.itemId());
            if(libraryItem != null)
                System.out.println(libraryItem.getDetails());
        }
    }

    public static void borrowElement(){
        displayAvailableItems();

        System.out.println("Which item to you want to borrow ?");

        int idChoiced = sc.nextInt();
        sc.nextLine();

    }

    public static void createLibraryItem(){
        String title;
        int publicationYear;
        LibraryItem libraryItem = null;

        System.out.print("""
                What do you want to create :
                1) Book ?
                2) Magazine ?
                
                Your choice :
                """);

        int option = sc.nextInt();
        sc.nextLine();

        System.out.println("Title : ");
        title = sc.nextLine();
        System.out.println("Publication year : ");
        publicationYear = sc.nextInt();
        sc.nextLine();

        switch (option){
            case 1 :
                System.out.println("Author : ");
                String author = sc.nextLine();
                System.out.println("Genre : ");
                String genre = sc.nextLine();
                libraryItem = new Book(title, publicationYear, author, genre);
                break;
            case 2 :
                System.out.println("Number : ");
                int number = sc.nextInt();
                sc.nextLine();
                libraryItem = new Magazine(title, publicationYear, number);
                break;
        }

        if(libraryItem != null)
            addItemToLibrary(libraryItem);

    }

    public static void initLibrary(){
        Book book = new Book("Coder pour les nuls", 2024, "Pr.Hashtag", "SF");
        Magazine magazine = new Magazine("Coder pour les nuls", 2024, 32);
        addItemToLibrary(book);
        addItemToLibrary(magazine);
    }

    public static void addItemToLibrary(LibraryItem libraryItem){
        libraryItem.setId(library.size()+1);
        library.add(libraryItem);
        System.out.println("Element added to the library...");
    }

    public static LibraryItem getItemInLibraryById(int id){
        for(LibraryItem libraryItem : library){
            if(libraryItem.getId() == id)
                return libraryItem;
        }
        return null;
    }
}