package TransportTerrestre;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GestionVehiculeEtChauffeurTest {

    private GestionVehiculeEtChauffeur gestion;
    private Agence agence;
    private Admin admin;
    private Vehicule vehicule1;
    private Vehicule vehicule2;
    private Vehicule vehicule3;

    private Trajet trajetA;
    private Trajet trajetB;
    private Trajet trajetC;

    private Chauffeur chauffeurA;
    private Chauffeur chauffeurB;
    private Chauffeur chauffeurSansVehicule;

    private Arret arretTana;
    private Arret arretFiana;
    private Arret arretAnkatso;

    @BeforeEach
    void setUp() {
        arretTana = new Arret(UUID.randomUUID(), "Antananarivo");
        arretFiana = new Arret(UUID.randomUUID(), "Fianarantsoa");
        arretAnkatso = new Arret(UUID.randomUUID(), "Ankatso");

        trajetA = new Trajet(
                UUID.randomUUID(), arretTana, arretFiana, 400, 10000,
                new ArrayList<>(List.of(arretTana, arretFiana)),
                new ArrayList<>(List.of(400))
        );
        trajetB = new Trajet(
                UUID.randomUUID(), arretTana, arretAnkatso, 10, 3000,
                new ArrayList<>(List.of(arretTana, arretAnkatso)),
                new ArrayList<>(List.of(10))
        );
        trajetC = new Trajet(
                UUID.randomUUID(), arretFiana, arretAnkatso, 500, 15000,
                new ArrayList<>(List.of(arretFiana, arretAnkatso)),
                new ArrayList<>(List.of(500))
        );

        agence = new Agence(
                UUID.randomUUID(), null, "Tana", "Adresse", "034",
                new HashMap<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(List.of(trajetA, trajetB, trajetC))
        );
        admin = new Admin(UUID.randomUUID(), "Admin", "Test", "a@t.com", "034", "pass", agence);
        agence.setAdmin(admin);

        vehicule1 = new Vehicule("V1", 8.0, Vehicule.TypeService.ECO, Vehicule.TypeVehicule.CITADINE, Vehicule.Usage.COURSE);
        vehicule1.setAgence(agence);
        vehicule2 = new Vehicule("V2", 12.0, Vehicule.TypeService.VIP, Vehicule.TypeVehicule.BUS, Vehicule.Usage.VOYAGE);
        vehicule2.setAgence(agence);
        vehicule3 = new Vehicule("V3", 5.0, Vehicule.TypeService.ECO, Vehicule.TypeVehicule.MOTO, Vehicule.Usage.COURSE);
        vehicule3.setAgence(agence);

        chauffeurA = new Chauffeur(UUID.randomUUID(), Chauffeur.Permis.B, "Jean", "Rakoto", "j@t.com", "034", "pass", "P001", vehicule1);
        chauffeurB = new Chauffeur(UUID.randomUUID(), Chauffeur.Permis.D, "Paul", "Rabe", "p@t.com", "034", "pass", "P002", vehicule2);
        chauffeurSansVehicule = new Chauffeur(UUID.randomUUID(), Chauffeur.Permis.B, "Solo", "Test", "s@t.com", "034", "pass", "P003");

        gestion = new GestionVehiculeEtChauffeur(agence);
    }

    // ─── ajouterVehicule ──────────────────────────────

    @Test
    void ajouterVehicule_ajouteDansMapEtDisponible() {
        gestion.ajouterVehicule(vehicule1, trajetA);

        assertTrue(agence.getVehiculeAssigneTrajet().containsKey(vehicule1));
        assertEquals(trajetA, agence.getVehiculeAssigneTrajet().get(vehicule1));
        assertTrue(vehicule1.estDisponible());
    }

    @Test
    void ajouterVehicule_dejaExistant_throws() {
        gestion.ajouterVehicule(vehicule1, trajetA);

        assertThrows(IllegalArgumentException.class,
                () -> gestion.ajouterVehicule(vehicule1, trajetB));
    }

    @Test
    void ajouterVehicule_plusieursVehicules() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        gestion.ajouterVehicule(vehicule2, trajetB);

        assertEquals(2, agence.getVehiculeAssigneTrajet().size());
        assertEquals(trajetA, agence.getVehiculeAssigneTrajet().get(vehicule1));
        assertEquals(trajetB, agence.getVehiculeAssigneTrajet().get(vehicule2));
    }

    @Test
    void ajouterVehicule_metDisponibleTrue() {
        vehicule1.setDisponible(false);
        gestion.ajouterVehicule(vehicule1, trajetA);

        assertTrue(vehicule1.estDisponible());
    }

    // ─── supprimerVehicule ─────────────────────────────

    @Test
    void supprimerVehicule_enleveDeLaMap() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        gestion.supprimerVehicule(vehicule1);

        assertFalse(agence.getVehiculeAssigneTrajet().containsKey(vehicule1));
    }

    @Test
    void supprimerVehicule_nonExistant_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> gestion.supprimerVehicule(vehicule1));
    }

    @Test
    void supprimerVehicule_metNonDisponible() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        gestion.supprimerVehicule(vehicule1);

        assertFalse(vehicule1.estDisponible());
    }

    @Test
    void supprimerVehicule_desassocieChauffeurLie() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        agence.getChauffeurs().add(chauffeurA);
        chauffeurA.setVehicule(vehicule1);

        gestion.supprimerVehicule(vehicule1);

        assertNull(chauffeurA.getVehicule());
    }

    @Test
    void supprimerVehicule_neTouchePasAutresChauffeurs() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        gestion.ajouterVehicule(vehicule2, trajetB);
        agence.getChauffeurs().addAll(List.of(chauffeurA, chauffeurB));
        chauffeurA.setVehicule(vehicule1);
        chauffeurB.setVehicule(vehicule2);

        gestion.supprimerVehicule(vehicule1);

        assertNull(chauffeurA.getVehicule());
        assertEquals(vehicule2, chauffeurB.getVehicule());
    }

    @Test
    void supprimerVehicule_aucunChauffeurLie_neCrassePas() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        agence.getChauffeurs().add(chauffeurSansVehicule);

        gestion.supprimerVehicule(vehicule1);

        assertFalse(agence.getVehiculeAssigneTrajet().containsKey(vehicule1));
    }

    // ─── ajouterChauffeur ──────────────────────────────

    @Test
    void ajouterChauffeur_ajouteDansListe() {
        gestion.ajouterChauffeur(chauffeurA);

        assertTrue(agence.getChauffeurs().contains(chauffeurA));
    }

    @Test
    void ajouterChauffeur_dejaExistant_throws() {
        gestion.ajouterChauffeur(chauffeurA);

        assertThrows(IllegalArgumentException.class,
                () -> gestion.ajouterChauffeur(chauffeurA));
    }

    @Test
    void ajouterChauffeur_plusieursChauffeurs() {
        gestion.ajouterChauffeur(chauffeurA);
        gestion.ajouterChauffeur(chauffeurB);

        assertEquals(2, agence.getChauffeurs().size());
    }

    // ─── supprimerChauffeur ────────────────────────────

    @Test
    void supprimerChauffeur_enleveDeLaListe() {
        gestion.ajouterChauffeur(chauffeurA);
        gestion.supprimerChauffeur(chauffeurA);

        assertFalse(agence.getChauffeurs().contains(chauffeurA));
    }

    @Test
    void supprimerChauffeur_nonExistant_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> gestion.supprimerChauffeur(chauffeurA));
    }

    @Test
    void supprimerChauffeur_unSeulChauffeurSupprime() {
        gestion.ajouterChauffeur(chauffeurA);
        gestion.ajouterChauffeur(chauffeurB);
        gestion.supprimerChauffeur(chauffeurA);

        assertTrue(agence.getChauffeurs().contains(chauffeurB));
        assertEquals(1, agence.getChauffeurs().size());
    }

    // ─── affecterVehicule ──────────────────────────────

    @Test
    void affecterVehicule_changeTrajet() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        gestion.affecterVehicule(vehicule1, trajetB);

        assertEquals(trajetB, agence.getVehiculeAssigneTrajet().get(vehicule1));
    }

    @Test
    void affecterVehicule_vehiculeNonEnregistre_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> gestion.affecterVehicule(vehicule1, trajetA));
    }

    @Test
    void affecterVehicule_trajetNonEnregistre_throws() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        Trajet trajetInconnu = new Trajet(
                UUID.randomUUID(), arretTana, arretFiana, 999, 99999,
                new ArrayList<>(), new ArrayList<>()
        );

        assertThrows(IllegalArgumentException.class,
                () -> gestion.affecterVehicule(vehicule1, trajetInconnu));
    }

    @Test
    void affecterVehicule_vehiculeNonEnregistreEtTrajetInconnu_throws() {
        Trajet inconnu = new Trajet(UUID.randomUUID(), arretTana, arretFiana, 1, 1, new ArrayList<>(), new ArrayList<>());

        assertThrows(IllegalArgumentException.class,
                () -> gestion.affecterVehicule(vehicule1, inconnu));
    }

    // ─── affecterChauffeur ─────────────────────────────

    @Test
    void affecterChauffeur_affecteVehicule() {
        agence.getChauffeurs().add(chauffeurSansVehicule);
        gestion.ajouterVehicule(vehicule1, trajetA);
        gestion.affecterChauffeur(chauffeurSansVehicule, vehicule1);

        assertEquals(vehicule1, chauffeurSansVehicule.getVehicule());
    }

    @Test
    void affecterChauffeur_chauffeurNonEnregistre_throws() {
        gestion.ajouterVehicule(vehicule1, trajetA);

        assertThrows(IllegalArgumentException.class,
                () -> gestion.affecterChauffeur(chauffeurA, vehicule1));
    }

    @Test
    void affecterChauffeur_vehiculeNonEnregistre_throws() {
        agence.getChauffeurs().add(chauffeurA);

        assertThrows(IllegalArgumentException.class,
                () -> gestion.affecterChauffeur(chauffeurA, vehicule1));
    }

    @Test
    void affecterChauffeur_desassocieAncienChauffeur() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        agence.getChauffeurs().addAll(List.of(chauffeurA, chauffeurB));
        chauffeurA.setVehicule(vehicule1);

        gestion.affecterChauffeur(chauffeurB, vehicule1);

        assertNull(chauffeurA.getVehicule());
        assertEquals(vehicule1, chauffeurB.getVehicule());
    }

    @Test
    void affecterChauffeur_vehiculeSansAncienChauffeur_neCrassePas() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        agence.getChauffeurs().add(chauffeurSansVehicule);

        gestion.affecterChauffeur(chauffeurSansVehicule, vehicule1);

        assertEquals(vehicule1, chauffeurSansVehicule.getVehicule());
    }

    @Test
    void affecterChauffeur_deuxVehiculesDistincts() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        gestion.ajouterVehicule(vehicule2, trajetB);
        agence.getChauffeurs().addAll(List.of(chauffeurA, chauffeurB));

        gestion.affecterChauffeur(chauffeurA, vehicule1);
        gestion.affecterChauffeur(chauffeurB, vehicule2);

        assertEquals(vehicule1, chauffeurA.getVehicule());
        assertEquals(vehicule2, chauffeurB.getVehicule());
    }

    @Test
    void affecterChauffeur_remplaceMemeVehicule_ancienNull() {
        gestion.ajouterVehicule(vehicule1, trajetA);
        agence.getChauffeurs().addAll(List.of(chauffeurA, chauffeurB));
        chauffeurA.setVehicule(vehicule1);

        gestion.affecterChauffeur(chauffeurB, vehicule1);

        assertNull(chauffeurA.getVehicule());
    }

    @Test
    void affecterChauffeur_tousDeuxNonEnregistres_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> gestion.affecterChauffeur(chauffeurA, vehicule1));
    }
}
