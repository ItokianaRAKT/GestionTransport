package TransportTerrestre;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
    enum StatutTicket {
        RESERVE, VALIDE, ANNULE, REMBOURSE
    }


    private UUID id;
    private Place placeConcernee;
    private StatutTicket statut;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<Bagage> bagages;



    private Arret arretDepart;
    private Arret arretArrivee;
    private LocalDate jourDepart;
    private LocalTime heureDepart;
    private boolean actif;
  
    public Ticket(String id, Arret depart, Arret arrivee, Place place) {
        this.id = id;
        this.arretDepart = depart;
        this.arretArrivee = arrivee;
        this.placeConcernée = place;
        this.bagages = new ArrayList<>();
        this.actif = true;
    }
    public void annuler() { this.actif = false; }




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
