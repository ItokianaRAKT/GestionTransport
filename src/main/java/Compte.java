import java.util.UUID;

public class Compte {
    UUID id ; 
    Double montant; 
    Client Detenteur ;
    String email ;
    String motDePasse ;
    boolean Connexion ;
    Double Montant; 
    
    
    public Compte(UUID id, Double montant, Client detenteur, String email, String motDePasse) {
        this.id = id;
        this.montant = montant;
        Detenteur = detenteur;
        this.email = email;
        this.motDePasse = motDePasse;
        this.Connexion =false ;
    }

    public void SeConnecter(String email, String motDePasse) {
        if (!this.email.equals(email)) {
            throw new IllegalArgumentException("Email incorrect");
        }
        if (!this.motDePasse.equals(motDePasse)) {
            throw new IllegalArgumentException("Mot de passe incorrect");
        }
        System.out.println("Connexion réussie ! Bienvenue " + this.Detenteur.prenom);
        this.Connexion = true ;      
    }
    
    public void Deconnecter() {
        System.out.println("Au revoir " + this.Detenteur.prenom);
        this.Connexion = false ;
    }

}
