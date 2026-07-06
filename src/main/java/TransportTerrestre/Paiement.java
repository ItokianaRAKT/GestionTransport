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

    private String id;
    private int montant;
    private String nomTitulaire;
    private int numeroCompte;
    private String referenceTransaction;
    private ModePaiement modePaiement;
    private LocalDate datePaiement;
    private boolean rembourse;

    public Paiement(String id, double montant, String nomTitulaire, int numeroCompte, ModePaiement modePaiement) {
        this.id = id;
        this.montant = montant;
        this.nomTitulaire = nomTitulaire;
        this.numeroCompte = numeroCompte;
        this.modePaiement = modePaiement;
        this.datePaiement = LocalDate.now();
        this.rembourse = false;
    }

    public boolean estRembourse() { return rembourse; }
    public void rembourser() { this.rembourse = true; }
}
