import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Movie> movieList = createListFromCSV();

        if (movieList == null) {
            System.out.println("Erreur lors du chargement des films.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        do {
            displayIHM();

            System.out.print("Veuillez choisir une option : ");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    displayAllMoviesTitle(movieList);
                    break;
                case 2:
                    System.out.print("Enter gender : ");
                    displayMovieByGender(movieList, sc.nextLine());
                    break;
                case 3:
                    displayMovieTitleAfter20s(movieList);
                    break;
                case 4:
                    System.out.print("Enter realisator's name : ");
                    displayMovieByRealisator(movieList, sc.nextLine());
                    break;
                case 5:
                    display5MostSeenMovies(movieList);
                    break;
                case 6:
                    displaySortByReleaseDate(movieList);
                    break;
                case 7:
                    display10LessSeenMoviesSorted(movieList);
                    break;
                case 8:
                    displayAndRegroupNumberOfMovieByGender(movieList);
                    break;
                case 9:
                    displayAndRegroupMovieByRealisator(movieList);
                    break;
                case 10:
                    displaySumOfAllEntrance(movieList);
                    break;
                case 11:
                    displayGenderOfMostEntrance(movieList);
                    break;
                case 12:
                    displayAverageEntranceByRealisator(movieList, "Michael Webster");
                    break;
                case 13:
                    displayMovieDetails(movieList);
                    break;
                case 14:
                    displayUniqueGenres(movieList);
                    break;
                case 15:
                    displayFirstMovieByGender(movieList);
                    break;
                case 16:
                    displayRealisatorWith50Movies(movieList);
                    break;
                case 17:
                    displayMoviesNumberReleasesBetween2Date(movieList, 1990, 2000);
                    break;
                case 0:
                    System.out.println("Au revoir!");
                    sc.close();
                    return;
                default:
                    System.out.println("Choix invalide, essayez à nouveau.");
            }
        }while (true);
    }

    private static void displayIHM() {
        System.out.println("\nMenu:");
        System.out.println("1. Afficher tous les titres");
        System.out.println("2. Afficher les films par genre");
        System.out.println("3. Afficher les films après 2000");
        System.out.println("4. Afficher les films d'un réalisateur");
        System.out.println("5. Afficher les 5 films les plus vus");
        System.out.println("6. Afficher les films triés par date");
        System.out.println("7. Afficher les 10 films les moins vus");
        System.out.println("8. Regrouper par genre");
        System.out.println("9. Regrouper par réalisateur");
        System.out.println("10. Somme des entrées");
        System.out.println("11. Genre avec le plus d'entrées");
        System.out.println("12. Moyenne des entrées par réalisateur");
        System.out.println("13. Détails des films");
        System.out.println("14. Genres uniques");
        System.out.println("15. Premier film par genre");
        System.out.println("16. Réalisateurs ayant plus de 50 films");
        System.out.println("17. Films sortis entre 1990 et 2000");
        System.out.println("0. Quitter");
    }

    private static void displayMoviesNumberReleasesBetween2Date(List<Movie> movieList, int yearStart, int yearEnd) {
        LocalDate start = LocalDate.of(yearStart, 1, 1);
        LocalDate end = LocalDate.of(yearEnd, 12, 31);

        long count = movieList.stream()
                .filter(movie -> movie.getReleaseDate().isAfter(start) && movie.getReleaseDate().isBefore(end))
                .count();

        System.out.println("Le nombre de films parus entre " + yearStart + " et " + yearEnd + " est de : " + count);
    }

    private static void displayRealisatorWith50Movies(List<Movie> movieList) {
        movieList.stream()
                .collect(Collectors.groupingBy(
                        Movie::getRealisator,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 50)
                .forEach(entry -> System.out.println("Le réalisateur " + entry.getKey() + " a réalisé " + entry.getValue() + " films."));
    }

    private static void displayFirstMovieByGender(List<Movie> movieList) {
        movieList.stream()
                .collect(Collectors.groupingBy(
                        Movie::getGender,
                        Collectors.minBy(Comparator.comparing(Movie::getReleaseDate))
                ))
                .forEach((genre, movieOptional) ->
                    movieOptional.ifPresent(movie ->
                            System.out.println("Le premier film du genre " + genre + " est : " + movie.getTitle() + " - " + movie.getReleaseDate())
                    ));
    }

    private static void displayUniqueGenres(List<Movie> movieList) {
        movieList.stream()
                .map(Movie::getGender)
                .distinct()
                .forEach(System.out::println);
    }

    private static void displayMovieDetails(List<Movie> movieList) {
        movieList.stream()
                .map(movie -> movie.getTitle() + " (" + movie.getGender() + ") - Réalisé par " + movie.getRealisator() + " en " + movie.getReleaseDate().getYear())
                .forEach(System.out::println);
    }


    private static void displayAverageEntranceByRealisator(List<Movie> movieList, String realisator) {
        movieList.stream()
                .filter(movie -> movie.getRealisator().contains(realisator))
                .mapToDouble(Movie::getEntranceNumber)
                .average()
                .ifPresentOrElse(
                        avg -> System.out.println("La moyenne des entrées pour " + realisator + " est : " + avg),
                        () -> System.out.println("Aucun film trouvé pour le réalisateur " + realisator)
                );
    }

    private static void displayGenderOfMostEntrance(List<Movie> movieList) {
        String maxGenre = movieList.stream()
                .collect(Collectors.groupingBy(
                        Movie::getGender,
                        Collectors.summingLong(Movie::getEntranceNumber)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown Genre");

        System.out.println("Le genre avec le plus d'entrées est : " + maxGenre);
    }

    private static void displaySumOfAllEntrance(List<Movie> movieList) {
        long totalEntrances = movieList.stream()
                .mapToLong(Movie::getEntranceNumber)
                .sum();

        System.out.println("La somme de toutes les entrées est de : " + totalEntrances);
    }

    private static void displayAndRegroupMovieByRealisator(List<Movie> movieList) {
        movieList.stream()
                .collect(Collectors.groupingBy(
                        Movie::getRealisator,
                        Collectors.mapping(Movie::getTitle, Collectors.toList())))
                .forEach((realisator, titles) -> {
                    System.out.println("Réalisateur: " + realisator);
                    titles.forEach(title -> System.out.println("  - " + title));
                });
    }

    private static void displayAndRegroupNumberOfMovieByGender(List<Movie> movieList) {
        movieList.stream()
                .collect(Collectors.groupingBy(Movie::getGender, Collectors.counting()))
                .forEach((genre, count) -> System.out.println("Genre: " + genre + " - " + count + " films"));
    }

    private static void display10LessSeenMoviesSorted(List<Movie> movieList) {
        System.out.println("Les 10 films avec le plus petit nombre d'entrées : ");
        movieList.stream()
                .sorted(Comparator.comparingLong(Movie::getEntranceNumber))
                .limit(10)
                .forEach(movie -> System.out.println(" - " + movie.getTitle() + "(" + movie.getEntranceNumber() + ")"));
    }

    private static void displaySortByReleaseDate(List<Movie> movieList) {
        System.out.println("Les films triés par date de parution: ");
        movieList.stream()
                .sorted(Comparator.comparing(Movie::getReleaseDate))
                .forEach(movie -> System.out.println(" - " + movie.getTitle() + "(" + movie.getReleaseDate() + ")"));
    }

    private static void display5MostSeenMovies(List<Movie> movieList) {
        System.out.println("Les 5 films avec le plus d'entrées : ");
        movieList.stream()
                .sorted(Comparator.comparingInt(Movie::getEntranceNumber).reversed())
                .limit(5)
                .forEach(movie -> System.out.println(" - " + movie.getTitle()));
    }

    private static void displayMovieByRealisator(List<Movie> movieList, String realisatorSearched) {
        movieList.stream()
                .map(Movie::getRealisator)
                .filter(realisator -> realisator.contains(realisatorSearched))
                .distinct()
                .findFirst()
                .ifPresentOrElse(realisator -> {
                    System.out.println("Films réalisés par " + realisator + " : ");
                    movieList.stream()
                            .filter(movie -> movie.getRealisator().equals(realisator))
                            .forEach(movie -> System.out.println(" - " + movie.getTitle()));
                }, () -> System.out.println("Nom du réalisateur non existant..."));
    }

    private static void displayMovieTitleAfter20s(List<Movie> movieList) {
        System.out.println("Films parus après 2000 : ");
        movieList.stream()
                .filter(movie -> movie.getReleaseDate().isAfter(LocalDate.of(2000, 12, 31)))
                .forEach(movie -> System.out.println(" - " + movie.getTitle()));
    }

    private static void displayMovieByGender(List<Movie> movieList, String genderSearched) {
        movieList.stream()
                .map(Movie::getGender)
                .distinct()
                .filter(gender -> gender.contains(genderSearched))
                .findFirst()
                .ifPresentOrElse(gender -> {
                    System.out.println("Films du genre " + gender + " :");
                    movieList.stream()
                            .filter(movie -> movie.getGender().equals(gender))
                            .forEach(movie -> System.out.println("'" + movie.getTitle() + "' paru le : " + movie.getReleaseDate()));
                }, () -> System.out.println("Nom du genre non existant..."));
    }

    private static void displayAllMoviesTitle(List<Movie> movieList) {
        System.out.println("Affichage de tout les titres de films : ");
        movieList.forEach(movie -> System.out.println(" - " + movie.getTitle()));
    }

    private static List<Movie> createListFromCSV() throws IOException {
        List<List<String>> records = new ArrayList<>();
        List<Movie> movieList = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/films_with_genres 1.csv"))) {
            records = reader.lines()
                    .skip(1)  // Skip header
                    .map(line -> Arrays.asList(line.split(",")))
                    .toList();

            if (records.isEmpty()) {
                return Collections.emptyList();
            }

            System.out.println("Lecture du fichier réussi !");

            movieList = records.stream()
                    .map(ele -> new Movie(
                            ele.get(0), // Title
                            LocalDate.parse(ele.get(1)), // Release Date
                            ele.get(2), // Gender
                            ele.get(3), // Realisator
                            Integer.parseInt(ele.get(4)) // Entrance Number
                    ))
                    .collect(Collectors.toList());

            System.out.println("Voici les 10 premiers films : ");
            movieList.stream().limit(10).forEach(System.out::println);

            return movieList;
        }
    }


}