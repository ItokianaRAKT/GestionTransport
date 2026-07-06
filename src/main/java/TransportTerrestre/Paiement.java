package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor

    private UUID id;
    private int montant;
    private String nomTitulaire;
    private int montantRembourse;
    private int numeroCompte;
    private String referenceTransaction;
    private ModePaiement modePaiement;
    private LocalDate datePaiement;
    private boolean rembourse;

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
