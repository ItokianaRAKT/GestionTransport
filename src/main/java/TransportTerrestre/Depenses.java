package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor


public class Depenses {
    enum TypeDepense{
        CARBURANT, REPARATION, ENTRETIENT, SALAIRE, ASSURANCE
    }
    private LocalDate date;
    private int montant;
    private String description;
    private Vehicule vehicule;
}
