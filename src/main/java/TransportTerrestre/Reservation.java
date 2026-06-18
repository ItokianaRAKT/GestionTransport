package TransportTerrestre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private LocalDate dateReservation;
    private ArrayList<Ticket> tickets;
    private Paiement paiement;
    private Client client;
    private Voyage voyage;
}
