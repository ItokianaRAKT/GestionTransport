package com.transport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsateurTest {

 private Chauffeur chauffeurTest;
 private UUID idMystere;

 @BeforeEach
 public void setUp() {
     idMystere = UUID.randomUUID();
      chauffeurTest = new Chauffeur(
                idMystere,
                 "Aubin",
                "Jean",
                "secret123",
                 "0601020304",
                 12345,
                true,
 new Agence(),
 new Vehicule()
 );
 }

 @Test
public void testChangerDisponibiliteChauffeur() {
 assertTrue(chauffeurTest.isDisponible());
 chauffeurTest.changerDisponibilite();
 assertFalse(chauffeurTest.isDisponible(), "La disponibilité aurait dû basculer à false.");
 }

 @Test
 public void testSeConnecter() {

 boolean connecte = chauffeurTest.seConnecter();
 assertTrue(connecte, "La méthode seConnecter devrait renvoyer true.");
}

 @Test
 public void testGettersUML() {
 assertEquals("Aubin", chauffeurTest.getNom());
 assertEquals("Jean", chauffeurTest.getPrenom());
 assertEquals(idMystere, chauffeurTest.getId());
}
}
