package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor

public abstract class Deplacement {


    enum StatutTransport {
        PLANIFIE, EN_COURS, TERMINE, ANNULE
    }

    private UUID id;
    private LocalDate date;
    private LocalTime heureDepart;
    private Trajet trajet;
    private StatutTransport statut;
    private Vehicule vehicule;
    private ArrayList<Chauffeur> chauffeur;
    private int prixTotal;

    public abstract double calculerPrix();

    public int compterPlacesDisponiblesRestantes() {
        return 0;
    }

    public abstract boolean estComplet();

}
