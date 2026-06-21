package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor

public class Vehicule {
    enum TypeService {
        ECO, VIP
    }
    enum TypeVehicule{
        MOTO, TAXI, TAXI_BROUSSE, MINI_BUS
    }
    private String matricule;
    private int nombreDePlaces;
    private TypeService typeService;
    private ArrayList<Place> places;
    private TypeVehicule typeVehicule;
    private boolean disponible;
    private int chargeMax;
    private boolean appartientAgence;
    private ArrayList<Depenses> depensesCeMois;

}
