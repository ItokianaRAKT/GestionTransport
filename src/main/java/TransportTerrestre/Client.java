package TransportTerrestre;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.UUID;

@Getter @Setter @ToString @EqualsAndHashCode(callSuper = true)

public class Client extends Utilisateur {
    private ArrayList<Reservation> reservations;

    public Client(UUID id, String nom, String prenom, String email, String telephone, String motDePasse, ArrayList<Reservation> reservations) {
        super(id, nom, prenom, email, telephone, motDePasse);
        this.reservations = reservations;
    }
}
