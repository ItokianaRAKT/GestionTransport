package TransportTerrestre;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

@Data

public class VoyageNational extends Deplacement {
    private int dureeEstimee;
    private LocalTime heureArriveeApproximative;
    private ArrayList<Ticket> tickets;

    public VoyageNational(UUID id, LocalDate date, LocalTime heureDepart, Trajet trajet, StatutTransport statut, Vehicule vehicule, ArrayList<Chauffeur> chauffeur, int prixTotal, int dureeEstimee, LocalTime heureArriveeApproximative, ArrayList<Ticket> tickets) {
        super(id, date, heureDepart, trajet, statut, vehicule, chauffeur, prixTotal);
        this.dureeEstimee = dureeEstimee;
        this.heureArriveeApproximative = heureArriveeApproximative;
        this.tickets = tickets;
    }

    @Override
    public double calculerPrix() {
        double total = 0;
        for(Ticket t : tickets){
            total += t.calculerPrixUnitaire();
        }
        return  total;
    }

    @Override
    public int compterPlacesDisponiblesRestantes() {
        return getVehicule().getNombreDePlaces() - tickets.size();
    }

    @Override
    public boolean estComplet() {
        return tickets.size() >= getVehicule().getNombreDePlaces();
    }

}
