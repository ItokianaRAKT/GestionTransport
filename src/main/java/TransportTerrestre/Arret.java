package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor

public class Arret {
    private UUID id;
    private String ville;
    private  int distanceDepuisDepart;
}
