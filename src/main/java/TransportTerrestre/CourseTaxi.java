package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

@Data


public class CourseTaxi extends Transport {


    public CourseTaxi(UUID id, LocalDate date, LocalTime heureDepart, Trajet trajet, StatutVoyage statut, Vehicule vehicule, Chauffeur chauffeur, int prixTotal, Client client, TypeCourse typeCourse, double tarifParKm) {
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
    public int placesDisponibles() {
        return estComplet() ? 0 : 1;
    }

    @Override
    public boolean estComplet() {
        return placesDisponibles() == 0;
    }


}
