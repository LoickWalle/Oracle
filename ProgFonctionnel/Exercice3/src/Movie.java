import java.time.LocalDate;

public class Movie {
    private String title;
    private LocalDate releaseDate;
    private String realisator;
    private String gender;
    private int entranceNumber;

    public Movie(String title, LocalDate releaseDate, String realisator, String gender, int entranceNumber) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.realisator = realisator;
        this.gender = gender;
        this.entranceNumber = entranceNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRealisator() {
        return realisator;
    }

    public void setRealisator(String realisator) {
        this.realisator = realisator;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getEntranceNumber() {
        return entranceNumber;
    }

    public void setEntranceNumber(int entranceNumber) {
        this.entranceNumber = entranceNumber;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", realisator='" + realisator + '\'' +
                ", gender='" + gender + '\'' +
                ", entranceNumber=" + entranceNumber +
                '}';
    }
}
