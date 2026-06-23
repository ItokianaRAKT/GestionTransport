import java.util.UUID;

public class CompteBancaire extends Compte{

    public CompteBancaire(UUID id, Double montant, Client detenteur, String email, String motDePasse) {
        super(id, montant, detenteur, email, motDePasse);
    }
    
}
