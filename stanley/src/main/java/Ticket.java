public class Ticket {
    int id ; 
    Trajet trajet ;
    Client leClient ;
    double prix ;
    int nombreDePersonne ;
    Voiture Voiture ;
    public Ticket(int id, Trajet trajet, Client leClient, int nombreDePersonne, Voiture maVoiture) {
        this.id = id;
        this.trajet = trajet;
        this.leClient = leClient;
        this.nombreDePersonne = nombreDePersonne;
        this.Voiture = choixVoiture(maVoiture);
        this.prix = PrixTotal();
    } 
    public Voiture choixVoiture (Voiture MonTransport){ 
        if (MonTransport.Etat <50.00){ 
            throw new IllegalArgumentException ("Ce Vehicule est en maintenance technique");
        }else 
        if (MonTransport.Usage == enumType.voyage){ 
            if (this.trajet!=MonTransport.Sontrajet){ 
                throw new IllegalArgumentException ("Le trajet de voyage de cette voiture est deja defini");
            }
        }
        if (this.nombreDePersonne >MonTransport.nombreDePlace ){ 
            throw new IllegalArgumentException ("pas assez de place");
        }
        double poids = 0.0;
        for (Bagages b : this.leClient.LesBagages) {
            poids += b.poids;
        }
        if (poids > MonTransport.ChargeMAX) {
        throw new IllegalArgumentException("Vos bagages sont trop lourd");
        }
        
        MonTransport.nombreDePlace -= this.nombreDePersonne;
        MonTransport.ChargeMAX -= poids;

        return MonTransport;
    }

    public Double PrixTotal (){ 
        Double EssenceDePense = this.trajet.Distance * this.Voiture.prixconsomationParKM ;
        Double interetDetrajet = ((EssenceDePense*30)/100);
        Double poidsTotalBagages = 0.0 ;
        double interetBagages = 0.0 ;
        for (Bagages ChaqueBagages : this.leClient.LesBagages) {
            poidsTotalBagages += ChaqueBagages.poids ;        
        }
        if (poidsTotalBagages > 20) {
    Double poidsEnSurcharge = poidsTotalBagages - 20; // franchise 20kg
    interetBagages = poidsEnSurcharge * 1000; // 1000 Ar par kg en surcharge
}

        return (nombreDePersonne*2000) + EssenceDePense + interetDetrajet +interetBagages;

    }
    
      

}
