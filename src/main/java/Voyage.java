import java.util.ArrayList;
import java.util.List;

public class Voyage extends Trajet{
    public List<Voyage> arret ;

    public Voyage(int id, String depart, String destination, Double distance) {
        super(id, depart, destination, distance);
        this.arret = new ArrayList<>();
    } 
    
}
