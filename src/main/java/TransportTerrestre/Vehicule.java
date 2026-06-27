package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.YearMonth;
import java.util.ArrayList;

@Data
@AllArgsConstructor

public class Vehicule {
    enum TypeService {
        ECO(1.0), VIP(1.5);
        private final double coefficient;
        TypeService(double coefficient){
            this.coefficient = coefficient;
        }
        public double getCoefficient() {
            return coefficient;
        }
    }

    enum TypeVehicule{
        MOTO, TAXI, TAXI_BROUSSE, MINI_BUS
    }

    private String matricule;
    private int nombreDePlaces;
    private TypeService typeService;
    private ArrayList<Place> places;
    private TypeVehicule typeVehicule;
    private boolean disponible;
    private double chargeMax;
    private boolean appartientAgence;
    private ArrayList<Depenses> listeDeDepenses;
    private ArrayList <Deplacement> transportsEffectues;

    public double getCoefficient() {
        return typeService.getCoefficient();
    }

    public boolean estDisponible(){
        return isDisponible();
    }

    public void changerDisponibilité(){
        if (isDisponible()){
            setDisponible(false);
        }
        else {
            setDisponible(true);
        }
    }

    public void ajouterDepense(Depenses depenses){
        listeDeDepenses.add(depenses);
        System.out.println("Dépense insérée avec succès");
    }

    public double calculDepensesMensuelle(YearMonth mois){
        double total = 0;
        for(Depenses D : listeDeDepenses ){
            if (YearMonth.from(D.getDate()).equals(mois)){
                total += D.getMontant();
            }
        }
        return total;
    }

    public double calculRecetteMensuelle(YearMonth mois) {
        double total = 0;
        for(Deplacement deplacement : transportsEffectues){
            if (YearMonth.from(deplacement.getDate()).equals(mois))
                total += deplacement.calculerPrix();
        }
        return total;
    }

    public double calculBeneficeMensuel(YearMonth mois){
        double total = 0;
        YearMonth moisCible = mois;
        total = calculDepensesMensuelle(moisCible) - calculRecetteMensuelle(moisCible);
        return total;
    }
}
