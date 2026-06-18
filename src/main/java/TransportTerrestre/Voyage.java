package TransportTerrestre;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Voyage {
    enum StatutVoyage {
        PLANIFIE, EN_COURS, TERMINE, ANNULE
    }

    private UUID id;
    private LocalDate date;
    private LocalTime heureDepart;
    private StatutVoyage statut;
    private Trajet trajet;
    private Vehicule vehicule;
    private Chauffeur chauffeur;
    private ArrayList<Ticket> tickets;
    private int prixTotal;
}
