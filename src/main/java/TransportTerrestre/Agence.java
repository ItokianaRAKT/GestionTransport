package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor

public class Agence {
    private UUID id;
    private Admin admin;
    private String ville;
    private String adresse;
    private String telephone;
    private Map<Vehicule, Trajet> VehiculeAssigneTrajet;
    private ArrayList<Chauffeur> chauffeurs;
    private ArrayList<Reservation> reservations;
    private ArrayList<Deplacement> voyagesEffectues;
    private ArrayList<Trajet> trajets;

    public void listerVoituresDisponibles(){
        return;
    }
}
