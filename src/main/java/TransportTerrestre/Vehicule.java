package TransportTerrestre;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Data


public class Vehicule {
    enum TypeService {
        ECO(1.0), VIP(1.5);
        private final double coefficient;

        TypeService(double coefficient) {
            this.coefficient = coefficient;
        }

        public double getCoefficient() {
            return coefficient;
        }
    }


    enum TypeVehicule{
        MOTO, BREAK, CITADINE, BUS

    }
    enum Usage { 
        COURSE , VOYAGE 
    }
    
    private String matricule;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Agence agence ;
    private TypeService typeService;
    private int nombreDePlaces ;
    private List<Place> sieges;
    private TypeVehicule typeVehicule;
    private Usage usage ;
    private Trajet trajet ; 
    private Double consommationParKM ;
    private boolean disponible;
    private double chargeMax;

    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<Depenses> listeDepenses;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List <Deplacement> transportsEffectues;




    public Vehicule(String matricule, Double consommationParKM  ,TypeService typeService, TypeVehicule typeVehicule, Usage usage) {
        this.matricule = matricule;
        this.consommationParKM = consommationParKM ;
        this.typeService = typeService;
        this.typeVehicule = typeVehicule;
        this.usage = usage;
        this.disponible = true ;
        this.chargeMax = conditionCharge();
        this.sieges = ajouterPlaces(typeVehicule);
        this.listeDepenses =  new ArrayList<>();
        this.transportsEffectues =  new ArrayList<>(); 
    }
    public double conditionCharge() {
        if (typeVehicule == TypeVehicule.MOTO) return 0.00;
        if (typeVehicule == TypeVehicule.CITADINE) return 350.00;
        if (typeVehicule == TypeVehicule.BREAK) return 500.00;
        if (typeVehicule == TypeVehicule.BUS) return 1500.00;
        return 0.00;
    }
    public double getCoefficient() {
        return typeService.getCoefficient();
    }


    public boolean estDisponible() {
        return isDisponible();
    }

    public void changerDisponibilite() {
        setDisponible(!isDisponible());
    }

    public void ajouterDepense(Depenses depenses) {

        listeDepenses.add(depenses);
        System.out.println("Dépense insérée avec succès");
    }

    public double calculDepensesMensuelle(YearMonth mois) {
        double total = 0;
        for (Depenses D : listeDepenses) {
            if (YearMonth.from(D.getDate()).equals(mois)) {
                total += D.getMontant();
            }
        }
        return total;
    }

    public double calculRecetteMensuelle(YearMonth mois) {
        double total = 0;
        for (Deplacement deplacement : transportsEffectues) {
            if (YearMonth.from(deplacement.getDate()).equals(mois))
                total += deplacement.calculerPrix();
        }
        return total;
    }

    public double calculBeneficeMensuel(YearMonth mois) {
        return calculRecetteMensuelle(mois) - calculDepensesMensuelle(mois);
    }
    public List<Place> ajouterPlaces(TypeVehicule genre) {
        List<Place> generationPlaces = new ArrayList<>();
        if (genre == TypeVehicule.MOTO) {
            nombreDePlaces = 1;
            generationPlaces.add(new Place(1, Place.Rangee.devant, Place.Colonne.milieu));
        } else if (genre == TypeVehicule.CITADINE) {
            nombreDePlaces = 4;
            generationPlaces.add(new Place(1, Place.Rangee.devant, Place.Colonne.fenetreG));
            generationPlaces.add(new Place(2, Place.Rangee.devant, Place.Colonne.fenetreD));
            generationPlaces.add(new Place(3, Place.Rangee.premier, Place.Colonne.fenetreG));
            generationPlaces.add(new Place(4, Place.Rangee.premier, Place.Colonne.fenetreD));
        } else if (genre == TypeVehicule.BREAK) {
            nombreDePlaces = 4;
            generationPlaces.add(new Place(1, Place.Rangee.devant, Place.Colonne.fenetreG));
            generationPlaces.add(new Place(2, Place.Rangee.devant, Place.Colonne.fenetreD));
            generationPlaces.add(new Place(3, Place.Rangee.premier, Place.Colonne.fenetreG));
            generationPlaces.add(new Place(4, Place.Rangee.premier, Place.Colonne.fenetreD));
        } else if (genre == TypeVehicule.BUS) {
            generationPlaces.add(new Place(1, Place.Rangee.devant, Place.Colonne.milieu));
            generationPlaces.add(new Place(2, Place.Rangee.devant, Place.Colonne.fenetreD));
            for (int compte = 0; compte < 4; compte++) {
                Place.Rangee rangee;
                if (compte == 0) rangee = Place.Rangee.premier;
                else if (compte == 1) rangee = Place.Rangee.deuxieme;
                else if (compte == 2) rangee = Place.Rangee.troisieme;
                else rangee = Place.Rangee.quatre;

                int base = 5 * compte + 3;
                generationPlaces.add(new Place(base, rangee, Place.Colonne.fenetreG));
                generationPlaces.add(new Place(base + 1, rangee, Place.Colonne.CouloirG));
                generationPlaces.add(new Place(base + 2, rangee, Place.Colonne.milieu));
                generationPlaces.add(new Place(base + 3, rangee, Place.Colonne.CouloirD));
                generationPlaces.add(new Place(base + 4, rangee, Place.Colonne.fenetreD));
            }
            nombreDePlaces = generationPlaces.size();
        }
        return generationPlaces;
    }
}
