package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
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
