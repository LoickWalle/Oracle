package models;

public class Book extends LibraryItem{
    private String author;
    private String genre;

    public Book(String title, int publicationYear, String author, String genre){
        super(title, publicationYear);
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String getDetails() {
        return "Book : [Title : " + super.title
                + ", Author : " + author
                + ", Genre : " + genre
                + ", Year : " + super.publicationYear;
    }
}
