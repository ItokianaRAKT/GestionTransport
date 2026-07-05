package TransportTerrestre;

import lombok.Data;
import lombok.Getter;
import java.util.UUID;

import TransportTerrestre.Vehicule.Usage;

@Data
@Getter

public class Chauffeur extends Utilisateur {
    enum Permis { 
        A, B ,D
    }
    private Permis permis ;
    private String numeroPermis;
    private boolean disponible;
    private Vehicule vehicule;
    private Agence agence;

    public Chauffeur(UUID id ,Permis permis, String nom, String prenom, String email, String telephone, String motDePasse, String numeroPermis, Vehicule vehicule) {
        super(id, nom, prenom, email, telephone, motDePasse);
        this.numeroPermis = numeroPermis;
        this.disponible = true;
        this.vehicule = vehicule;
      
    }
    // un chauffeur peut ne pas avoir de vehicule 
    public Chauffeur(UUID id,Permis permis, String nom, String prenom, String email, String telephone, String motDePasse, String numeroPermis) {
        super(id, nom, prenom, email, telephone, motDePasse);
        this.numeroPermis = numeroPermis;
        this.disponible = true;     
    }
     
     public void changerDisponibilité (){ 
        if (this.disponible== true){ 
            disponible= false ; 
        }else disponible = true ; 
     }
     public void changerDisponibilitéVehicule (boolean choix){    
        if(this.vehicule.getDispo() == choix) { 
            System.out.println("rien ne changes");
        }else 
                this.vehicule.setDispo(choix);

     }
     public void terminerTransport (){ 
        
     }
     public void Negociation (Reservation reservationClient,double prixChauffeur){
        if ((this.vehicule.getUsage()== Usage.COURSE) &&(reservationClient.getPaiement() == null)){ 
            reservationClient.setPrix(prixChauffeur); 
            System.out.println("negociation avec succes");
        }else throw new IllegalArgumentException
        ("cela n est pas possible soit le payment est deja realiser soit c est voyage prix  defini!!!"); 

    }

}
