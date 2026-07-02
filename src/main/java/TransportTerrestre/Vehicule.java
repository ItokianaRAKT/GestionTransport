package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor

public class Vehicule {
    enum TypeService {
        ECO(1.0), VIP(1.5);
        private final double coefficient;
        TypeService(double coefficient){
            this.coefficient = coefficient;
        }
        public double getCoefficient() {
            return coefficient;
        }
    }

    enum TypeVehicule{
        MOTO, TAXI_BREAK, TAXI_BROUSSE, MINI_BUS
    }
    private String matricule;
    private int nombreDePlaces;
    private TypeService typeService;
    private ArrayList<Place> places;
    private TypeVehicule typeVehicule;
    private boolean disponible;
    private double chargeMax;
    private boolean appartientAgence;
    private ArrayList<Depenses> listeDeDepenses;
    private ArrayList <Deplacement> transportsEffectues;

    public double getCoefficient() {
        return typeService.getCoefficient();
    }

    public boolean estDisponible(){
        return isDisponible();
    }

    public void changerDisponibilité(){
        if (isDisponible()){
            setDisponible(false);
        }
        else {
            setDisponible(true);
        }
    }

    public void ajouterDepense(Depenses depenses){
        listeDeDepenses.add(depenses);
        System.out.println("Dépense insérée avec succès");
    }

    public double calculDepensesMensuelle(YearMonth mois){
        double total = 0;
        for(Depenses D : listeDeDepenses ){
            if (YearMonth.from(D.getDate()).equals(mois)){
                total += D.getMontant();
            }
        }
        return total;
    }

    public double calculRecetteMensuelle(YearMonth mois) {
        double total = 0;
        for(Deplacement deplacement : transportsEffectues){
            if (YearMonth.from(deplacement.getDate()).equals(mois))
                total += deplacement.calculerPrix();
        }
        return total;
    }

    public double calculBeneficeMensuel(YearMonth mois){
        double total = 0;
        YearMonth moisCible = mois;
        total = calculDepensesMensuelle(moisCible) - calculRecetteMensuelle(moisCible);
        return total;
    }
    public void ajoutPlaces (TypeVehicule genre){  
        if (genre == TypeVehicule.MOTO){ 
            Place chaise = new Place(1); 
            places.add(chaise);
        }
        if (genre == TypeVehicule.TAXI_BROUSSE || genre == TypeVehicule.TAXI_BREAK){ 
            Place chaise = new Place(1, Place.Ranger.devant, Place.Colonne.fenetreD);
            Place chaise1 = new Place(2,Place.Ranger.deuxieme,Place.Colonne.fenetreG);
            Place chaise2 =new Place (3,Place.Ranger.deuxieme,Place.Colonne.milieu);
            Place chaise3  =new Place (4,Place.Ranger.deuxieme,Place.Colonne.fenetreD);
            
            this.places = new ArrayList<>(List.of(chaise,chaise1,chaise2,chaise3));
        }
        if (genre== TypeVehicule.MINI_BUS){ 
            Place chaise = new Place(1, Place.Ranger.devant, Place.Colonne.milieu);
            Place chaise1 = new Place(2, Place.Ranger.devant, Place.Colonne.fenetreD);
            places.add(chaise);
            places.add(chaise1);
            int compte ;
            int ajout = 5 ; 
            for (compte = 0; compte<4; compte++){  
                places.add(new Place( (ajout*compte)+3,Place.Ranger.deuxieme,Place.Colonne.fenetreG));
                places.add(new Place( (ajout*compte)+5,Place.Ranger.deuxieme,Place.Colonne.CouloirG));
                places.add(new Place( (ajout*compte)+6,Place.Ranger.deuxieme,Place.Colonne.milieu));
                places.add(new Place( (ajout*compte)+7,Place.Ranger.deuxieme,Place.Colonne.CouloirD));
                places.add(new Place( (ajout*compte)+8,Place.Ranger.deuxieme,Place.Colonne.fenetreD));
                
            }
        }
    }
    
}
