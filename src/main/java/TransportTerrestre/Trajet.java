package TransportTerrestre;

import java.util.ArrayList;
import java.util.UUID;

public class Trajet {
    private UUID id;
    private String villeDepart;
    private String villeArrivee;
    private int distance;
    private int prix;
    private ArrayList<Arret> arrets;
}
