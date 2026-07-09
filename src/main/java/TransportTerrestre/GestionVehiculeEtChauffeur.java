package TransportTerrestre;


public class GestionVehiculeEtChauffeur {
    private Agence agence;

    public GestionVehiculeEtChauffeur(Agence agence) {
        this.agence = agence;
    }

    public void ajouterVehicule(Vehicule vehicule, Trajet trajet) {
        if (agence.getVehiculeAssigneTrajet().containsKey(vehicule)) {
            throw new IllegalArgumentException("Ce vehicule est déjà enregistré");
        }
        agence.getVehiculeAssigneTrajet().put(vehicule, trajet);
        vehicule.setDisponible(true);
    }

    public void supprimerVehicule(Vehicule vehicule) {
        if (!agence.getVehiculeAssigneTrajet().containsKey(vehicule)) {
            throw new IllegalArgumentException("Ce vehicule n'est pas enregistré");
        }
        agence.getVehiculeAssigneTrajet().remove(vehicule);
        agence.getChauffeurs().stream()
                .filter(chauffeur -> vehicule.equals(chauffeur.getVehicule()))
                .forEach(chauffeur -> chauffeur.setVehicule(null));
        vehicule.setDisponible(false);
    }

    public void ajouterChauffeur(Chauffeur chauffeur) {
        if (agence.getChauffeurs().contains(chauffeur)) {
            throw new IllegalArgumentException("Ce chauffeur est déjà enregistré.");
        }
        agence.getChauffeurs().add(chauffeur);
        chauffeur.setAgence(agence);
    }

    public void supprimerChauffeur(Chauffeur chauffeur) {
        if (!agence.getChauffeurs().contains(chauffeur)) {
            throw new IllegalArgumentException("Ce chauffeur n'est pas enregistré");
        }
        agence.getChauffeurs().remove(chauffeur);
    }

    public void affecterVehicule(Vehicule vehicule, Trajet trajet) {
        if (!agence.getVehiculeAssigneTrajet().containsKey(vehicule)) {
            throw new IllegalArgumentException("Ce vehicule n'est pas enregistré");
        }
        if (!agence.getTrajets().contains(trajet)) {
            throw new IllegalArgumentException("Ce trajet n'est pas enregistré");
        }
        agence.getVehiculeAssigneTrajet().put(vehicule, trajet);
    }

    public void affecterChauffeur(Chauffeur chauffeur, Vehicule vehicule) {
        if (!agence.getChauffeurs().contains(chauffeur)) {
            throw new IllegalArgumentException("Ce chauffeur n'est pas enregistré");
        }
        if (!agence.getVehiculeAssigneTrajet().containsKey(vehicule)) {
            throw new IllegalArgumentException("Ce vehicule n'est pas enregistré");
        }
        if (!vehicule.estDisponible()) {
            throw new IllegalStateException("Ce vehicule n'est pas disponible");
        }
        agence.getChauffeurs().stream()
                .filter(c -> vehicule.equals(c.getVehicule()))
                .forEach(c -> c.setVehicule(null));
        chauffeur.setVehicule(vehicule);
    }
}
