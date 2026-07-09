package TransportTerrestre;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter @Setter @ToString @EqualsAndHashCode(callSuper = true)


public class Admin extends Utilisateur {
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Agence agence;

    public Admin(UUID id, String nom, String prenom, String email, String telephone, String motDePasse, Agence agence) {
        super(id, nom, prenom, email, telephone, motDePasse);
        this.agence = agence;
        if (agence != null) agence.setAdmin(this);
    }

}
