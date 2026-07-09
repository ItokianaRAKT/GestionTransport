package TransportTerrestre;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Paiement {

    enum ModePaiement {
        CASH, MOBILE_MONEY, CARTE_BANCAIRE
    }

    private UUID id;
    private double montant;
    private LocalDate datePaiement;
    private ModePaiement modePaiement;

    @Builder.Default
    private String referenceTransaction = UUID.randomUUID().toString();

    private String numeroCompte;
    private String nomTitulaire;
    private double montantRembourse;
    private boolean rembourse;

    public boolean estRembourse() {
        return rembourse;
    }

    public void rembourser() {
        this.rembourse = true;
    }

}
