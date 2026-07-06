package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

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
    private Client client;


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

    public UUID getId() {
        return id;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public Client getClient() {
        return client;
    }

    public Paiement getPaiement() {
        return paiement;
    }
    public double calculerMontant(){
        double montant = 0;
        for(Ticket tick : tickets){
            montant += tickets.getTypeCourse.calculerPrix();
        }
        return montant;
    }
    public ArrayList<Bagage> ajouterBagages(Bagage bag){
        return this.tickets.add(bag);
    }
    public ArrayList<Bagage> retirerBagages(Bagage bag){
        return this.tickets.remove(bag);

    }
}
