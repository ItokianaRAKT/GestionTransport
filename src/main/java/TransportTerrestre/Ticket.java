package TransportTerrestre;

import java.util.ArrayList;
import java.util.UUID;

public class Ticket {
    enum StatutTicket {
        RESERVE, VALIDE, ANNULE, REMBOURSE
    }

    private UUID id;
    private Place place;
    private StatutTicket statut;
    private Arret arretMontee;
    private Arret arretDescente;
    private Voyage voyage;
    private ArrayList<Bagage> bagages;
}
