package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor


public class Paiement {
    public Paiement(int montant, UUID id, LocalDate datePayement, ModePaiement modePaiement, String nomTitulaire, int montantRembourse) {
        this.montant = montant;
        this.id = id;
        this.datePayement = datePayement;
        this.modePaiement = modePaiement;
        this.nomTitulaire = nomTitulaire;
        this.montantRembourse = montantRembourse;
    }



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
