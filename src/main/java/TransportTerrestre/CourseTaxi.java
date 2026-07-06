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
    public boolean estComplet() {
        return true;
    }

    public void demarrer() {
        setStatut(StatutTransport.EN_COURS);
    }

    public void terminer() {
        setStatut(StatutTransport.TERMINE);
        setPrixTotal((int) calculerPrix());
        getVehicule().getTransportsEffectues().add(this);
    }

}
