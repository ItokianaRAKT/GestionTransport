package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor

public class Reservation {
    private UUID id;
    private LocalDate dateReservation;
    private ArrayList<Ticket> tickets;
    private Paiement paiement;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Client client;
    private double prix;



    public Reservation(UUID id, Client client, Paiement paiement) {
        this.id = id;
        this.client = client;
        this.paiement = paiement;
        this.dateReservation = LocalDate.now();
        this.tickets = new ArrayList<>();
    }

    public Reservation(UUID id, Client client, Paiement paiement, ArrayList<Ticket> tickets, LocalDate dateReservation) {
        this.id = id;
        this.client = client;
        this.paiement = paiement;
        this.tickets = tickets;
        this.dateReservation = dateReservation;
    }

    public void ajouterTicket(Ticket ticket){
        this.tickets.add(ticket);
        this.prix = calculerMontant();
    }

    public double calculerMontant(){
        double montant = 0;
        for(Ticket ticket : tickets){
            montant += ticket.getPrix();
            for(Bagage bag : ticket.getBagages()){
                montant += bag.calculerFrais();
            }
        }
        return montant;
    }

    public double calculerFraisBagages(){
        double total = 0;
        for(Ticket ticket : tickets){
            for(Bagage bag : ticket.getBagages()){
                total += bag.calculerFrais();
            }
        }
        return total;
    }

    public double calculerPoidsBagages(){
        double total = 0;
        for(Ticket ticket : tickets){
            for(Bagage bag : ticket.getBagages()){
                total += bag.getPoids();
            }
        }
        return total;
    }

    public double annulerTicket(Ticket ticket) {
        if (!ticket.isActif()) {
            throw new IllegalStateException("Ce ticket est déjà annulé");
        }
        LocalDate jourDepart = ticket.getJourDepart();
        LocalTime heureDepart = ticket.getHeureDepart();
        if (jourDepart != null && heureDepart != null) {
            LocalDateTime depart = LocalDateTime.of(jourDepart, heureDepart);
            if (!LocalDateTime.now().isBefore(depart)) {
                throw new IllegalStateException("Impossible d'annuler après l'heure de départ");
            }
        }
        double pourcentage = calculerPourcentageRemboursement(ticket);
        double montantTicket = ticket.getPrix();
        for (Bagage b : ticket.getBagages()) {
            montantTicket += b.calculerFrais();
        }
        double montantRembourse = montantTicket * pourcentage / 100.0;
        if (montantRembourse > 0) {
            ticket.setStatut(Ticket.StatutTicket.REMBOURSE);
            paiement.setRembourse(true);
            paiement.setMontantRembourse(paiement.getMontantRembourse() + montantRembourse);
        } else {
            ticket.setStatut(Ticket.StatutTicket.ANNULE);
        }
        ticket.setActif(false);
        if (ticket.getPlaceConcernee() != null) {
            ticket.getPlaceConcernee().setDisponibilite(true);
        }
        this.prix = calculerMontant();
        return montantRembourse;
    }

    public double annulerReservation() {
        double total = 0;
        for (Ticket t : new ArrayList<>(tickets)) {
            total += annulerTicket(t);
        }
        return total;
    }

    private double calculerPourcentageRemboursement(Ticket ticket) {
        LocalDate jourDepart = ticket.getJourDepart();
        LocalTime heureDepart = ticket.getHeureDepart();
        if (jourDepart == null || heureDepart == null) {
            return 0;
        }
        LocalDateTime depart = LocalDateTime.of(jourDepart, heureDepart);
        LocalDateTime maintenant = LocalDateTime.now();
        if (!maintenant.isBefore(depart)) {
            return 0;
        }
        long joursAvant = ChronoUnit.DAYS.between(maintenant.toLocalDate(), jourDepart);
        long heuresAvant = ChronoUnit.HOURS.between(maintenant, depart);
        if (joursAvant >= 5) return 100;
        if (joursAvant >= 3) return 50;
        if (heuresAvant >= 24) return 25;
        return 0;
    }

    public void ajouterBagage(Ticket ticket, Bagage bag){
        if (bag.estInterdit()) {
            throw new IllegalArgumentException("Bagage interdit : poids depasse " + Bagage.SECOND_PLAFOND + "kg");
        }
        ticket.getBagages().add(bag);
        this.prix = calculerMontant();
    }

    public void retirerBagage(Ticket ticket, Bagage bag){
        ticket.getBagages().remove(bag);
        this.prix = calculerMontant();
    }
}
