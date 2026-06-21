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
    private ArrayList<Transport> voyage;
}
