package fr.ensai.library;

import java.util.ArrayList;
import java.util.List;

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
}

