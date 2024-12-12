package org.example.entities;

public class Book {
    private String name;
    private String author;
    private int publishYearDate;

    public Book(String name, String author, int publishYearDate) {
        this.name = name;
        this.author = author;
        this.publishYearDate = publishYearDate;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishYearDate() {
        return publishYearDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publishYearDate=" + publishYearDate +
                '}';
    }
}
