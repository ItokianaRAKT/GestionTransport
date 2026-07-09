package TransportTerrestre;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

@Getter @Setter @ToString @EqualsAndHashCode(callSuper = true)

public class CourseTaxi extends Deplacement {


    public CourseTaxi(UUID id, LocalDate date, LocalTime heureDepart, Trajet trajet, StatutTransport statut, Vehicule vehicule, ArrayList<Chauffeur> chauffeur, double prixTotal, Client client, TypeCourse typeCourse, double tarifParKm) {
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
        if (getTrajet() == null || getVehicule() == null) {
            throw new IllegalStateException("Impossible de calculer le prix sans trajet ni vehicule");
        }
        double prixBase = getTrajet().getDistance() * getTarifParKm();
        return (prixBase * getVehicule().getCoefficient());
    }

    @Override
    public boolean estComplet() {
        return client != null;
    }

    @Override
    public int compterPlacesDisponiblesRestantes() {
        return client == null ? 1 : 0;
    }

    public void demarrer() {
        setStatut(StatutTransport.EN_COURS);
    }

    public void terminer() {
        if (getVehicule() == null || getTrajet() == null) {
            throw new IllegalStateException("Impossible de terminer une course sans vehicule ni trajet");
        }
        setStatut(StatutTransport.TERMINE);
        setPrixTotal(calculerPrix());
        getVehicule().getTransportsEffectues().add(this);
        if (getVehicule().getAgence() != null) {
            getVehicule().getAgence().getVoyagesEffectues().add(this);
        }
    }

}
