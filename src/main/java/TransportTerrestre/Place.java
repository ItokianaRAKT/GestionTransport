package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Place {
    private int numero;
    private Vehicule vehicule;
    private VoyageNational voyageNational;
}
