package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor

public class Ticket {
    enum StatutTicket {
        RESERVE, VALIDE, ANNULE, REMBOURSE
    }

    private UUID id;
    private String nomPassager;
    private Place placeConcenre;
    private StatutTicket statut;
    private Transport transport;
    private ArrayList<Bagage> bagages;
}
