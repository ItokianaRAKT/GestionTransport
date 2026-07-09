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
            throw new IllegalArgumentException("Ville introuvable dans le trajet");
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
    public void ajouterArret(Arret avant, Arret nouvelArret, int distance) {
        int index = listeArrets.indexOf(avant);
        if (index == -1) {
            throw new IllegalArgumentException("Arret avant introuvable dans le trajet");
        }

        if (index == listeArrets.size() - 1) {
            listeArrets.add(nouvelArret);
            listeDistances.add(distance);
        } else {
            int oldDistance = listeDistances.get(index);
            if (distance > oldDistance) {
                throw new IllegalArgumentException("La distance depasse le segment existant");
            }
            listeArrets.add(index + 1, nouvelArret);
            listeDistances.set(index, distance);
            listeDistances.add(index + 1, oldDistance - distance);
        }

        this.distance = calculerDistanceTotale();
    }
    public void supprimerArret(Arret cible) {
        int index = listeArrets.indexOf(cible);
        if (index == -1) {
            throw new IllegalArgumentException("Arret introuvable dans le trajet");
        }

        if (index == 0) {
            listeArrets.remove(0);
            listeDistances.remove(0);
            if (!listeArrets.isEmpty()) {
                this.depart = listeArrets.get(0);
            }
        } else if (index == listeArrets.size() - 1) {
            listeArrets.remove(index);
            listeDistances.remove(index - 1);
            if (!listeArrets.isEmpty()) {
                this.arrivee = listeArrets.get(listeArrets.size() - 1);
            }
        } else {
            listeDistances.set(index - 1, listeDistances.get(index - 1) + listeDistances.get(index));
            listeDistances.remove(index);
            listeArrets.remove(index);
        }

        this.distance = calculerDistanceTotale();
    }

}
