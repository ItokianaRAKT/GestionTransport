package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor

public class Agence {
    private UUID id;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Admin admin;
    private String ville;
    private String adresse;
    private String telephone;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Map<Vehicule, Trajet> VehiculeAssigneTrajet;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private ArrayList<Chauffeur> chauffeurs;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private ArrayList<Reservation> reservations;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private ArrayList<Deplacement> voyagesEffectues;
    private ArrayList<Trajet> trajets;

    public void listerVoituresDisponibles() {
        for (Vehicule v : VehiculeAssigneTrajet.keySet()) {
            if (v.estDisponible()) {
                System.out.println("- " + v.getMatricule() + " (" + v.getTypeVehicule() + ")");
            }
        }
    }
}
