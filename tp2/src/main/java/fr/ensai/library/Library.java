package fr.ensai.library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private String name;
    private List<Book> books;

    // Constructeur
    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    // Ajouter un livre à la collection
    public void addBook(Book book) {
        books.add(book);
    }

    // Afficher tous les livres de la bibliothèque
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("La bibliothèque " + name + " ne contient aucun livre.");
        } else {
            System.out.println("Livres disponibles dans la bibliothèque " + name + " :");
            for (Book book : books) {
                System.out.println(book); // Appelle automatiquement book.toString()
            }
        }
    }
    /**
     * Loads books from a CSV file and adds them to the library.
     * 
     * @param filePath The path to the CSV file containing book data.
     * @throws IOException If there is an error reading the file, an
     *                     {@link IOException} will be thrown.
     */
    public void loadBooksFromCSV(String filePath) {

        URL url = getClass().getClassLoader().getResource(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(url.getFile()))) {
            Map<String, Author> authors = new HashMap<>();
            String line;
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 5) {
                    String isbn = data[0].trim();
                    String title = data[1].trim();
                    String authorName = data[2].trim();
                    int year = Integer.parseInt(data[3].trim());
                    int pageCount = Integer.parseInt(data[4].trim());

                    // Check if author already exists in the map
                    Author author = authors.get(authorName);
                    if (author == null) {
                        author = new Author(authorName);
                        authors.put(authorName, author);
                        System.out.println(author.toString());
                    }
                    Book book = new Book(isbn, title, author, year, pageCount);

                    this.addIem(book);
                }
            }
        } catch (

        IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}

