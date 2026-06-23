import java.util.UUID;

public class Chauffeur extends Utilisateur{
    private typePermis sonPermis ;
    private boolean disponible;
    private Voiture vehicule;
    private Agence agence;
    public Chauffeur(UUID id, typePermis sonPermis ,String nom, String prenom, String email, String motDePasse, String numeroPermis,
            Voiture vehicule, Agence agence) {
        super(id, nom, prenom, email, motDePasse);
        this.sonPermis = sonPermis;
        this.vehicule = vehicule;
        this.agence = agence;
        this.disponible = true ; 
    } 
    
      
}
