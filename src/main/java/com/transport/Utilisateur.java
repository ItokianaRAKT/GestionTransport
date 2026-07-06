package com.transport;

import java.util.UUID;

public abstract class Utilisateur {
    private UUID id;
    private String nom;
    private String prenom;
    private String motDePasse;
    private String telephone;

    public Utilisateur(UUID id, String nom, String prenom, String motDePasse, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
    }


    public boolean seConnecter() {
        return true;
    }

    public void seDeconnecter() {
        System.out.println("[Utilisateur] Déconnexion réussie.");
    }

    public void modifierInformation() {
        System.out.println("[Utilisateur] Informations modifiées.");
    }

    public void changerMDP() {
        System.out.println("[Utilisateur] Mot de passe modifié.");
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
}
