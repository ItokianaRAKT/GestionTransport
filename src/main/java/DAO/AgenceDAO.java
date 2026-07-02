package DAO;
import java.util.Scanner;
import java.sql.*;
import java.util.Scanner;


public class AgenceDAO {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgenceDAO dao = new AgenceDAO(); 

        System.out.println("=== Menu Agence ===");
        System.out.println("1. Vérifier une agence");
        System.out.println("2. Supprimer une agence");
        System.out.println("3. Changer le nom d'une agence");
        System.out.println("4. Creez une Agence ");
        System.out.print("Choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // vider le buffer

        switch (choix) {
            case 1 -> {
                System.out.print("ID à vérifier : ");
                int id = scanner.nextInt();

                boolean existe = dao.checkerAgence(id); // 👈 appel de la méthode
                if (existe) {
                    System.out.println("L'agence existe !");
                } else {
                    System.out.println("Agence introuvable.");
                }
            }

            case 2 -> {
                System.out.print("ID à supprimer : ");
                int id = scanner.nextInt();

                boolean succes = dao.deleteAgence(id); // 👈 appel de la méthode
                System.out.println(succes ? "Supprimée !" : "Échec.");
            }

            case 3 -> {
                System.out.print("ID de l'agence : ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Nouveau nom : ");
                String nouveauNom = scanner.nextLine();

                boolean succes = dao.changerNomAgence(id, nouveauNom); // 👈 appel
                System.out.println(succes ? "Nom modifié !" : "Échec.");
            }
            case 4 -> {
                System.out.print("ID de l'agence : ");
                int id = scanner.nextInt();
                scanner.nextLine(); // vide le \n après nextInt() ✅

                System.out.print("Nom de l'Agence : ");
                String nom = scanner.nextLine(); // ✅ pas de nextLine() en trop après

                System.out.print("ID du boss : ");
                int idBoss = scanner.nextInt();
                scanner.nextLine(); // vide le \n après nextInt() ✅

                boolean succes = dao.creerAgence(id, nom, idBoss);
                System.out.println(succes ? "Agence créée avec succès !" : "Échec de la création.");
            }

            default -> System.out.println("Choix invalide.");
        }

        scanner.close();
    }





    public boolean creerAgence(int id, String nom, int chefId) {
        String url = "jdbc:postgresql://localhost:5432/transport"; // ✅
        String user = "heri";
        String password = "HEI";
    String queryCheckBoss = "SELECT COUNT(*) FROM admins WHERE id = ?";
    String queryInsert = "INSERT INTO agences (id, nom, chef_id) VALUES (?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(url,user,password)) {

        // 1. Vérifier que le boss existe
        boolean bossExiste = false;
        try (PreparedStatement checkStmt = conn.prepareStatement(queryCheckBoss)) {
            checkStmt.setInt(1, chefId);

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    bossExiste = true;
                }
            }
        }

        if (!bossExiste) {
            System.out.println("Erreur : L'ID du boss (" + chefId + ") n'existe pas.");
            return false; // 👈 on sort ici avec false
        }

        // 2. Insérer l'agence
        try (PreparedStatement insertStmt = conn.prepareStatement(queryInsert)) {
            insertStmt.setInt(1, id);
            insertStmt.setString(2, nom);
            insertStmt.setInt(3, chefId);

            return insertStmt.executeUpdate() > 0; // 👈 true si insertion réussie
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false; // 👈 en cas d'erreur SQL
    }
}
    
    public boolean checkerAgence(int id) {
        String url = "jdbc:postgresql://localhost:5432/transport"; // ✅
        String user = "heri";
        String password = "HEI";
    String query = "SELECT * FROM admins WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(url,user,password);
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        return rs.next();  // true si une ligne existe, false sinon

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean deleteAgence(int id) {
        String url = "jdbc:postgresql://localhost:5432/transport"; // ✅
        String user = "heri";
        String password = "HEI";
    String query = "DELETE FROM agences WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(url,user,password);
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, id);
        int lignesSupprimees = stmt.executeUpdate();

        return lignesSupprimees > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean changerNomAgence(int id, String nouveauNom) {
        String url = "jdbc:postgresql://localhost:5432/transport"; // ✅
        String user = "heri";
        String password = "HEI";
    String query = "UPDATE agences SET nom = ? WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(url,user,password);
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, nouveauNom);
        stmt.setInt(2, id);

        int lignesModifiees = stmt.executeUpdate();
        return lignesModifiees > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}