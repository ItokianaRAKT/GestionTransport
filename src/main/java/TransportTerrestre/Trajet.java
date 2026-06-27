package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
@AllArgsConstructor

public class Trajet {
    private UUID id;
    private Arret depart;
    private Arret arrivee;
    private int distance;
    private int prix;
    private ArrayList<Arret> listeArrets;
    private ArrayList<Integer> listeDistances;

    public int calculerDistanceTotale(){
        int total = 0;
        for (int d : listeDistances){
            total += d;
        }
        return total;
    }

    public int calculerDistance(String depart, String arrivee){
        int indexDepart = listeArrets.indexOf(depart);
        int indexArrivee = listeArrets.indexOf(arrivee);
        int distance = 0;

        if(indexDepart > indexArrivee){
            int tmp = indexDepart;
            indexDepart = indexArrivee;
            indexArrivee = tmp;
        }

        for (int i = indexDepart; i < indexArrivee; i++) {
            distance += listeDistances.get(i);
        }
        return distance;
    }


}
