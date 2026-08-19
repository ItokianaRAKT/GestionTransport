package TransportTerrestre;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter @ToString @EqualsAndHashCode(callSuper = true)

public class VoyageNational extends Deplacement {
    private int dureeEstimee;
    private LocalTime heureArriveeApproximative;
    private ArrayList<Ticket> tickets;

    public VoyageNational(UUID id, LocalDate date, LocalTime heureDepart, Trajet trajet, StatutTransport statut, Vehicule vehicule, ArrayList<Chauffeur> chauffeur, double prixTotal, int dureeEstimee, LocalTime heureArriveeApproximative, ArrayList<Ticket> tickets) {
        super(id, date, heureDepart, trajet, statut, vehicule, chauffeur, prixTotal);
        this.dureeEstimee = dureeEstimee;
        this.heureArriveeApproximative = heureArriveeApproximative;
        this.tickets = tickets;
    }

    public double calculerPrixTicket(Arret depart, Arret arrivee) {
        if (getTrajet() == null || getVehicule() == null) {
            throw new IllegalStateException("Impossible de calculer le prix du ticket sans trajet ni vehicule");
        }
        double prixBase = getTrajet().getPrix() * getVehicule().getCoefficient();
        int distance = getTrajet().calculerDistance(depart.getVille(), arrivee.getVille());
        int totalDistance = getTrajet().calculerDistanceTotale();
        if (distance == 0) {
            throw new IllegalArgumentException("Le départ et l'arrivée sont identiques");
        }
        double prix = prixBase;
        if (distance < totalDistance / 2.0) {
            prix = prix / 2;
        }
        return Math.round(prix);
    }

    public Ticket creerTicket(Arret depart, Arret arrivee, Place place) {
        Ticket ticket = new Ticket(UUID.randomUUID(), depart, arrivee, place, calculerPrixTicket(depart, arrivee));
        ticket.setJourDepart(getDate());
        ticket.setHeureDepart(getHeureDepart());
        ticket.setVoyage(this);
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public double calculerPrix() {
        double total = 0;
        for (Ticket t : tickets) {
            double prix = t.getPrix();
            double fraisBagages = 0;
            for (Bagage b : t.getBagages()) {
                fraisBagages += b.calculerFrais();
            }
            total += prix + (double) Math.round(fraisBagages);
        }
        return total;
    }

    @Override
    public int compterPlacesDisponiblesRestantes() {
        return getVehicule().getNombreDePlaces() - tickets.size();
    }

    @Override
    public boolean estComplet() {
        return tickets.size() >= getVehicule().getNombreDePlaces();
    }

    public void terminer() {
        if (getVehicule() == null || getTrajet() == null) {
            throw new IllegalStateException("Impossible de terminer un voyage sans vehicule ni trajet");
        }
        setStatut(StatutTransport.TERMINE);
        setPrixTotal(calculerPrix());
        getVehicule().getTransportsEffectues().add(this);
        if (getVehicule().getAgence() != null) {
            getVehicule().getAgence().getVoyagesEffectues().add(this);
        }
    }

}
