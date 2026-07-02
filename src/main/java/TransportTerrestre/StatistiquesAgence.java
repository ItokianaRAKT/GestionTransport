package TransportTerrestre;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class StatistiquesAgence {
    public double calculerRecetteTotaleMensuelle(Vehicule vehicule, YearMonth mois) {
        double recette = 0;
        Set<LocalDate> joursService = new HashSet<>();
        int nbTrajetsNational = 0;

        for (Deplacement d : vehicule.getTransportsEffectues()) {
            if (!YearMonth.from(d.getDate()).equals(mois)) continue;

            if (d instanceof CourseTaxi) {
                joursService.add(d.getDate());
            } else if (d instanceof VoyageNational) {
                nbTrajetsNational++;
            }
        }

        if (!vehicule.isAppartientAgence()) {
            if (!joursService.isEmpty()) {
                int nbJours = joursService.size();
                if (vehicule.getTypeVehicule() == Vehicule.TypeVehicule.MOTO) {
                    recette += (vehicule.getTypeService() == Vehicule.TypeService.ECO ? 2000 : 3000) * nbJours;
                } else {
                    recette += (vehicule.getTypeService() == Vehicule.TypeService.ECO ? 5000 : 8000) * nbJours;
                }
            }
            if (nbTrajetsNational > 0) {
                recette += vehicule.getTypeService() == Vehicule.TypeService.ECO
                        ? 40000 + 10000 * nbTrajetsNational
                        : 30000 + 5000 * nbTrajetsNational;
            }
        } else {
            for (Deplacement d : vehicule.getTransportsEffectues()) {
                if (!YearMonth.from(d.getDate()).equals(mois)) continue;
                recette += d.calculerPrix();
            }
        }

        return recette;
    }

    public int calculerDepenseTotaleMensuelle() {
        return 0;
    }

    public int calculerBeneficeTotaleMensuelle() {
        return 0;
    }

    public int calculerDepenseMensuelleVehicule(Vehicule v) {
        return 0;
    }

    public int calculerDepenseTotaleVehicule(Vehicule v) {
        return 0;
    }

    public void estPlusRentable(Trajet t) {
        //vehicule

    }

    public void chauffeurTaxiEstPlusActif() {

    }

    public void chauffeurNationalPlusActif(Trajet t) {
        // plus de km
    }

    public void chauffeurNationalPlusActif() {
        // plus de km}
    }
}
