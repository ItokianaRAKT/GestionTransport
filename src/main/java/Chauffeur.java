import java.util.UUID;

public class Chauffeur extends Utilisateur{
     typePermis sonPermis ;
     boolean disponible;
     Vehicule vehicule;
    Agence agence;
    public Chauffeur(UUID id, typePermis sonPermis ,String nom, String prenom, String email, String motDePasse,
            Vehicule vehicule) {
        super(id, nom, prenom, email, motDePasse);
        this.sonPermis = sonPermis;
        this.vehicule = vehicule;
        this.disponible = true ; 
    } 
    public double Negociation (Reservation reservationClient,double prixChauffeur){
        return reservationClient.prix=prixChauffeur ;
    }
    
      
}
