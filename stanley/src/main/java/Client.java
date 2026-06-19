import java.util.ArrayList;
import java.util.List;

public class Client extends Utilisateur { 
    List<Bagages> LesBagages; 
    
    public Client(int id, String nom, String prenom) {
        super(id, nom, prenom);
        this.LesBagages = new ArrayList<>();
    }
    public int ajoutBagages (Bagages sac){
        System.out.println("les bagages actuel:"+LesBagages.size());
        this.LesBagages.add(sac);
        return LesBagages.size();
    }
}