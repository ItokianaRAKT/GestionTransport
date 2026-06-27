package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor

public class Paiement {
    enum ModePaiement {
        CASH, MOBILE_MONEY, CARTE_BANCAIRE
    }
    enum EtatPaiement {
        EFFECTUE, REMBOURSE
    }
    private UUID id;
    private int montant;
    private LocalDate datePayement;
    private ModePaiement modePaiement;
    private String referenceTransaction;
    private String numeroCompte;
    private String nomTitulaire;
    private int montantRembourse;
}
