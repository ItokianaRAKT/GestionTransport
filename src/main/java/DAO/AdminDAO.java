package DAO;
import java.sql.Connection ; 
import java.sql.DriverManager ;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner; 
import java.sql.*;
import java.util.Scanner;

public class AdminDAO {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/transport";
        String user = "heri";
        String password = "HEI";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre id : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // 👈 IMPORTANT : vide le \n laissé par nextInt()

        System.out.print("Entrez votre nom : ");
        String nom = scanner.nextLine();

        String query = "INSERT INTO admins (id, nom) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);        // ✅ setInt pour un int
            stmt.setString(2, nom);    // ✅ setString pour un String

            int lignesModifiees = stmt.executeUpdate();

            if (lignesModifiees > 0) {
                System.out.println("Admin ajouté avec succès !");
            } else {
                System.out.println("Aucune ligne insérée.");
            }

            System.out.println("Bonjour " + nom + ", id = " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
