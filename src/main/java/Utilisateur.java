import java.util.UUID;

public abstract class Utilisateur {
    protected UUID id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String telephone;
    protected String motDePasse;
    protected boolean Connexion ;

    public Utilisateur(UUID id, String nom, String prenom, 
                       String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.Connexion = false ;
    }

    public void SeConnecter(String email, String motDePasse) {
        if (!this.email.equals(email)) {
            throw new IllegalArgumentException("Email incorrect");
        }
        if (!this.motDePasse.equals(motDePasse)) {
            throw new IllegalArgumentException("Mot de passe incorrect");
        }
        System.out.println("Connexion réussie ! Bienvenue " + this.prenom);
        this.Connexion = true ;      
    }
    
    public void Deconnecter() {
        System.out.println("Au revoir " + this.prenom);
        this.Connexion = false ;
    }

    
}