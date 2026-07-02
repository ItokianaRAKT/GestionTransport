import java.util.ArrayList;
import java.util.List;



public class Agence {
    int id ; 
    Admin chef ; 
    List<Reservation> reservations ; 
    List<Vehicule> LeurVoiture ;
    //trajetvoyage
    List<Trajet> international ;
    List<Chauffeur> Personnel ;
    
    public void AssignerUneVoiture (Chauffeur service,Vehicule VOITURE){ 
        if ((service.sonPermis == typePermis.A)&&(VOITURE.LaVoiture == typeVehicule.MOTO)){ 
            if (VOITURE.getChauffeur()!= null){ 
            VOITURE.setChauffeur(service) ; 
            }
            throw new IllegalArgumentException("il y a deja un chauffeur"); 
        }
        if ((service.sonPermis == typePermis.B)&&((VOITURE.LaVoiture == typeVehicule.BREAK) || (VOITURE.LaVoiture == typeVehicule.CITADINE))){ 
            if (VOITURE.getChauffeur()!= null){ 
            VOITURE.setChauffeur(service) ; 
            }
            throw new IllegalArgumentException("il y a deja un chauffeur"); 
        }
        if ((service.sonPermis == typePermis.D)&&(VOITURE.LaVoiture == typeVehicule.BUS)){ 
            if (VOITURE.getChauffeur()!= null){ 
            VOITURE.setChauffeur(service) ; 
            }
            throw new IllegalArgumentException("il y a deja un chauffeur"); 
        }
    }

    public void enregistrerTrajetVoyage (Trajet voyage){ 
        if (!international.contains(voyage)){ 
            international.add(voyage);
        }else {
            throw new IllegalArgumentException("Il y a deja ce trajet") ;
        }
    }
    public void accepterUnArret (Trajet Arret,Trajet Voyage){ 
        
        for (Trajet V : international) {
            if (V.arret.contains(Arret)){ 
                throw new IllegalArgumentException("Cette arret est deja prit en compte"); 
            }
            if (V == Voyage){ 
                V.arret.add(Arret);
            }else { 
                throw new IllegalArgumentException("ce trajet de voyage n est pas enregistrer par l agence"); 
            }   
        }
    }
    
    public Agence(int id, Admin chef) {
        this.id = id;
        this.chef = chef;
        this.LeurVoiture= new ArrayList<>();
        this.reservations= new ArrayList<>();
        this.international= new ArrayList<>();
    }
    public void donnerIVoyage(int matricule , Trajet Route){ 
        for (Vehicule B : this.LeurVoiture) {
            if((B.Usage == enumType.voyage) &&(B.matricule ==matricule)){ 
                B.setSonTrajet(Route) ;
            }
        }
    }
    public void lancerUnVoyage (Vehicule lancer){ 
        for (Vehicule V : LeurVoiture) {
            if((V.matricule == lancer.matricule) &&(V.disponible== Disponible.Libre)){ 
                V.disponible =Disponible.EnRoute ;
            }
        }
    }
    public void NouvelleVoiture (Vehicule nouvelle ){ 
        LeurVoiture.add(nouvelle);
    }
    
    public double Voirdepense (){ 
        double totalDepense = 0.0 ; 
        List<Integer> dejaPrit = new ArrayList<>();
        for (Reservation ticket : reservations) {
            if(!dejaPrit.contains(ticket.vehicule.matricule)){
                dejaPrit.add(ticket.vehicule.matricule);
            totalDepense+=ticket.trajet.Distance*ticket.vehicule.getPrixConsomationParKM();}
        }
        System.out.println("les depenses :");
        return totalDepense ; 
    }
    public double voirGain (){ 
        double totalGain = 0.0 ; 
        List<Reservation> dejaPrit = new ArrayList<>();
        for (Reservation ticket : reservations) {
            
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
