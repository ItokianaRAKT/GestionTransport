package TransportTerrestre;

import java.time.LocalDate;
import java.util.UUID;

public class Paiement {
    enum ModePaiement {
        CASH, MOBILE_MONEY, CARTE_BANCAIRE
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
