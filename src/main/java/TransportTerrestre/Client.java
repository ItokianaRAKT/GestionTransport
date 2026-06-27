package TransportTerrestre;

import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data

public class Client extends Utilisateur{
    private ArrayList<Reservation> reservations;

    public Client(UUID id, String nom, String prenom, String email, String telephone, String motDePasse, ArrayList<Reservation> reservations) {
        super(id, nom, prenom, email, telephone, motDePasse);
        this.reservations = new ArrayList<>();
    }
}
