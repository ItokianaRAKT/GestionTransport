package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor

public abstract class Transport {


    enum StatutVoyage {
        PLANIFIE, EN_COURS, TERMINE, ANNULE
    }

    private UUID id;
    private LocalDate date;
    private LocalTime heureDepart;
    private Trajet trajet;
    private StatutVoyage statut;
    private Vehicule vehicule;
    private Chauffeur chauffeur;
    private int prixTotal;

    public abstract double calculerPrix();
    public abstract int placesDisponibles();
    public abstract boolean estComplet();

}
