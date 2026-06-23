import java.util.ArrayList;
import java.util.List;


public class Agence {
    int id ; 
    Admin chef ; 
    List<Ticket> tickets ; 
    List<Voiture> LeurVoiture ;
    //trajetvoyage
    List<Trajet> international ;
    List<Chauffeur> Personnel ;

    public void enregistrerTrajetVoyage (Trajet voyage){ 
        if (!international.contains(voyage)){ 
            international.add(voyage);
        }else {
            throw new IllegalArgumentException("Il y a deja ce trajet") ;
        }
    }
    public void accepterUnArret (Trajet Arret,Trajet Voyage){ 
        
        for (Trajet V : international) {
            if (V.ArretPossible.contains(Arret)){ 
                throw new IllegalArgumentException("Cette arret est deja prit en compte"); 
            }
            if (V == Voyage){ 
                V.ArretPossible.add(Arret);
            }else { 
                throw new IllegalArgumentException("ce trajet de voyage n est pas enregistrer par l agence"); 
            }   
        }
    }
    
    public Agence(int id, Admin chef) {
        this.id = id;
        this.chef = chef;
        this.LeurVoiture= new ArrayList<>();
        this.tickets= new ArrayList<>();
        this.international= new ArrayList<>();
    }
    public void donnerIVoyage(int matricule , Trajet Route){ 
        for (Voiture B : this.LeurVoiture) {
            if((B.Usage == enumType.voyage) &&(B.matricule ==matricule)){ 
                B.Sontrajet = Route ;
            }
        }
    }
    public void lancerUnVoyage (Voiture lancer){ 
        for (Voiture V : LeurVoiture) {
            if((V.matricule == lancer.matricule) &&(V.localisation == Action.Libre)){ 
                V.localisation =Action.EnRoute ;
            }
        }
    }
    public void NouvelleVoiture (Voiture nouvelle ){ 
        LeurVoiture.add(nouvelle);
    }
    
    public double Voirdepense (){ 
        double totalDepense = 0.0 ; 
        List<Integer> dejaPrit = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if(!dejaPrit.contains(ticket.Voiture.matricule)){
                dejaPrit.add(ticket.Voiture.matricule);
            totalDepense+=ticket.trajet.Distance*ticket.Voiture.prixconsomationParKM;}
        }
        System.out.println("les depenses :");
        return totalDepense ; 
    }
    public double voirGain (){ 
        double totalGain = 0.0 ; 
        List<Ticket> dejaPrit = new ArrayList<>();
        for (Ticket ticket : tickets) {
            
                dejaPrit.add(ticket);
                totalGain += ticket.prix ;
            
        }
        System.out.println("les Gains :");
        return totalGain ;
    }
    public double Benefices (){ 
        double perte = Voirdepense(); 
        double gain = voirGain();
        
        System.out.println("Benefice :" );
        return gain - perte ; 

    }
    

}
