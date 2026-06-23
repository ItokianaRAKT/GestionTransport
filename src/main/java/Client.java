import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;



public class Client extends Utilisateur { 
    List<Bagages> LesBagages;
    CompteBancaire CarteBancaire ;
    Mobile Money ;
    
    
    public Client(UUID id, String nom, String prenom, String email, String motDePasse) {
        super(id, nom, prenom, email, motDePasse);
        this.LesBagages = new ArrayList<>();
        
    }
    public Client(UUID id, String nom, String prenom, String email, String motDePasse
        , CompteBancaire cartBancaire 
    ) {
        super(id, nom, prenom, email, motDePasse);
        this.LesBagages = new ArrayList<>();
        this.CarteBancaire = cartBancaire ; 
    }
    public Client(UUID id, String nom, String prenom, String email, String motDePasse
        , Mobile Money 
    ) {
        super(id, nom, prenom, email, motDePasse);
        this.LesBagages = new ArrayList<>();
        this.Money = Money ; 
    }
    public Client(UUID id, String nom, String prenom, String email, String motDePasse
        , Mobile Money ,CompteBancaire cartBancaire
    ) {
        super(id, nom, prenom, email, motDePasse);
        this.LesBagages = new ArrayList<>();
        this.Money = Money ; 
        this.CarteBancaire = cartBancaire ;
    }  
    public int ajoutBagages (Bagages sac){
        if (Connexion != true){ 
            throw new IllegalArgumentException ("T es pas encore connecter ...");  
        }
        System.out.println("les bagages actuel:"+LesBagages.size());
        this.LesBagages.add(sac);
        return LesBagages.size();
    }
    public void ModifierLesInfo (String select,String ChoixOperation){
        if (Connexion != true){ 
            throw new IllegalArgumentException ("T es pas encore connecter ...");  
        }
        if(ChoixOperation =="nom"){ 
            changerDeNom(select);
        }else 
        if(ChoixOperation =="prenom"){ 
            changerDePrenom(select);
        } else 
        if(ChoixOperation =="motDePasse"){ 
            changerDeMotDePasse(select);
        }
        
    }
    public void changerDeNom (String requeteNom){ 
        this.nom = requeteNom ;
    }; 
    public void changerDePrenom (String requetePrenom){ 
        this.prenom = requetePrenom ;
    }; 
    public void changerDeMotDePasse (String requeteMotDePasse){ 
        this.motDePasse = requeteMotDePasse;
    }; 
    public void consulterTicket(Agence KOFI){ 
        if (Connexion != true){ 
            throw new IllegalArgumentException ("T es pas encore connecter ...");  
        }
        for ( Ticket Monticket : KOFI.tickets) {
            if (Monticket.leClient.id == this.id){ 
                System.out.println(Monticket);
            }
        }
    }
    public void AnnulerTicket (Ticket refuser,Agence KOFI){ 
        if (Connexion != true){ 
            throw new IllegalArgumentException ("T es pas encore connecter ...");  
        }
        KOFI.tickets.removeIf(p->p.id == refuser.id );
    }

}