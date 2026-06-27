package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor

public class Ticket {
    enum StatutTicket {
        RESERVE, VALIDE, ANNULE, REMBOURSE
    }

    private UUID id;
    private Place placeConcenre;
    private StatutTicket statut;
    private Deplacement deplacement;
    private ArrayList<Bagage> bagages;
    String arretDepart;
    String arretArrivee;

    public int calculerDistance() {
        return deplacement.getTrajet()
                .calculerDistance(arretDepart, arretArrivee);
    }

    public double calculerPrixUnitaire() {

        double prixBase = deplacement.getTrajet().getPrix();
        double coef = deplacement.getVehicule().getCoefficient();

        int distance = deplacement.getTrajet()
                .calculerDistance(arretDepart, arretArrivee);

        int total = deplacement.getTrajet()
                .calculerDistanceTotale();

        double prix = prixBase * coef;

        if (distance < total / 2) {
            prix = prix / 2;
        }

        return Math.round(prix);
    }



}
