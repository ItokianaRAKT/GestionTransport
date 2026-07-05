package TransportTerrestre;


import lombok.Data;

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
    private Agence agence ;
    private TypeService typeService;
    private int nombreDePlace ;
    private List<Place> sieges;
    private TypeVehicule typeVehicule;
    private Usage usage ;
    private Trajet trajet ; 
    private Double consommationParKM ;
    private boolean disponible;
    private double chargeMax;

    private List<Depenses> listeDepenses;
    private List <Deplacement> transportsEffectues;




    public Vehicule(String matricule, Double consommationParKM  ,TypeService typeService, TypeVehicule typeVehicule, Usage usage) {
        this.matricule = matricule;
        this.consommationParKM = consommationParKM ;
        this.typeService = typeService;
        this.typeVehicule = typeVehicule;
        this.usage = usage;
        this.disponible = true ;
        this.chargeMax = ConditionCharge() ;
        this.sieges = ajoutPlaces(typeVehicule); 
        this.listeDepenses =  new ArrayList<>();
        this.transportsEffectues =  new ArrayList<>(); 
    }
    public double ConditionCharge(){ 
        if (this.typeVehicule == typeVehicule.MOTO){ 
            return 0.00;
        }
         if (this.typeVehicule  == typeVehicule.CITADINE){ 
            return 350.00 ;
        }
        if (this.typeVehicule  == typeVehicule.BREAK) { 
                return 500.00;
            }
        if (this.typeVehicule ==typeVehicule.BUS){
            return 1500.00 ; 
        } 
        return 0.00;
    }
    public double getCoefficient() {
        return typeService.getCoefficient();
    }


    public boolean estDisponible() {
        return isDisponible();
    }

    public void changerDisponibilité() {
        if (isDisponible()) {
            setDisponible(false);
        } else {
            setDisponible(true);
        }
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
    public List<Place> ajoutPlaces (TypeVehicule genre){  
        List<Place> generationPlaces = new ArrayList<>() ; 
        if (genre == TypeVehicule.MOTO){ 
            nombreDePlace = 1 ; 
            
        }
        if (genre == TypeVehicule.BREAK || genre == TypeVehicule.CITADINE){ 
            nombreDePlace = 4 ;
        }
        if (genre== TypeVehicule.BUS){
            Place siege1 = new Place(1, Place.Rangee.devant, Place.Colonne.milieu);
            Place siege2 = new Place(2, Place.Rangee.devant, Place.Colonne.fenetreD);
            generationPlaces.add(siege1);
            generationPlaces.add(siege2);
            Place.Rangee variation = Place.Rangee.premier;  // valeur par défaut obligatoire
            int ajout = 5;
            for (int compte = 0; compte < 4; compte++) {
                if (compte == 0) variation = Place.Rangee.premier;
                else if (compte == 1) variation = Place.Rangee.deuxieme;
                else if (compte == 2) variation = Place.Rangee.troisieme;
                else if (compte == 3) variation = Place.Rangee.quatre;

                generationPlaces.add(new Place((ajout*compte) + 3, variation, Place.Colonne.fenetreG));
                generationPlaces.add(new Place((ajout*compte) + 4, variation, Place.Colonne.CouloirG));
                generationPlaces.add(new Place((ajout*compte) + 5, variation, Place.Colonne.milieu));
                generationPlaces.add(new Place((ajout*compte) + 6, variation, Place.Colonne.CouloirD));
                generationPlaces.add(new Place((ajout*compte) + 7, variation, Place.Colonne.fenetreD));
                nombreDePlace = generationPlaces.size();
            }
                
            }
            return generationPlaces ;
            }
    public boolean getDispo (){ 
        return disponible ;
    }
    public boolean setDispo (boolean choix){ 
      return choix ;
    }
    
}
