import java.time.LocalDate;

public class Livre {
    private String titre;
    private String auteur;
    private String genre;
    private LocalDate datePublication;
    private int nbPages;
    private boolean estDisponible;
    private double prix;

    public Livre(String titre, String auteur, String genre, LocalDate datePublication, int nbPages, boolean estDisponible, double prix) {
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.datePublication = datePublication;
        this.nbPages = nbPages;
        this.estDisponible = estDisponible;
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public int getNbPages() {
        return nbPages;
    }

    public boolean isEstDisponible() {
        return estDisponible;
    }

    public double getPrix() {
        return prix;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", genre='" + genre + '\'' +
                ", datePublication=" + datePublication +
                ", nbPages=" + nbPages +
                ", estDisponible=" + estDisponible +
                ", prix=" + prix +
                '}';
    }
}
