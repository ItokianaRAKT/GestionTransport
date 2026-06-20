package TransportTerrestre;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Agence {
    private UUID id;
    private Admin admin;
    private String ville;
    private String adresse;
    private String telephone;
    private ArrayList<Vehicule> vehicules;
    private ArrayList<Chauffeur> chauffeurs;
    private ArrayList<Trajet> trajets;
    private ArrayList<Voyage> voyagesEffectues;


    public void ConfirmationTicket (Ticket vente){
        tickets.add(vente);
    }
    public double Voirdepense (){
        double totalDepense = 0.0 ;
        List<Integer> dejaPrit = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if(!dejaPrit.contains(ticket.Vehicule.matricule)){
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
