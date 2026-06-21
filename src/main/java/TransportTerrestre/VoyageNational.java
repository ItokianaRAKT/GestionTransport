package TransportTerrestre;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

@Data

public class VoyageNational extends Transport {
    private String numeroLigne;
    private int nombrePlacesDisponibles;
    private Trajet trajet;
    private int dureeEstimee;
    private LocalTime heureArrivee;

    public VoyageNational(UUID id, LocalDate date, LocalTime heureDepart, Trajet trajet, StatutVoyage statut, Vehicule vehicule, Chauffeur chauffeur, ArrayList<Ticket> tickets, int prixTotal, String numeroLigne, int nombrePlacesDisponibles, Trajet trajet1, int dureeEstimee, LocalTime heureArrivee) {
        super(id, date, heureDepart, trajet, statut, vehicule, chauffeur, tickets, prixTotal);
        this.numeroLigne = numeroLigne;
        this.nombrePlacesDisponibles = nombrePlacesDisponibles;
        this.trajet = trajet1;
        this.dureeEstimee = dureeEstimee;
        this.heureArrivee = heureArrivee;
    }
}
