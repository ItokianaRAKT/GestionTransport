# GestionTransport

Projet d'UE **Programmation 2 — POO** (L1-S2).  
Système de gestion pour une **agence de transport terrestre** (courses en ville, voyages nationaux, location de véhicules).

## Membres du groupe

- Rakotoarivelo **Itokiana** Tsiorisoa: STD25009
- Chan Kwong **Stanley**: STD25049
- Andrianandrasana Kiwin **Mamode**: STD25069
- Miasa Franckcine **Christina**: STD25108

## Fonctionnalités

- **Courses taxi** en ville (immédiates ou programmées)
- **Voyages nationaux** avec réservation de tickets et gestion des bagages
- **Gestion de flotte** : véhicules internes (appartenant à l'agence) et externes (cotisation mensuelle)
- **Gestion des chauffeurs** : affectation aux véhicules, disponibilité, suivi des courses
- **Réservations et tickets** : places, bagages, paiement (Cash, Mobile Money, Carte bancaire)
- **Trajets et arrêts** : calcul de distance, prix par segment
- **Tableau de bord Admin** : CRUD véhicules/chauffeurs/trajets, rapports financiers, statistiques
- **Statistiques** : recettes, dépenses, bénéfices (mensuels/annuels), véhicule le plus rentable, chauffeur le plus actif

## Architecture

```
TransportTerrestre
├── Agence                       # Classe centrale
├── Utilisateur (abstract)       # Parent : Admin, Client, Chauffeur
├── Admin                        # Gestion administrative
├── Client                       # Réservation de transports
├── Chauffeur                    # Conducteur de véhicule
├── Deplacement (abstract)       # Parent : CourseTaxi, VoyageNational
│   ├── CourseTaxi               # Course en ville
│   └── VoyageNational           # Voyage longue distance
├── Vehicule                     # Moto, Taxi, Taxi-brousse, Mini-bus (Éco/VIP)
├── Trajet                       # Route avec arrêts intermédiaires
├── Arret                        # Point d'arrêt (ville)
├── Reservation                  # Regroupe des tickets + paiement
├── Ticket                       # Billet pour un voyage, lié à une place
├── Place                        # Siège dans un véhicule
├── Bagage                       # Bagage associé à un ticket
├── Paiement                     # Transaction financière
├── Depenses                     # Carburant, réparation, entretien, etc.
├── StatistiquesAgence           # Calculs et rapports financiers
└── GestionVehiculeEtChauffeur   # CRUD véhicules/chauffeurs
```

## Stack technique

- **Java 21**
- **Maven**
- **Lombok** 

## Pour commencer

```bash
mvn clean compile
```

Pour exécuter les tests :

```bash
mvn test
```
