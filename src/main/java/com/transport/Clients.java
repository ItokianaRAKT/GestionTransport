package com.transport;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Clients extends Utilisateur {


    public List<Reservation> reservations;


    public Clients (UUID id, String nom, String prenom, String motDePasse, String telephone) {
        super(id, nom, prenom, motDePasse, telephone);
        this.reservations = new ArrayList<>(); // Initialisation de la liste
    }

    private void reserverTransport(Transport t) {
        System.out.println("[Client] Transport réservé.");
    }

    private void annulerReservation(Reservation r) {
        System.out.println("[Client] Réservation annulée.");
    }

    private void annulerTicket(Ticket t) {
        System.out.println("[Client] Ticket annulé.");
    }

    private void consulterReservation() {
        System.out.println("[Client] Consultation des réservations.");
    }
}