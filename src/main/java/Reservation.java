
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Reservation {
    UUID id ; 
    Agence agence ; 
    Trajet trajet ;
    Client leClient ;
    double prix ;
    LocalDate dateDeConfirmation ; 
    Vehicule vehicule  ;
    List <Place> placesClient ; 
    enum Payement { 
        CASH, MOBILE_MONEY, CARTE_BANCAIRE
    }
    Payement Payement ;
    
    public Reservation(UUID id, Agence Agence,Trajet trajet, Client leClient, int nombreDePersonne, Vehicule vehicule ,Payement Payement) {
        this.id = id;
        this.agence =Agence ; 
        this.trajet = trajet;
        this.leClient = leClient;      
        this.vehicule = choixVoiture(vehicule);
        this.prix = PrixTotal();
    } 
    public List <Place> VoirPlaces (Vehicule MonTransport){ 
        List <Place> placesDispo = new ArrayList<>();
        for (Place place : MonTransport.places) {
            if(place.disponibilite == true ){ 
                placesDispo.add(place);
            }
        }
        return placesDispo ;
    }
    public Vehicule choixVoiture (Vehicule MonTransport){ 
        
        AttribuerUnTrajet(MonTransport) ;
        double poids = 0.0;
        for (Bagages b : this.leClient.LesBagages) {
            poids += b.poids;
        }
        
        if (MonTransport.getEtat() <50.00){ 
            throw new IllegalArgumentException ("Ce Vehicule est en maintenance technique");
        }else 
        if (MonTransport.Usage == enumType.voyage){ 
            if (MonTransport.disponible != Disponible.EnRoute ){ 
            throw new IllegalArgumentException ("Ce Vehicule est deja parti");
            }
            if (this.trajet!=MonTransport.getSonTrajet()){ 
                if (MonTransport.getSonTrajet().arret.contains(this.trajet.Destination)){
                    if (this.placesClient.size() >VoirPlaces(MonTransport).size()){ 
                        throw new IllegalArgumentException ("pas assez de place");
                    }
                    if (poids > MonTransport.ChargeMAX) {
                        throw new IllegalArgumentException("Vos bagages sont trop lourd");
                    }
                    MonTransport.ChargeMAX -= poids;
                    return MonTransport;
                } 
                else throw new IllegalArgumentException ("Le trajet de voyage de cette voiture est pas sur ce trajet");
            }
            
        }
        if (this.placesClient.size() >VoirPlaces(MonTransport).size()){ 
            throw new IllegalArgumentException ("pas assez de place");
        }
        if (poids > MonTransport.ChargeMAX) {
        throw new IllegalArgumentException("Vos bagages sont trop lourd");
        }
        
        MonTransport.ChargeMAX -= poids;

        return MonTransport;
    }

    public Double PrixTotal (){ 
        Double EssenceDePense = 0.0;
        Double PrixTotal  = 0.0; 
        int TauxDinteret =0 ;
        if (this.vehicule.LaVoiture ==typeVehicule.BUS){ 
             EssenceDePense = (this.trajet.Distance * this.vehicule.getPrixConsomationParKM())/20 ;
             TauxDinteret = 15 ;
        }
        if ((this.vehicule.LaVoiture ==typeVehicule.BREAK)||(this.vehicule.LaVoiture ==typeVehicule.CITADINE)){ 
            if(this.placesClient.size() < 4){ 
             EssenceDePense = (this.trajet.Distance * this.vehicule.getPrixConsomationParKM())/2 ; 
             TauxDinteret = 35 ;}
             else EssenceDePense = this.trajet.Distance * this.vehicule.getPrixConsomationParKM() ;
              TauxDinteret = 25;
        }
        if(this.vehicule.LaVoiture == typeVehicule.MOTO){ 
            EssenceDePense = this.trajet.Distance * this.vehicule.getPrixConsomationParKM();
            TauxDinteret = 20;
        }
        Double interetDetrajet = ((EssenceDePense*TauxDinteret)/100);
        Double poidsTotalBagages = 0.0 ;
        double interetBagages = 0.0 ;
        for (Bagages ChaqueBagages : this.leClient.LesBagages) {
            poidsTotalBagages += ChaqueBagages.poids ;        
        }
        if (poidsTotalBagages > 20) {
    Double poidsEnSurcharge = poidsTotalBagages - 20; // franchise 20kg
    interetBagages = poidsEnSurcharge * 1000; // 1000 Ar par kg en surcharge
}   
    PrixTotal=(placesClient.size()*2000) + EssenceDePense + interetDetrajet +interetBagages;  
    
    if((this.Payement == Payement.MOBILE_MONEY) && (this.leClient.CarteBancaire!=null) ){ 
        return PrixTotal+= (PrixTotal * 5)/100;
    }
    if((this.Payement == Payement.CARTE_BANCAIRE) &&(this.leClient.Money!=null)){ 
        return PrixTotal+= (PrixTotal * 10)/100;
    }
    if(this.vehicule.Usage == enumType.voyage){ 
        return PrixTotal +=( PrixTotal * 10 )/100;  
    }
     return PrixTotal;
    }
    public void payementEnLigne (Payement type_payement) {
        if (this.agence.reservations.contains(type_payement)){
            throw new IllegalArgumentException("Reservation deja payer");
        }
        if(type_payement == Payement.CARTE_BANCAIRE){ 
           
            if(this.leClient.CarteBancaire.Montant < this.prix){ 
            throw new IllegalArgumentException("Somme inssufisantes Dans votre carte ");
            
            }
             this.leClient.CarteBancaire.Montant -= this.prix ;
        }
        if(type_payement == Payement.MOBILE_MONEY){ 
            
            if(this.leClient.Money.Montant < this.prix){ 
            throw new IllegalArgumentException("Somme inssufisantes Dans votre Mobile Money ");
            
            }
            this.leClient.Money.Montant -= this.prix ;
            
        } 
        for (Place place : placesClient) {
            this.vehicule.ajoutTickets(new Ticket(leClient, place, type_payement, prix));;   
        }   
        this.agence.reservations.add(this);
        }
             
    
    public void PäyementCash (Double Montant){ 
        if(Montant < this.prix){ 
            throw new IllegalArgumentException("Somme inssufisantes");
        }
        this.agence.reservations.add(this);
        for (Place place : placesClient) {
            Ticket ticket = new Ticket(leClient, place, Payement, prix);
        }   
        System.out.println("Payement realiser avec sucess!");
    }
    public void Remboursement (UUID id){ 
        boolean existe = this.agence.reservations.stream()
                         .anyMatch(t -> t.id.equals(id));

        if (!existe) {
        throw new IllegalArgumentException("Ce ticket n'a jamais été enregistré");
        }
        this.agence.reservations.removeIf(t -> t.id.equals(id));
    }
    public Trajet AttribuerUnTrajet(Vehicule Cible){ 
        if((Cible.Usage ==enumType.course)&&(Cible.disponible == Disponible.Libre)){ 
            return Cible.setSonTrajet(this.trajet)  ;      
        } else 
        if ((Cible.Usage ==enumType.voyage)&&(this.trajet != Cible.getSonTrajet())) {
            throw new IllegalArgumentException ("Seul l Agence peut changer MonTrajet") ; 
        }
        else throw new IllegalArgumentException("Le vehicule est encore en mission ");
    }
    
    
      

}
