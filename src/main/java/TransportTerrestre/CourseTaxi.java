package TransportTerrestre;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

@Data


public class CourseTaxi extends Deplacement {


    public CourseTaxi(UUID id, LocalDate date, LocalTime heureDepart, Trajet trajet, StatutTransport statut, Vehicule vehicule, ArrayList<Chauffeur> chauffeur, int prixTotal, Client client, TypeCourse typeCourse, double tarifParKm) {
        super(id, date, heureDepart, trajet, statut, vehicule, chauffeur, prixTotal);
        this.client = client;
        this.typeCourse = typeCourse;
        this.tarifParKm = tarifParKm;
    }

    enum TypeCourse {
        IMMEDIATE, PROGRAMMEE
    }

    private Client client;
    private TypeCourse typeCourse;
    private double tarifParKm;

    @Override
    public double calculerPrix() {
        double prixBase = getTrajet().getDistance() * getTarifParKm();
        return (prixBase * getVehicule().getCoefficient());
    }

    @Override
    public int compterPlacesDisponiblesRestantes() {
        return estComplet() ? 0 : 1;
    }

    @Override
    public boolean estComplet() {
        return compterPlacesDisponiblesRestantes() == 0;
    }

    public void demarerCourse(){

    }

    public void terminerCourse() {

    }

}
