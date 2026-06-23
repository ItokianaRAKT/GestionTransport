

public class Voiture {
    int matricule; 
    double prixconsomationParKM ; 
    int nombreDePlace ; 
    Trajet Sontrajet ; 
    typeVehicule LaVoiture ;
    Double ChargeMAX;
    Chauffeur Chauffeur ; 
    Double Etat ;
    enumType Usage ;
    Action localisation ;
     
    public Voiture(int matricule, int nombreDePlace, Chauffeur chauffeur,double prixconsomationParKM
        ,enumType Usage,typeVehicule laVoiture) {
        this.matricule = matricule;
        this.nombreDePlace = nombreDePlace;
        this.Chauffeur = chauffeur;
        LaVoiture = laVoiture;
        
        this.ChargeMAX = ConditionCharge();
        this.Etat = 100.00 ;
        this.Usage= Usage ;
        this.localisation = Action.Libre ;
    } 
    
    public double ConditionCharge(){ 
        if (nombreDePlace==2){ 
            return 0.00;
        }
        else if (nombreDePlace == 4){ 
            if (LaVoiture == typeVehicule.RAV4) { 
                return 500.00;
            }else { return 350.00 ;}
        }
        else if (nombreDePlace == 20){ 
            return 1500.00 ; 
        } 
        return 0.00;
    }
    
    
}
