package fr.ensai.library;

import java.util.Objects;
import java.util.Random;

/**
 * Represents an Author.
 */
public class Author extends Person {

    // Attributes
    private String nationality;

    /**
     * Constructs a new Book object.
     */
    public Author(String name, int age, String nationality) {
        super(name, age);
        this.nationality = nationality;
    }

    public Author(String name) {
        super(name, new Random().nextInt(150));
    }

    public String getName() {
        return this.name;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two authors are considered equal if their names are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Author author = (Author) obj;
        return Objects.equals(name, author.name);
    }

    @Override
    public String toString() {
        return "Author " + name;
    }

}
