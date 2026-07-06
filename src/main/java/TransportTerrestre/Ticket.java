package TransportTerrestre;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
public class Ticket {
    enum StatutTicket {
        RESERVE, VALIDE, ANNULE, REMBOURSE
    }

    private String id;
    private Arret arretDepart;
    private Arret arretArrivee;
    private List<Bagage> bagages;
    private Place placeConcernee;
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
}
