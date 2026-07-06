package com.transport;

import java.util.UUID;

public class Chauffeur extends Utilisateur {


    private int numeroPermis;
    private boolean disponible;
    private Agence agence;
    private Vehicule vehicule;


    public Chauffeur(UUID id, String nom, String prenom, String motDePasse, String telephone,
                     int numeroPermis, boolean disponible, Agence agence, Vehicule vehicule) {
        super(id, nom, prenom, motDePasse, telephone);
        this.numeroPermis = numeroPermis;
        this.disponible = disponible;
        this.agence = agence;
        this.vehicule = vehicule;
    }



    public void commencerTransport() {
        System.out.println("[Chauffeur] Le transport commence.");
    }

    public void terminerTransport() {
        System.out.println("[Chauffeur] Le transport est terminé.");
    }

    public void changerDisponibilite() {
        this.disponible = !this.disponible;
        System.out.println("[Chauffeur] Disponibilité modifiée. Nouvel état : " + this.disponible);
    }


    public int getNumeroPermis() { return numeroPermis; }
    public void setNumeroPermis(int numeroPermis) { this.numeroPermis = numeroPermis; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public Agence getAgence() { return agence; }
    public void setAgence(Agence agence) { this.agence = agence; }

    public Vehicule getVehicule() { return vehicule; }
    public void setVehicule(Vehicule vehicule) { this.vehicule = vehicule; }
}