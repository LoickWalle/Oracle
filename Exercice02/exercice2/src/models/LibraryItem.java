package models;

public abstract class LibraryItem {
    protected int id;
    protected String title;
    protected int publicationYear;

    public LibraryItem(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public abstract String getDetails();

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
