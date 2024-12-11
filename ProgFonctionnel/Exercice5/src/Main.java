import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Livre> bookList = createListFromCSV();

        if (bookList == null) {
            System.out.println("Erreur lors du chargement des livres.");
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
                    displayAllAvailableBooks(bookList);
                    break;
                case 2:
                    displayBookPublishedBefore50s(bookList);
                    break;
                case 3:
                    displayBookGroupByGender(bookList);
                    break;
                case 4:
                    displayOldestBook(bookList);
                    break;
                case 5:
                    System.out.print("Enter start of title : ");
                    displayBookStartingWith(bookList, sc.nextLine());
                    break;
                case 6:
                    displayBooksAveragePrice(bookList);
                    break;
                case 7:
                    displayBooksByPagesNumber(bookList);
                    break;
                case 8:
                    System.out.print("Enter gender : ");
                    displayBooksPagesSumByGender(bookList, sc.nextLine());
                    break;
                case 9:
                    displayMostExpensiveBook(bookList);
                    break;
                case 10:
                    displayAuthorWithMostPublification(bookList);
                    break;
                case 11:
                    displayBookNumberByGender(bookList);
                    break;
                case 12:
                    System.out.print("Enter price : ");
                    displayAvailableBookInferiorToGivenPrice(bookList, sc.nextDouble());
                    sc.nextLine();
                    break;
                case 13:
                    displayBookPagesNumberByYear(bookList);
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
        System.out.println("1. Afficher tous les titres disponibles");
        System.out.println("2. Afficher les livres publiées avant 1950");
        System.out.println("3. Afficher les livres groupé par genre");
        System.out.println("4. Afficher le livre le plus ancien");
        System.out.println("5. Afficher les livres commençant par un mot");
        System.out.println("6. Afficher le prix moyen des livres");
        System.out.println("7. Afficher les livres triés par le nombre de pages");
        System.out.println("8. Afficher le total des pages pour un genre donné");
        System.out.println("9. Afficher le livre disponible le plus cher");
        System.out.println("10. Afficher les auteurs les plus prolifiques");
        System.out.println("11. Afficher le nombre de livre par genre");
        System.out.println("12. Afficher les livres disponible dont le prix est inférieur à un nombre donné");
        System.out.println("13. Afficher le nombre total des pages par année");
        System.out.println("0. Quitter");
    }

    private static void displayBookPagesNumberByYear(List<Livre> bookList) {
        System.out.println("Affichage du nombre de pages par années : ");
        bookList.stream()
                .collect(Collectors.groupingBy(livre -> livre.getDatePublication().getYear()))
                .forEach((year, bookList1) -> {
                    System.out.print("Année " + year + " : ");
                    System.out.println(bookList1.stream().mapToInt(Livre::getNbPages).sum());
                });
    }


    private static void displayAvailableBookInferiorToGivenPrice(List<Livre> bookList, double price) {
        System.out.println("Affichage des livres dont le prix est inférieur à "+ price + " : ");
        bookList.stream()
                .filter(Livre::isEstDisponible)
                .filter(livre -> livre.getPrix() < price)
                .forEach(livre -> System.out.println(" - " + livre.getTitre() + " (" + livre.getPrix() + "€)"));
    }

    private static void displayBookNumberByGender(List<Livre> bookList) {
        bookList.stream()
                .collect(Collectors.groupingBy(Livre::getGenre))
                .forEach((gender, bookList1) -> {
                    System.out.println("Livres du genre " + gender + " : " + bookList1.size());
                });
    }

    private static void displayAuthorWithMostPublification(List<Livre> bookList) {
        System.out.println("Affichage des auteurs ayant été les plus prolifiques : ");
        bookList.stream()
                .collect(Collectors.groupingBy(Livre::getAuteur))
                .forEach((author, bookPublished) -> {
                    if(bookPublished.size() > 17){
                        System.out.println(author);
                        bookPublished.forEach(livre -> System.out.println(" - " + livre.getTitre()));
                    }
                });
    }

    private static void displayMostExpensiveBook(List<Livre> bookList) {
        bookList.stream()
                .filter(Livre::isEstDisponible)
                .mapToDouble(Livre::getPrix)
                .max()
                .ifPresentOrElse(value ->
                        System.out.println("Le livre disponible le plus cher coute : " + String.format("%.2f", value)),
                        () -> System.out.println("Aucun prix trouvé...")
                );

    }

    private static void displayBooksPagesSumByGender(List<Livre> bookList, String gender) {
        int pagesSum = bookList.stream()
                .filter(livre -> livre.getGenre().contains(gender))
                .mapToInt(Livre::getNbPages)
                .sum();

        System.out.println("La somme des pages du genre "+gender+" est " + pagesSum);
    }

    private static void displayBooksByPagesNumber(List<Livre> bookList) {
        System.out.println("Affichages de tout les livres triés par le nombre de pages : ");
        bookList.stream()
                .sorted(Comparator.comparingLong(Livre::getNbPages).thenComparingDouble(Livre::getPrix))
                .forEach(livre -> System.out.println(" - " + livre.getTitre() + " (" + livre.getNbPages() + " pages)"));
    }

    private static void displayBooksAveragePrice(List<Livre> bookList) {
        System.out.print("Le prix moyen des livres de la bibliothèque est : ");
        System.out.println(
                bookList.stream()
                        .mapToDouble(Livre::getPrix)
                        .average()
                        .getAsDouble()
        );
    }

    private static void displayBookStartingWith(List<Livre> bookList, String title) {
        System.out.println("Livres commençant par "+ title +" : ");
        bookList.stream()
                .filter(book -> book.getTitre().startsWith(title))
                .forEach(book -> System.out.println(" - " + book.getTitre()));
    }

    private static void displayOldestBook(List<Livre> bookList) {
        bookList.stream()
                .min(Comparator.comparing(Livre::getDatePublication))
                .ifPresentOrElse(
                        livre -> System.out.println("Le livre le plus vieux est : " + livre.getTitre()),
                        () -> System.out.println("Aucun livre trouvé...")
                );
    }

    private static void displayBookGroupByGender(List<Livre> bookList) {
        bookList.stream()
                .collect(Collectors.groupingBy(Livre::getGenre))
                .forEach((gender, books) -> {
                    System.out.println("\nLivres du genre " + gender + " : ");
                    books.forEach(book -> System.out.println(" - " + book.getTitre()));
                });
    }

    private static void displayBookPublishedBefore50s(List<Livre> bookList) {
        System.out.println("Livres publié avant 1950 : ");
        bookList.stream()
                .filter(book -> book.getDatePublication().isBefore(LocalDate.of(1950, 01, 01)))
                .forEach(book -> System.out.println(" - '" + book.getTitre() + "' (" + book.getDatePublication()+")"));
    }

    private static void displayAllAvailableBooks(List<Livre> bookList) {
        System.out.println("Affichage de tout les livres disponibles : ");
        bookList.stream()
                .filter(Livre::isEstDisponible)
                .forEach(livre -> System.out.println(" - " + livre.getTitre() + " ("+livre.isEstDisponible()+")"));
    }

    private static List<Livre> createListFromCSV() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/books_dataset.csv"))) {
            List<List<String>> records = reader.lines()
                    .skip(1)  // Skip header
                    .map(line -> Arrays.asList(line.split(",")))
                    .toList();

            if (records.isEmpty()) {
                return Collections.emptyList();
            }

            System.out.println("Lecture du fichier réussi !");

            return records.stream()
                    .map(ele -> new Livre(
                            ele.get(0),
                            ele.get(1),
                            ele.get(2),
                            LocalDate.parse(ele.get(3)),
                            Integer.parseInt(ele.get(4)),
                            Boolean.parseBoolean(ele.get(5)),
                            Double.parseDouble(ele.get(6))
                    ))
                    .toList();
        }
    }


}