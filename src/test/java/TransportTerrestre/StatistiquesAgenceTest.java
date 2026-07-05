package TransportTerrestre;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;

public class StatistiquesAgenceTest {

    private StatistiquesAgence stats;
    private Agence agence;
    private Admin admin;
    private Client client;

    private Vehicule vAgencyCitadine;
    private Vehicule vAgencyBus;
    private Vehicule vExterneMoto;

    private Trajet trajetNational;
    private Trajet trajetUrbain;

    private Chauffeur chauffeur1;
    private Chauffeur chauffeur2;
    private Chauffeur chauffeurSansVehicule;

    private Arret arretTana;
    private Arret arretFiana;
    private Arret arretAnkadifotsy;
    private Arret arretAnkatso;

    private YearMonth mois;
    private LocalDate dateDansMois;
    private LocalDate dateAutreMois;

    @BeforeEach
    void setUp() {
        stats = new StatistiquesAgence();
        mois = YearMonth.of(2026, 6);
        dateDansMois = LocalDate.of(2026, 6, 15);
        dateAutreMois = LocalDate.of(2026, 5, 15);

        arretTana = new Arret(UUID.randomUUID(), "Antananarivo");
        arretFiana = new Arret(UUID.randomUUID(), "Fianarantsoa");
        arretAnkadifotsy = new Arret(UUID.randomUUID(), "Ankadifotsy");
        arretAnkatso = new Arret(UUID.randomUUID(), "Ankatso");

        trajetNational = new Trajet(
                UUID.randomUUID(), arretTana, arretFiana, 400, 10000,
                new ArrayList<>(List.of(arretTana, arretFiana)),
                new ArrayList<>(List.of(400))
        );
        trajetUrbain = new Trajet(
                UUID.randomUUID(), arretAnkadifotsy, arretAnkatso, 5, 2000,
                new ArrayList<>(List.of(arretAnkadifotsy, arretAnkatso)),
                new ArrayList<>(List.of(5))
        );

        agence = new Agence(
                UUID.randomUUID(), null, "Tana", "Adresse", "034",
                new HashMap<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(List.of(trajetNational, trajetUrbain))
        );
        admin = new Admin(UUID.randomUUID(), "Admin", "Test", "a@t.com", "034", "pass", agence);
        agence.setAdmin(admin);

        client = new Client(UUID.randomUUID(), "Client", "Test", "c@t.com", "034", "pass", new ArrayList<>());

        vAgencyCitadine = new Vehicule("001A", 8.0, Vehicule.TypeService.ECO, Vehicule.TypeVehicule.CITADINE, Vehicule.Usage.COURSE);
        vAgencyCitadine.setAgence(agence);

        vAgencyBus = new Vehicule("002B", 12.0, Vehicule.TypeService.ECO, Vehicule.TypeVehicule.BUS, Vehicule.Usage.VOYAGE);
        vAgencyBus.setAgence(agence);

        vExterneMoto = new Vehicule("003C", 3.0, Vehicule.TypeService.VIP, Vehicule.TypeVehicule.MOTO, Vehicule.Usage.COURSE);
        vExterneMoto.setAgence(null);

        chauffeur1 = new Chauffeur(UUID.randomUUID(), Chauffeur.Permis.B, "Jean", "Rakoto", "j@t.com", "034", "pass", "P001", vAgencyCitadine);
        chauffeur2 = new Chauffeur(UUID.randomUUID(), Chauffeur.Permis.D, "Paul", "Rabe", "p@t.com", "034", "pass", "P002", vAgencyBus);
        chauffeurSansVehicule = new Chauffeur(UUID.randomUUID(), Chauffeur.Permis.B, "Solo", "Test", "s@t.com", "034", "pass", "P003");

        agence.getChauffeurs().addAll(List.of(chauffeur1, chauffeur2, chauffeurSansVehicule));
        agence.getVehiculeAssigneTrajet().put(vAgencyCitadine, trajetUrbain);
        agence.getVehiculeAssigneTrajet().put(vAgencyBus, trajetNational);
    }

    // ─── Helpers ─────────────────────────────────────

    private CourseTaxi creerCourse(Vehicule v, Trajet t, LocalDate date, Chauffeur c, double tarif) {
        CourseTaxi course = new CourseTaxi(
                UUID.randomUUID(), date, LocalTime.of(8, 0), t,
                Deplacement.StatutTransport.TERMINE, v,
                new ArrayList<>(List.of(c)), 0, client,
                CourseTaxi.TypeCourse.IMMEDIATE, tarif
        );
        course.setPrixTotal((int) course.calculerPrix());
        return course;
    }

    private VoyageNational creerVoyage(Vehicule v, Trajet t, LocalDate date, Chauffeur c) {
        return new VoyageNational(
                UUID.randomUUID(), date, LocalTime.of(8, 0), t,
                Deplacement.StatutTransport.TERMINE, v,
                new ArrayList<>(List.of(c)), 0, 240,
                LocalTime.of(12, 0), new ArrayList<>()
        );
    }

    // ─── calculerRecetteTotaleMensuelle ──────────────

    @Test
    void recette_vehiculeExterne_motoVip_unTaxi() {
        CourseTaxi course = creerCourse(vExterneMoto, trajetUrbain, dateDansMois, chauffeur1, 1000);
        vExterneMoto.getTransportsEffectues().add(course);

        double recette = stats.calculerRecetteTotaleMensuelle(vExterneMoto, mois);

        assertEquals(3000, recette, 0.001);
    }

    @Test
    void recette_vehiculeExterne_motoVip_troisCoursesMemeJour() {
        for (int i = 0; i < 3; i++) {
            CourseTaxi c = creerCourse(vExterneMoto, trajetUrbain, dateDansMois, chauffeur1, 1000);
            vExterneMoto.getTransportsEffectues().add(c);
        }

        double recette = stats.calculerRecetteTotaleMensuelle(vExterneMoto, mois);

        assertEquals(3000, recette, 0.001);
    }

    @Test
    void recette_vehiculeExterne_motoVip_troisCoursesJoursDifferents() {
        for (int i = 0; i < 3; i++) {
            CourseTaxi c = creerCourse(vExterneMoto, trajetUrbain, dateDansMois.plusDays(i), chauffeur1, 1000);
            vExterneMoto.getTransportsEffectues().add(c);
        }

        double recette = stats.calculerRecetteTotaleMensuelle(vExterneMoto, mois);

        assertEquals(9000, recette, 0.001);
    }

    @Test
    void recette_vehiculeExterne_citadineEco_unTaxi() {
        vExterneMoto.setTypeVehicule(Vehicule.TypeVehicule.CITADINE);
        vExterneMoto.setTypeService(Vehicule.TypeService.ECO);
        CourseTaxi course = creerCourse(vExterneMoto, trajetUrbain, dateDansMois, chauffeur1, 1000);
        vExterneMoto.getTransportsEffectues().add(course);

        double recette = stats.calculerRecetteTotaleMensuelle(vExterneMoto, mois);

        assertEquals(5000, recette, 0.001);
    }

    @Test
    void recette_vehiculeExterne_citadineVip_unTaxi() {
        vExterneMoto.setTypeVehicule(Vehicule.TypeVehicule.CITADINE);
        vExterneMoto.setTypeService(Vehicule.TypeService.VIP);
        CourseTaxi course = creerCourse(vExterneMoto, trajetUrbain, dateDansMois, chauffeur1, 1000);
        vExterneMoto.getTransportsEffectues().add(course);

        double recette = stats.calculerRecetteTotaleMensuelle(vExterneMoto, mois);

        assertEquals(8000, recette, 0.001);
    }

    @Test
    void recette_vehiculeExterne_unVoyageNationalEco() {
        vExterneMoto.setTypeVehicule(Vehicule.TypeVehicule.CITADINE);
        vExterneMoto.setTypeService(Vehicule.TypeService.ECO);
        VoyageNational voyage = creerVoyage(vExterneMoto, trajetNational, dateDansMois, chauffeur1);
        vExterneMoto.getTransportsEffectues().add(voyage);

        double recette = stats.calculerRecetteTotaleMensuelle(vExterneMoto, mois);

        assertEquals(40000 + 10000, recette, 0.001);
    }

    @Test
    void recette_vehiculeExterne_unVoyageNationalVip() {
        vExterneMoto.setTypeService(Vehicule.TypeService.VIP);
        VoyageNational voyage = creerVoyage(vExterneMoto, trajetNational, dateDansMois, chauffeur1);
        vExterneMoto.getTransportsEffectues().add(voyage);

        double recette = stats.calculerRecetteTotaleMensuelle(vExterneMoto, mois);

        assertEquals(30000 + 5000, recette, 0.001);
    }

    @Test
    void recette_vehiculeExterne_mixTaxiEtNational() {
        vExterneMoto.setTypeService(Vehicule.TypeService.ECO);
        vExterneMoto.setTypeVehicule(Vehicule.TypeVehicule.CITADINE);
        CourseTaxi course = creerCourse(vExterneMoto, trajetUrbain, dateDansMois, chauffeur1, 1000);
        VoyageNational voyage = creerVoyage(vExterneMoto, trajetNational, dateDansMois, chauffeur1);
        vExterneMoto.getTransportsEffectues().add(course);
        vExterneMoto.getTransportsEffectues().add(voyage);

        double recette = stats.calculerRecetteTotaleMensuelle(vExterneMoto, mois);

        assertEquals(5000 + 40000 + 10000, recette, 0.001);
    }

    @Test
    void recette_vehiculeExterne_aucunTransport() {
        double recette = stats.calculerRecetteTotaleMensuelle(vExterneMoto, mois);

        assertEquals(0, recette, 0.001);
    }

    @Test
    void recette_vehiculeAgency_troisCoursesTaxi() {
        for (int i = 0; i < 3; i++) {
            CourseTaxi c = creerCourse(vAgencyCitadine, trajetUrbain, dateDansMois.plusDays(i), chauffeur1, 1000);
            vAgencyCitadine.getTransportsEffectues().add(c);
        }

        double recette = stats.calculerRecetteTotaleMensuelle(vAgencyCitadine, mois);

        double attendu = 3 * (5 * 1000 * 1.0);
        assertEquals(attendu, recette, 0.001);
    }

    @Test
    void recette_vehiculeAgency_transportHorsMoisIgnore() {
        CourseTaxi course = creerCourse(vAgencyCitadine, trajetUrbain, dateAutreMois, chauffeur1, 1000);
        vAgencyCitadine.getTransportsEffectues().add(course);

        double recette = stats.calculerRecetteTotaleMensuelle(vAgencyCitadine, mois);

        assertEquals(0, recette, 0.001);
    }

    // ─── calculerDepenseMensuelleVehicule ────────────

    @Test
    void depenseMensuelle_vehiculeExterne_retourneZero() {
        double depense = stats.calculerDepenseMensuelleVehicule(vExterneMoto, mois);

        assertEquals(0, depense, 0.001);
    }

    @Test
    void depenseMensuelle_vehiculeAgency_avecDepenses() {
        Depenses d = new Depenses(dateDansMois, 50000, "Carburant", vAgencyCitadine);
        vAgencyCitadine.getListeDepenses().add(d);

        double depense = stats.calculerDepenseMensuelleVehicule(vAgencyCitadine, mois);

        assertEquals(50000, depense, 0.001);
    }

    @Test
    void depenseMensuelle_vehiculeAgency_plusieursDepensesMois() {
        vAgencyCitadine.getListeDepenses().add(new Depenses(dateDansMois, 30000, "", vAgencyCitadine));
        vAgencyCitadine.getListeDepenses().add(new Depenses(dateDansMois.plusDays(1), 20000, "", vAgencyCitadine));

        double depense = stats.calculerDepenseMensuelleVehicule(vAgencyCitadine, mois);

        assertEquals(50000, depense, 0.001);
    }

    @Test
    void depenseMensuelle_vehiculeAgency_depenseAutreMoisIgnore() {
        vAgencyCitadine.getListeDepenses().add(new Depenses(dateAutreMois, 30000, "", vAgencyCitadine));

        double depense = stats.calculerDepenseMensuelleVehicule(vAgencyCitadine, mois);

        assertEquals(0, depense, 0.001);
    }

    // ─── calculerDepenseTotaleMensuelle ──────────────

    @Test
    void depenseTotaleMensuelle_plusieursVehicules() {
        vAgencyCitadine.getListeDepenses().add(new Depenses(dateDansMois, 30000, "", vAgencyCitadine));
        vAgencyBus.getListeDepenses().add(new Depenses(dateDansMois, 70000, "", vAgencyBus));

        double total = stats.calculerDepenseTotaleMensuelle(agence, mois);

        assertEquals(100000, total, 0.001);
    }

    @Test
    void depenseTotaleMensuelle_aucuneDepense() {
        double total = stats.calculerDepenseTotaleMensuelle(agence, mois);

        assertEquals(0, total, 0.001);
    }

    // ─── calculerBeneficeTotaleMensuelle ─────────────

    @Test
    void beneficeTotalMensuel_positif() {
        CourseTaxi course = creerCourse(vAgencyCitadine, trajetUrbain, dateDansMois, chauffeur1, 1000);
        vAgencyCitadine.getTransportsEffectues().add(course);
        vAgencyCitadine.getListeDepenses().add(new Depenses(dateDansMois, 2000, "", vAgencyCitadine));

        int benefice = stats.calculerBeneficeTotaleMensuelle(agence, mois);

        int recette = (int) (5 * 1000 * 1.0);
        assertEquals(recette - 2000, benefice);
    }

    @Test
    void beneficeTotalMensuel_negatif() {
        vAgencyCitadine.getListeDepenses().add(new Depenses(dateDansMois, 50000, "", vAgencyCitadine));

        int benefice = stats.calculerBeneficeTotaleMensuelle(agence, mois);

        assertTrue(benefice < 0);
    }

    // ─── calculerDepenseTotaleVehicule ───────────────

    @Test
    void depenseTotaleVehicule_avecDepenses() {
        vAgencyCitadine.getListeDepenses().add(new Depenses(dateDansMois, 10000, "", vAgencyCitadine));
        vAgencyCitadine.getListeDepenses().add(new Depenses(dateAutreMois, 5000, "", vAgencyCitadine));

        int total = stats.calculerDepenseTotaleVehicule(vAgencyCitadine);

        assertEquals(15000, total);
    }

    @Test
    void depenseTotaleVehicule_sansDepense() {
        int total = stats.calculerDepenseTotaleVehicule(vAgencyCitadine);

        assertEquals(0, total);
    }

    // ─── estPlusRentable(Agence, Trajet) ─────────────

    @Test
    void plusRentable_parTrajet_vehiculeAplusDeRecette() {
        Vehicule v2 = new Vehicule("002", 8.0, Vehicule.TypeService.ECO, Vehicule.TypeVehicule.CITADINE, Vehicule.Usage.COURSE);
        v2.setAgence(agence);
        agence.getVehiculeAssigneTrajet().put(v2, trajetUrbain);

        CourseTaxi c1 = creerCourse(vAgencyCitadine, trajetUrbain, dateDansMois, chauffeur1, 1000);
        vAgencyCitadine.getTransportsEffectues().add(c1);

        Chauffeur c2 = new Chauffeur(UUID.randomUUID(), Chauffeur.Permis.B, "Deux", "Test", "d@t.com", "034", "pass", "P004", v2);
        agence.getChauffeurs().add(c2);
        CourseTaxi c2Course = creerCourse(v2, trajetUrbain, dateDansMois.plusDays(1), c2, 2000);
        v2.getTransportsEffectues().add(c2Course);

        Vehicule meilleur = stats.estPlusRentable(agence, trajetUrbain);

        assertEquals(v2, meilleur);
    }

    @Test
    void plusRentable_parTrajet_unSeulVehicule() {
        CourseTaxi c1 = creerCourse(vAgencyCitadine, trajetUrbain, dateDansMois, chauffeur1, 1000);
        vAgencyCitadine.getTransportsEffectues().add(c1);

        Vehicule meilleur = stats.estPlusRentable(agence, trajetUrbain);

        assertEquals(vAgencyCitadine, meilleur);
    }

    @Test
    void plusRentable_parTrajet_aucunVehicule() {
        Trajet tAutre = new Trajet(UUID.randomUUID(), arretTana, arretAnkatso, 10, 3000,
                new ArrayList<>(List.of(arretTana, arretAnkatso)), new ArrayList<>(List.of(10)));

        Vehicule meilleur = stats.estPlusRentable(agence, tAutre);

        assertNull(meilleur);
    }

    // ─── estPlusRentable(Agence, YearMonth) ──────────

    @Test
    void plusRentable_parMois_trajetPlusBeneficiaire() {
        CourseTaxi c1 = creerCourse(vAgencyCitadine, trajetUrbain, dateDansMois, chauffeur1, 100000);
        vAgencyCitadine.getTransportsEffectues().add(c1);
        vAgencyCitadine.getListeDepenses().add(new Depenses(dateDansMois, 1000, "", vAgencyCitadine));

        CourseTaxi c2 = creerCourse(vAgencyBus, trajetNational, dateDansMois, chauffeur2, 100);
        vAgencyBus.getTransportsEffectues().add(c2);
        vAgencyBus.getListeDepenses().add(new Depenses(dateDansMois, 500000, "", vAgencyBus));

        Trajet meilleur = stats.estPlusRentable(agence, mois);

        assertNotNull(meilleur);
        assertEquals(trajetUrbain.getId(), meilleur.getId());
    }

    @Test
    void plusRentable_parMois_aucunTrajet() {
        agence.getTrajets().clear();

        Trajet meilleur = stats.estPlusRentable(agence, mois);

        assertNull(meilleur);
    }

    // ─── estPlusRentable(Agence) ─────────────────────

    @Test
    void plusRentable_allTime_trajetPlusBeneficiaire() {
        CourseTaxi c1 = creerCourse(vAgencyCitadine, trajetUrbain, dateDansMois, chauffeur1, 100000);
        vAgencyCitadine.getTransportsEffectues().add(c1);

        CourseTaxi c2 = creerCourse(vAgencyBus, trajetNational, dateDansMois, chauffeur2, 100);
        vAgencyBus.getTransportsEffectues().add(c2);
        vAgencyBus.getListeDepenses().add(new Depenses(dateDansMois, 500000, "", vAgencyBus));

        Trajet meilleur = stats.estPlusRentable(agence);

        assertNotNull(meilleur);
        assertEquals(trajetUrbain.getId(), meilleur.getId());
    }

    // ─── chauffeurTaxiPlusActif ──────────────────────

    @Test
    void chauffeurTaxiPlusActif_plusDeKm() {
        CourseTaxi c1 = creerCourse(vAgencyCitadine, trajetUrbain, dateDansMois, chauffeur1, 1000);
        vAgencyCitadine.getTransportsEffectues().add(c1);

        Chauffeur meilleur = stats.chauffeurTaxiPlusActif(agence);

        assertEquals(chauffeur1, meilleur);
    }

    @Test
    void chauffeurTaxiPlusActif_plusieursChauffeurs() {
        Vehicule v2 = new Vehicule("004", 8.0, Vehicule.TypeService.ECO, Vehicule.TypeVehicule.CITADINE, Vehicule.Usage.COURSE);
        v2.setAgence(agence);
        Chauffeur c2 = new Chauffeur(UUID.randomUUID(), Chauffeur.Permis.B, "Deux", "Test", "d@t.com", "034", "pass", "P005", v2);
        agence.getChauffeurs().add(c2);

        CourseTaxi courseCh1 = creerCourse(vAgencyCitadine, trajetUrbain, dateDansMois, chauffeur1, 1000);
        vAgencyCitadine.getTransportsEffectues().add(courseCh1);

        CourseTaxi courseCh2_a = creerCourse(v2, trajetUrbain, dateDansMois.plusDays(1), c2, 1000);
        CourseTaxi courseCh2_b = creerCourse(v2, trajetUrbain, dateDansMois.plusDays(2), c2, 1000);
        v2.getTransportsEffectues().add(courseCh2_a);
        v2.getTransportsEffectues().add(courseCh2_b);

        Chauffeur meilleur = stats.chauffeurTaxiPlusActif(agence);

        assertEquals(c2, meilleur);
    }

    @Test
    void chauffeurTaxiPlusActif_chauffeurSansVehiculeIgnore() {
        CourseTaxi c1 = creerCourse(vAgencyCitadine, trajetUrbain, dateDansMois, chauffeur1, 1000);
        vAgencyCitadine.getTransportsEffectues().add(c1);

        Chauffeur meilleur = stats.chauffeurTaxiPlusActif(agence);

        assertEquals(chauffeur1, meilleur);
        assertNotNull(meilleur);
    }

    @Test
    void chauffeurTaxiPlusActif_aucunChauffeur() {
        agence.getChauffeurs().clear();

        Chauffeur meilleur = stats.chauffeurTaxiPlusActif(agence);

        assertNull(meilleur);
    }

    // ─── chauffeurNationalPlusActifSurUnTrajet ───────

    @Test
    void chauffeurNationalPlusActifSurUnTrajet_plusDeTrajets() {
        Vehicule v2 = new Vehicule("005", 10.0, Vehicule.TypeService.ECO, Vehicule.TypeVehicule.BUS, Vehicule.Usage.VOYAGE);
        v2.setAgence(agence);
        Chauffeur c2 = new Chauffeur(UUID.randomUUID(), Chauffeur.Permis.D, "Deux", "Test", "d@t.com", "034", "pass", "P006", v2);
        agence.getChauffeurs().add(c2);

        VoyageNational v1 = creerVoyage(vAgencyBus, trajetNational, dateDansMois, chauffeur2);
        vAgencyBus.getTransportsEffectues().add(v1);

        VoyageNational v2a = creerVoyage(v2, trajetNational, dateDansMois.plusDays(1), c2);
        VoyageNational v2b = creerVoyage(v2, trajetNational, dateDansMois.plusDays(2), c2);
        v2.getTransportsEffectues().add(v2a);
        v2.getTransportsEffectues().add(v2b);

        agence.getVehiculeAssigneTrajet().put(v2, trajetNational);

        Chauffeur meilleur = stats.chauffeurNationalPlusActifSurUnTrajet(agence, trajetNational);

        assertEquals(c2, meilleur);
    }

    @Test
    void chauffeurNationalPlusActifSurUnTrajet_aucunTrajet() {
        Chauffeur meilleur = stats.chauffeurNationalPlusActifSurUnTrajet(agence, trajetNational);

        assertNull(meilleur);
    }

    // ─── chauffeurNationalPlusActif ──────────────────

    @Test
    void chauffeurNationalPlusActif_plusDeKm() {
        Vehicule v2 = new Vehicule("006", 10.0, Vehicule.TypeService.ECO, Vehicule.TypeVehicule.BUS, Vehicule.Usage.VOYAGE);
        v2.setAgence(agence);
        Chauffeur c2 = new Chauffeur(UUID.randomUUID(), Chauffeur.Permis.D, "Long", "Test", "l@t.com", "034", "pass", "P007", v2);
        agence.getChauffeurs().add(c2);

        VoyageNational court = creerVoyage(vAgencyBus, trajetNational, dateDansMois, chauffeur2);
        vAgencyBus.getTransportsEffectues().add(court);

        Trajet trajetLong = new Trajet(UUID.randomUUID(), arretTana, arretFiana, 1000, 20000,
                new ArrayList<>(List.of(arretTana, arretFiana)), new ArrayList<>(List.of(1000)));
        VoyageNational longV = creerVoyage(v2, trajetLong, dateDansMois, c2);
        v2.getTransportsEffectues().add(longV);

        agence.getVehiculeAssigneTrajet().put(v2, trajetLong);
        agence.getTrajets().add(trajetLong);

        Chauffeur meilleur = stats.chauffeurNationalPlusActif(agence);

        assertEquals(c2, meilleur);
    }

    @Test
    void chauffeurNationalPlusActif_aucunChauffeur() {
        agence.getChauffeurs().clear();

        Chauffeur meilleur = stats.chauffeurNationalPlusActif(agence);

        assertNull(meilleur);
    }
}
