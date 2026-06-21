package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor

public class Trajet {
    private UUID id;
    private String lieuPriseEnCharge;
    private String destination;
    private int distance;
    private int prix;
    private ArrayList<Arret> arrets;
}
