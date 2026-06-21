package TransportTerrestre;

import lombok.Data;

import java.util.UUID;

@Data

public class Chauffeur extends Utilisateur {
    private String numeroPermis;
    private boolean disponible;
    private Vehicule vehicule;
    private Agence agence;

    public Chauffeur(UUID id, String nom, String prenom, String email, String telephone, String motDePasse, String numeroPermis, boolean disponible, Vehicule vehicule, Agence agence) {
        super(id, nom, prenom, email, telephone, motDePasse);
        this.numeroPermis = numeroPermis;
        this.disponible = disponible;
        this.vehicule = vehicule;
        this.agence = agence;
    }
}
