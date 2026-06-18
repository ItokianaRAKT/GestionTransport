package TransportTerrestre;

public class CourseTaxi extends Voyage {
    enum TypeCourse {
        IMMEDIATE, PROGRAMMEE
    }

    private Client client;
    private String lieuPriseEnCharge;
    private String lieuDestination;
    private TypeCourse typeCourse;
    private int dureeEstimee;
}
