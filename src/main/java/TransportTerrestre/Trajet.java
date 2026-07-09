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

    public int calculerDistanceTotale() {
        int total = 0;
        for (int d : listeDistances) {
            total += d;
        }
        return total;
    }

    public int calculerDistance(String depart, String arrivee) {
        int indexDepart = -1;
        int indexArrivee = -1;

        for (int i = 0; i < listeArrets.size(); i++) {
            if (listeArrets.get(i).getVille().equals(depart)) {
                indexDepart = i;
            }
            if (listeArrets.get(i).getVille().equals(arrivee)) {
                indexArrivee = i;
            }
        }

        if (indexDepart == -1 || indexArrivee == -1) {
            return 0;
        }

        if (indexDepart > indexArrivee) {
            int tmp = indexDepart;
            indexDepart = indexArrivee;
            indexArrivee = tmp;
        }

        int distance = 0;
        for (int i = indexDepart; i < indexArrivee; i++) {
            distance += listeDistances.get(i);
        }
        return distance;
    }
    public void ajouterArret(Arret arret, int distance) {
        this.listeArrets.add(arret);
        this.listeDistances.add(distance);
    }
    public void supprimerArret(Arret cible) {
        this.listeArrets.removeIf(p -> p.getVille().equals(cible.getVille()));
    }

}
