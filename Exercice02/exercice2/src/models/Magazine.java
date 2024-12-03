package models;

public class Magazine extends LibraryItem{
    private int issueNumber;

    public Magazine(String title, int publicationYear, int issueNumber){
        super(title, publicationYear);
        this.issueNumber = issueNumber;
    }

    @Override
    public String getDetails() {
        return super.id + " - Magazine : [Title : " + super.title
                + ", Number : " + issueNumber
                + ", Year : " + super.publicationYear;
    }
}
