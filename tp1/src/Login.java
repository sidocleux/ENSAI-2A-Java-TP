import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

public class Login {
    private static final int MAX_ATTEMPTS = 3; // Nombre maximal d'essais

    public static void main(String[] args) {
        HashMap<String, String> userDatabase = loadUserDatabase("../data/user_hashpwd.csv");
        Scanner scanner = new Scanner(System.in);

        while (true) { // Boucle principale du login
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();

            if (userDatabase.containsKey(username)) { // Vérifie si l'utilisateur existe
                int attempts = 0;
                while (attempts < MAX_ATTEMPTS) {
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine().trim();

                    // Hachage du mot de passe entré
                    String hashedPassword = hashPassword(password);

                    // Vérification du mot de passe haché
                    if (hashedPassword.equals(userDatabase.get(username))) {
                        System.out.println("Login successful!");
                        scanner.close();
                        return; // Quitte après un login réussi
                    } else {
                        attempts++;
                        System.out.println("Incorrect password. Attempts left: " + (MAX_ATTEMPTS - attempts));
                    }
                }
                System.out.println("Too many failed attempts. Restarting login...\n");
            } else {
                System.out.println("Username not found. Try again.\n");
            }
        }
    }

    /**
     * Loads a user database from a CSV file.
     * The CSV file is expected to have two columns: username and hashed password.
     * 
     * @param filename The path to the CSV file containing user data.
     * @return A HashMap where keys are usernames and values are hashed passwords.
     */
    public static HashMap<String, String> loadUserDatabase(String filename) {
        HashMap<String, String> userDatabase = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Ignore l'en-tête du CSV
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userDatabase.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return userDatabase;
    }

    /**
     * Hashes a password using SHA-256.
     * 
     * @param password The plain text password.
     * @return The hashed password as a hex string.
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
