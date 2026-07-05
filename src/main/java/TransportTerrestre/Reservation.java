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

    public void calculerMontant() {

    }
}
