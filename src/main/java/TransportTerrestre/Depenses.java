package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor


public class Depenses {
    enum TypeDepense {
        CARBURANT, REPARATION, ENTRETIEN, SALAIRE, ASSURANCE
    }

    private LocalDate date;
    private double montant;
    private String description;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Vehicule vehicule;
    
    

}
