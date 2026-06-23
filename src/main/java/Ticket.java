import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.UUID;

public class Ticket {
    UUID id ; 
    Agence Agence ; 
    Trajet trajet ;
    Client leClient ;
    double prix ;
    int nombreDePersonne ;
    LocalDate dateDeConfirmation ; 
    Voiture Voiture ;
    enum Payement { 
        CASH, MOBILE_MONEY, CARTE_BANCAIRE
    }
    Payement Payement ;
    
    public Ticket(UUID id, Agence Agence,Trajet trajet, Client leClient, int nombreDePersonne, Voiture maVoiture,Payement Payement) {
        this.id = id;
        this.Agence =Agence ; 
        this.trajet = trajet;
        this.leClient = leClient;
        this.nombreDePersonne = nombreDePersonne;
        this.Voiture = choixVoiture(maVoiture);
        this.prix = PrixTotal();
    } 
    public Voiture choixVoiture (Voiture MonTransport){ 
        AttribuerUnTrajet(MonTransport) ;
        double poids = 0.0;
        for (Bagages b : this.leClient.LesBagages) {
            poids += b.poids;
        }
        
        if (MonTransport.Etat <50.00){ 
            throw new IllegalArgumentException ("Ce Vehicule est en maintenance technique");
        }else 
        if (MonTransport.Usage == enumType.voyage){ 
            if (MonTransport.localisation != Action.EnRoute ){ 
            throw new IllegalArgumentException ("Ce Vehicule est deja parti");
            }
            if (this.trajet!=MonTransport.Sontrajet){ 
                if (MonTransport.Sontrajet.ArretPossible.contains(this.trajet.Destination)){
                    if (this.nombreDePersonne >MonTransport.nombreDePlace ){ 
                        throw new IllegalArgumentException ("pas assez de place");
                    }
                    if (poids > MonTransport.ChargeMAX) {
                        throw new IllegalArgumentException("Vos bagages sont trop lourd");
                    }
                    MonTransport.nombreDePlace -= this.nombreDePersonne;
                    MonTransport.ChargeMAX -= poids;
                    return MonTransport;
                } 
                else throw new IllegalArgumentException ("Le trajet de voyage de cette voiture est pas sur ce trajet");
            }
            
        }
        if (this.nombreDePersonne >MonTransport.nombreDePlace ){ 
            throw new IllegalArgumentException ("pas assez de place");
        }
        if (poids > MonTransport.ChargeMAX) {
        throw new IllegalArgumentException("Vos bagages sont trop lourd");
        }
        
        MonTransport.nombreDePlace -= this.nombreDePersonne;
        MonTransport.ChargeMAX -= poids;

        return MonTransport;
    }

    public Double PrixTotal (){ 
        Double EssenceDePense = 0.0;
        Double PrixTotal  = 0.0; 
        int TauxDinteret =0 ;
        if (this.Voiture.LaVoiture ==typeVehicule.Bus){ 
             EssenceDePense = (this.trajet.Distance * this.Voiture.prixconsomationParKM)/20 ;
             TauxDinteret = 15 ;
        }
        if ((this.Voiture.LaVoiture ==typeVehicule.Berline)||(this.Voiture.LaVoiture ==typeVehicule.RAV4)){ 
            if(this.nombreDePersonne < 4){ 
             EssenceDePense = (this.trajet.Distance * this.Voiture.prixconsomationParKM)/2 ; 
             TauxDinteret = 35 ;}
             else EssenceDePense = this.trajet.Distance * this.Voiture.prixconsomationParKM ;
              TauxDinteret = 25;
        }
        if(this.Voiture.LaVoiture == typeVehicule.Moto){ 
            EssenceDePense = this.trajet.Distance * this.Voiture.prixconsomationParKM;
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
    PrixTotal=(nombreDePersonne*2000) + EssenceDePense + interetDetrajet +interetBagages;  
    
    if((this.Payement == Payement.MOBILE_MONEY) && (this.leClient.CarteBancaire!=null) ){ 
        return PrixTotal+= (PrixTotal * 5)/100;
    }
    if((this.Payement == Payement.CARTE_BANCAIRE) &&(this.leClient.Money!=null)){ 
        return PrixTotal+= (PrixTotal * 10)/100;
    }
    if(this.Voiture.Usage == enumType.voyage){ 
        return PrixTotal +=( PrixTotal * 10 )/100;  
    }
     return PrixTotal;
    }
    public void payementEnLigne (){ 
        
        if(this.Payement == Payement.CARTE_BANCAIRE){ 
           
            if(this.leClient.CarteBancaire.Montant < this.prix){ 
            throw new IllegalArgumentException("Somme inssufisantes Dans votre carte ");
            
            }
             this.leClient.CarteBancaire.Montant -= this.prix ;
        }
        if(this.Payement == Payement.MOBILE_MONEY){ 
            
            if(this.leClient.Money.Montant < this.prix){ 
            throw new IllegalArgumentException("Somme inssufisantes Dans votre Mobile Money ");
            
            }
            this.leClient.Money.Montant -= this.prix ;
    }
    }
    public void PäyementCash (Double Montant){ 
        if(Montant < this.prix){ 
            throw new IllegalArgumentException("Somme inssufisantes");
        }
        this.Agence.tickets.add(this);
        System.out.println("Payement realiser avec sucess!");
    }
    public void Remboursement (UUID id){ 
        boolean existe = this.Agence.tickets.stream()
                         .anyMatch(t -> t.id.equals(id));

        if (!existe) {
        throw new IllegalArgumentException("Ce ticket n'a jamais été enregistré");
        }
        this.Agence.tickets.removeIf(t -> t.id.equals(id));
    }
    public Trajet AttribuerUnTrajet(Voiture Cible){ 
        if((Cible.Usage ==enumType.course)&&(Cible.localisation == Action.Libre)){ 
            return Cible.Sontrajet = this.trajet ;      
        } else 
        if ((Cible.Usage ==enumType.voyage)&&(this.trajet != Cible.Sontrajet)) {
            throw new IllegalArgumentException ("Seul l Agence peut changer MonTrajet") ; 
        }
        else throw new IllegalArgumentException("Le vehicule est encore en mission ");
    }
    
      

}
