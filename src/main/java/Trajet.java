import java.util.ArrayList;
import java.util.List;

public class Trajet {
    int id;
    String Depart ; 
    String Destination ;
    Double Distance ;
    List<Trajet> ArretPossible ; 
    
    public Trajet(int id, String depart, String destination, Double distance ) {
        this.id = id;
        this.Depart = depart;
        this.Destination = destination;
        this.Distance = distance;
        this.ArretPossible = new ArrayList<>();
    } 
      
}
