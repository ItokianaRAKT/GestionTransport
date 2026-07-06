package TransportTerrestre;

import lombok.Data;

import java.util.UUID;

@Data


public abstract class Utilisateur {
    private UUID id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String motDePasse;

    public Utilisateur(UUID id, String nom, String prenom, String email, String telephone, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
    }
}
