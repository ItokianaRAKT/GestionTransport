package TransportTerrestre;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Map;
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

    public double calculerDepenseTotaleMensuelle(Agence agence, YearMonth mois) {
        double total = 0;
        for (Vehicule v : agence.getVehiculeAssigneTrajet().keySet()) {
            total += calculerDepenseMensuelleVehicule(v, mois);
        }
        return total;
    }

    public int calculerBeneficeTotaleMensuelle(Agence agence, YearMonth mois) {
        double totalRecette = 0;
        double totalDepense = 0;
        for (Vehicule v : agence.getVehiculeAssigneTrajet().keySet()) {
            totalRecette += calculerRecetteTotaleMensuelle(v, mois);
            totalDepense += calculerDepenseMensuelleVehicule(v, mois);
        }
        return (int)(totalRecette - totalDepense);
    }

    public double calculerDepenseMensuelleVehicule(Vehicule v, YearMonth mois) {
        if (!v.isAppartientAgence()) return 0;
        return v.calculDepensesMensuelle(mois);
    }

    public int calculerDepenseTotaleVehicule(Vehicule v) {
        double total = 0;
        for (Depenses d : v.getListeDeDepenses()) {
            total += d.getMontant();
        }
        return (int)total;
    }

    public Vehicule estPlusRentable(Agence agence, Trajet t) {
        Vehicule meilleur = null;
        double maxBenefice = Double.NEGATIVE_INFINITY;
        for (Map.Entry<Vehicule, Trajet> entry : agence.getVehiculeAssigneTrajet().entrySet()) {
            if (!entry.getValue().getId().equals(t.getId())) continue;
            Vehicule v = entry.getKey();
            double recette = 0;
            for (Deplacement d : v.getTransportsEffectues()) {
                if (d.getTrajet().getId().equals(t.getId())) {
                    recette += d.calculerPrix();
                }
            }
            double benefice = recette - calculerDepenseTotaleVehicule(v);
            if (benefice > maxBenefice) {
                maxBenefice = benefice;
                meilleur = v;
            }
        }
        return meilleur;
    }

    public Trajet estPlusRentable(Agence agence, YearMonth mois) {
        Trajet meilleur = null;
        double maxRecette = 0;
        for (Trajet t : agence.getTrajets()) {
            double recette = 0;
            for (Vehicule v : agence.getVehiculeAssigneTrajet().keySet()) {
                if (!agence.getVehiculeAssigneTrajet().get(v).getId().equals(t.getId())) continue;
                for (Deplacement d : v.getTransportsEffectues()) {
                    if (d.getTrajet().getId().equals(t.getId()) && YearMonth.from(d.getDate()).equals(mois)) {
                        recette += d.calculerPrix();
                    }
                }
            }
            if (recette > maxRecette) {
                maxRecette = recette;
                meilleur = t;
            }
        }
        return meilleur;
    }

    public Trajet estPlusRentable(Agence agence) {
        Trajet meilleur = null;
        double maxRecette = 0;
        for (Trajet t : agence.getTrajets()) {
            double recette = 0;
            for (Vehicule v : agence.getVehiculeAssigneTrajet().keySet()) {
                if (!agence.getVehiculeAssigneTrajet().get(v).getId().equals(t.getId())) continue;
                for (Deplacement d : v.getTransportsEffectues()) {
                    if (d.getTrajet().getId().equals(t.getId())) {
                        recette += d.calculerPrix();
                    }
                }
            }
            if (recette > maxRecette) {
                maxRecette = recette;
                meilleur = t;
            }
        }
        return meilleur;
    }

    public Chauffeur chauffeurTaxiPlusActif(Agence agence) {
        Chauffeur plusActif = null;
        double maxKm = 0;
        for (Chauffeur c : agence.getChauffeurs()) {
            double km = 0;
            for (Vehicule v : agence.getVehiculeAssigneTrajet().keySet()) {
                for (Deplacement d : v.getTransportsEffectues()) {
                    if (d instanceof CourseTaxi && d.getChauffeur().contains(c)) {
                        km += d.getTrajet().getDistance();
                    }
                }
            }
            if (km > maxKm) {
                maxKm = km;
                plusActif = c;
            }
        }
        return plusActif;
    }

    public Chauffeur chauffeurNationalPlusActifSurUnTrajet(Agence agence, Trajet t) {
        Chauffeur plusActif = null;
        int maxTrajets = 0;
        for (Chauffeur c : agence.getChauffeurs()) {
            int count = 0;
            for (Vehicule v : agence.getVehiculeAssigneTrajet().keySet()) {
                for (Deplacement d : v.getTransportsEffectues()) {
                    if (d instanceof VoyageNational && d.getChauffeur().contains(c)
                            && d.getTrajet().getId().equals(t.getId())) {
                        count++;
                    }
                }
            }
            if (count > maxTrajets) {
                maxTrajets = count;
                plusActif = c;
            }
        }
        return plusActif;
    }

    public Chauffeur chauffeurNationalPlusActif(Agence agence) {
        Chauffeur plusActif = null;
        double maxKm = 0;
        for (Chauffeur c : agence.getChauffeurs()) {
            double km = 0;
            for (Vehicule v : agence.getVehiculeAssigneTrajet().keySet()) {
                for (Deplacement d : v.getTransportsEffectues()) {
                    if (d instanceof VoyageNational && d.getChauffeur().contains(c)) {
                        km += d.getTrajet().getDistance();
                    }
                }
            }
            if (km > maxKm) {
                maxKm = km;
                plusActif = c;
            }
        }
        return plusActif;
    }
}
