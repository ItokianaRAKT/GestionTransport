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
        TypeService(double coefficient){
            this.coefficient = coefficient;
        }
        public double getCoefficient() {
            return coefficient;
        }
    }

    enum TypeVehicule{
        MOTO, BREAK, CITADINE, BUS, MINI_BUS
    }
    enum Usage { 
        COURSE , VOYAGE 
    }
    
    private String matricule;
    private Agence agence ;
    private TypeService typeService;
    private List<Place> chaises;
    private TypeVehicule typeVehicule;
    private Usage usage ;
    private Trajet trajet ; 
    private Double consommationParKM ;
    private boolean disponible;
    private double chargeMax;
    private List<Depenses> listeDeDepenses;
    private List <Deplacement> transportsEffectues;



    public Vehicule(String matricule, Double consommationParKM  ,TypeService typeService, TypeVehicule typeVehicule, Usage usage) {
        this.matricule = matricule;
        this.consommationParKM = consommationParKM ;
        this.typeService = typeService;
        this.typeVehicule = typeVehicule;
        this.usage = usage;
        this.disponible = true ;
        this.chargeMax = ConditionCharge() ;
        this.chaises =  new ArrayList<>(); 
        this.listeDeDepenses =  new ArrayList<>(); 
        this.transportsEffectues =  new ArrayList<>(); 
    }
    public double ConditionCharge(){ 
        if (this.typeVehicule == typeVehicule.MOTO){ 
            return 0.00;
        }
         if (this.typeVehicule  == typeVehicule.BREAK){ 
            return 350.00 ;
        }
        if (this.typeVehicule  == typeVehicule.CITADINE) { 
                return 500.00;
            }
        if (this.typeVehicule ==typeVehicule.MINI_BUS){ 
            return 1500.00 ; 
        } 
        return 0.00;
    }
    public double getCoefficient() {
        return typeService.getCoefficient();
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
            chaises.add(chaise);
        }
        if (genre == TypeVehicule.BREAK || genre == TypeVehicule.CITADINE){ 
            Place chaise = new Place(1, Place.Rangee.devant, Place.Colonne.fenetreD);
            Place chaise1 = new Place(2,Place.Rangee.deuxieme,Place.Colonne.fenetreG);
            Place chaise2 =new Place (3,Place.Rangee.deuxieme,Place.Colonne.milieu);
            Place chaise3  =new Place (4,Place.Rangee.deuxieme,Place.Colonne.fenetreD);
            
            this.chaises = new ArrayList<>(List.of(chaise,chaise1,chaise2,chaise3));
        }
        if (genre== TypeVehicule.MINI_BUS){ 
            Place chaise = new Place(1, Place.Rangee.devant, Place.Colonne.milieu);
            Place chaise1 = new Place(2, Place.Rangee.devant, Place.Colonne.fenetreD);
            chaises.add(chaise);
            chaises.add(chaise1);
            Place.Rangee variation = Place.Rangee.premier;  // valeur par défaut obligatoire
            int ajout = 5;
            for (int compte = 0; compte < 4; compte++) {
                if (compte == 0) variation = Place.Rangee.premier;
                else if (compte == 1) variation = Place.Rangee.deuxieme;
                else if (compte == 2) variation = Place.Rangee.troisieme;
                else if (compte == 3) variation = Place.Rangee.quatre;

                chaises.add(new Place((ajout*compte) + 3, variation, Place.Colonne.fenetreG));
                chaises.add(new Place((ajout*compte) + 4, variation, Place.Colonne.CouloirG));
                chaises.add(new Place((ajout*compte) + 5, variation, Place.Colonne.milieu));
                chaises.add(new Place((ajout*compte) + 6, variation, Place.Colonne.CouloirD));
                chaises.add(new Place((ajout*compte) + 7, variation, Place.Colonne.fenetreD));
            }
                
            }
            }
    public boolean getDispo (){ 
        return disponible ;
    }
    public boolean setDispo (){ 
    if (disponible == false){ 
        return disponible = true ;
    }
    return disponible = false ; 
}
    
}
