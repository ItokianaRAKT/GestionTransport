package TransportTerrestre;

import TransportTerrestre.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Se connecter");
            System.out.println("2. Créer un compte");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1" -> connecter(scanner);
                case "2" -> creerCompte(scanner);
                case "3" -> {
                    System.out.println("Au revoir !");
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

}
