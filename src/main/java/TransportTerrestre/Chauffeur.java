package TransportTerrestre;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
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
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Vehicule vehicule;
    @ToString.Exclude @EqualsAndHashCode.Exclude
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
     
     public void changerDisponibilite() {
        this.disponible = !this.disponible;
     }

     public void terminerCourse(CourseTaxi course) {
        course.terminer();
        setDisponible(true);
        if (getVehicule() != null) {
            getVehicule().setDisponible(true);
        }
     }
}
