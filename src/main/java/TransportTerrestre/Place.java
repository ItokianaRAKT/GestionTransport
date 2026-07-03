package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Place {
    private int numero;
    private Vehicule vehicule;
    private VoyageNational voyageNational;
    enum Ranger {
        devant , premier , deuxieme ,troisieme ,quatre
    }
    enum Colonne {
        FenetreG, FenetreD ,CouloirG ,CouloirD , Milieu
    }
    Ranger ranger ;
    Colonne colonne ;
}
