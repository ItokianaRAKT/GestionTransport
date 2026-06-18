package TransportTerrestre;

import java.util.ArrayList;

public class Vehicule {
    enum TypeService {
        ECO, VIP
    }
    enum TypeVehicule{
        MOTO, TAXI, TAXI_BROUSSE
    }
    private String matricule;
    private int nombreDePlaces;
    private TypeService typeService;
    private ArrayList<Place> places;
    private TypeVehicule typeVehicule;
}
