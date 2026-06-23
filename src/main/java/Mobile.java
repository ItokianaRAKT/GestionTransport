import java.util.UUID;

public class Mobile extends Compte{

    public Mobile(UUID id, Double montant, Client detenteur, String email, String motDePasse) {
        super(id, montant, detenteur, email, motDePasse);
    }
    
}
