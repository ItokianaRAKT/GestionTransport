package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor

public class Bagage {
    private UUID id;
    private double poids;



}
