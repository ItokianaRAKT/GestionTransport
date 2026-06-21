package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor

public class Agence {
    private UUID id;
    private Admin admin;
    private String ville;
    private String adresse;
    private String telephone;
    private ArrayList<Vehicule> vehicules;
    private ArrayList<Chauffeur> chauffeurs;
    private ArrayList<Trajet> trajets;
    private ArrayList<Reservation> reservations;
    private ArrayList<Transport> voyagesEffectues;


public int calculDepense(Vehicule vehicule){
    if (!vehicule.isAppartientAgence() && vehicule.getTypeVehicule() == Vehicule.TypeVehicule.TAXI_BROUSSE ){
        YearMonth ceMois = YearMonth.now();

    }

}