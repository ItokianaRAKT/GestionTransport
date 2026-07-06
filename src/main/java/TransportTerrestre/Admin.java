package TransportTerrestre;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@Data


public class Admin extends Utilisateur {
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Agence agence;

    public Admin(UUID id, String nom, String prenom, String email, String telephone, String motDePasse, Agence agence) {
        super(id, nom, prenom, email, telephone, motDePasse);
        this.agence = agence;
    }

}
