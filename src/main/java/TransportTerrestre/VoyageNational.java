package TransportTerrestre;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

@Data

public class VoyageNational extends Transport {
    private String numeroLigne;
    private int dureeEstimee;
    private LocalTime heureArrivee;
    private ArrayList<Ticket> tickets;

    public VoyageNational(UUID id, LocalDate date, LocalTime heureDepart, Trajet trajet, StatutVoyage statut, Vehicule vehicule, Chauffeur chauffeur, int prixTotal, String numeroLigne, int dureeEstimee, LocalTime heureArrivee, ArrayList<Ticket> tickets) {
        super(id, date, heureDepart, trajet, statut, vehicule, chauffeur, prixTotal);
        this.numeroLigne = numeroLigne;
        this.dureeEstimee = dureeEstimee;
        this.heureArrivee = heureArrivee;
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
    public int placesDisponibles() {
        return getVehicule().getNombreDePlaces() - tickets.size();
    }

    @Override
    public boolean estComplet() {
        return tickets.size() >= getVehicule().getNombreDePlaces();
    }



}
