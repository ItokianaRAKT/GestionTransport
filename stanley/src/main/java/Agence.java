import java.util.ArrayList;
import java.util.List;


public class Agence {
    int id ; 
    Admin chef ; 
    List<Ticket> tickets ; 
    List<Voiture> LeurVoiture ;
    public void ConfirmationTicket (Ticket vente){ 
        tickets.add(vente);
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
