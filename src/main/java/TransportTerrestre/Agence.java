package TransportTerrestre;

import java.util.ArrayList;
import java.util.UUID;

public class Agence {
    private UUID id;
    private String ville;
    private String adresse;
    private String telephone;
    private ArrayList<Vehicule> vehicules;
    private ArrayList<Chauffeur> chauffeurs;
    private ArrayList<Trajet> trajets;
    private ArrayList<Voyage> voyagesEffectues;
}
