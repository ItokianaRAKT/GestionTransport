package TransportTerrestre;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Ticket(UUID id, Arret depart, Arret arrivee, Place place) {
        this.id = id;
        this.arretDepart = depart;
        this.arretArrivee = arrivee;
        this.placeConcernee = place;
        this.bagages = new ArrayList<>();
        this.actif = true;
    }

    public void annuler() { this.actif = false; }

    public UUID getId() { return id; }
    public Place getPlaceConcernee() { return placeConcernee; }
    public StatutTicket getStatut() { return statut; }
    public List<Bagage> getBagages() { return bagages; }
    public Arret getArretDepart() { return arretDepart; }
    public Arret getArretArrivee() { return arretArrivee; }
    public LocalDate getJourDepart() { return jourDepart; }
    public LocalTime getHeureDepart() { return heureDepart; }
    public boolean isActif() { return actif; }

}
