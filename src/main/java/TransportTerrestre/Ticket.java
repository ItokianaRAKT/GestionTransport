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
    private String nomPassager;
    private Place placeConcenre;
    private StatutTicket statut;
    private Transport transport;
    private ArrayList<Bagage> bagages;
    String arretDepart;
    String arretArrivee;

    public int calculerDistance() {
        return transport.getTrajet()
                .calculerDistance(arretDepart, arretArrivee);
    }

    public double calculerPrixUnitaire() {

        double prixBase = transport.getTrajet().getPrix();
        double coef = transport.getVehicule().getCoefficient();

        int distance = transport.getTrajet()
                .calculerDistance(arretDepart, arretArrivee);

        int total = transport.getTrajet()
                .calculerDistanceTotale();

        double prix = prixBase * coef;

        if (distance < total / 2) {
            prix = prix / 2;
        }

        return Math.round(prix);
    }



}
