import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

public class Vehicule {
    public int matricule; 
    private double prixConsomationParKM ; 
    private Trajet sonTrajet ; 
    public typeVehicule LaVoiture ;
    public Double ChargeMAX;
    private Chauffeur Chauffeur ; 
    private Double Etat ;
    enumType Usage ;
    Disponible disponible;
    private List <Ticket> tickets ; 
    public List <Place> places ;
     
    
    public Vehicule(int matricule, Chauffeur chauffeur,double prixconsomationParKM
        ,enumType Usage,typeVehicule laVoiture) {
        this.matricule = matricule;
        
        this.Chauffeur = chauffeur;
        LaVoiture = laVoiture;
        this.tickets =new ArrayList<>(); 
        this.ChargeMAX = ConditionCharge();
        this.Etat = 100.00 ;
        this.Usage= Usage ;
        this.disponible = Disponible.Libre ;
    } 
    public Vehicule(int matricule, int nombreDePlace,double prixconsomationParKM
        ,enumType Usage,typeVehicule laVoiture) {
        this.matricule = matricule;
        this.tickets =new ArrayList<>(); 
        LaVoiture = laVoiture;
        this.ChargeMAX = ConditionCharge();
        this.Etat = 100.00 ;
        this.Usage= Usage ;
        this.disponible = Disponible.Libre ;
    } 
    
    public double ConditionCharge(){ 
        if (this.LaVoiture== typeVehicule.MOTO){ 
            return 0.00;
        }
         if (this.LaVoiture == typeVehicule.BREAK){ 
            return 350.00 ;
        }
        if (this.LaVoiture == typeVehicule.CITADINE) { 
                return 500.00;
            }
        if (this.LaVoiture==typeVehicule.BUS){ 
            return 1500.00 ; 
        } 
        return 0.00;
    }
    
    
    public void ajoutPlaces (typeVehicule genre){  
        if (genre == typeVehicule.MOTO){ 
            Place chaise = new Place(1); 
            places.add(chaise);
        }
        if (genre == typeVehicule.BREAK || genre == typeVehicule.CITADINE){ 
            Place chaise = new Place(1, Place.Ranger.devant, Place.Colonne.fenetreD);
            Place chaise1 = new Place(2,Place.Ranger.deuxieme,Place.Colonne.fenetreG);
            Place chaise2 =new Place (3,Place.Ranger.deuxieme,Place.Colonne.milieu);
            Place chaise3  =new Place (4,Place.Ranger.deuxieme,Place.Colonne.fenetreD);
            
            this.places = new ArrayList<>(List.of(chaise,chaise1,chaise2,chaise3));
        }
        if (genre== typeVehicule.BUS){ 
            Place chaise = new Place(1, Place.Ranger.devant, Place.Colonne.milieu);
            Place chaise1 = new Place(2, Place.Ranger.devant, Place.Colonne.fenetreD);
            places.add(chaise);
            places.add(chaise1);
            int compte ;
            int ajout = 5 ; 
            for (compte = 0; compte<4; compte++){  
                places.add(new Place( (ajout*compte)+3,Place.Ranger.deuxieme,Place.Colonne.fenetreG));
                places.add(new Place( (ajout*compte)+5,Place.Ranger.deuxieme,Place.Colonne.CouloirG));
                places.add(new Place( (ajout*compte)+6,Place.Ranger.deuxieme,Place.Colonne.Strapotin));
                places.add(new Place( (ajout*compte)+7,Place.Ranger.deuxieme,Place.Colonne.CouloirD));
                places.add(new Place( (ajout*compte)+8,Place.Ranger.deuxieme,Place.Colonne.fenetreD));
                
            }
        }
    }
    
    public int getMatricule() {
        return matricule;
    }
    public double getPrixConsomationParKM() {
        return prixConsomationParKM;
    }
    public Trajet getSonTrajet() {
        return sonTrajet;
    }
    public Trajet setSonTrajet(Trajet cible ){ 
        return this.sonTrajet = cible ;
    }
    public typeVehicule getLaVoiture() {
        return LaVoiture;
    }
    public Double getChargeMAX() {
        return ChargeMAX;
    }
    public Chauffeur getChauffeur() {
        return Chauffeur;
    }
    public Chauffeur setChauffeur(Chauffeur designer) {
        return this.Chauffeur = designer;
    }
    public Double getEtat() {
        return Etat;
    }
    public enumType getUsage() {
        return Usage;
    }
    public Disponible getDisponible() {
        return disponible;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }
    public List<Place> getPlaces() {
        return places;
    }
    public void ajoutTickets (Ticket add){ 
        tickets.add(add);
    }
    
}
