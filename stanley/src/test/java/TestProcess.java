import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestProcess { 
    Admin Chef ; 
    Agence KOFIMANGA ; 
    Client KOLOINA ; 
    Client RAZAO ; 
    Client JAONA ; 
    Client George ;
    Ticket ticket1 ;
    Ticket ticket2 ;
    Ticket ticket3 ;
    Ticket ticket4 ;
    Trajet trajetVoyage1 ;
    Trajet trajetVoyage2 ;
    Trajet course1 ;
    Trajet course2 ;
    Bagages bagages1 ;
    Bagages bagages2 ;
    Bagages bagages3 ;
    Bagages bagages4 ;
    Voiture Voiture1 ; 
    Voiture Voiture2 ; 
    Voiture Voiture3 ;
    Voiture Voiture4 ;
    Voiture Voiture5 ;
    Voiture VoitureHS;


    @BeforeEach 
    void setup (){ 
        Chef = new Admin(1, "Bob", "Marley");
        Voiture1 = new Voiture(0, 4, 320, "034 40 326 13", enumType.course, typeVehicule.Berline);
        Voiture2 = new Voiture(1, 4, 440 , "034 40 326 13", enumType.voyage, typeVehicule.RAV4);
        Voiture3 = new Voiture(2, 2, 140, "032 60 670 17", enumType.course, typeVehicule.Moto);
        Voiture4 = new Voiture(3, 20, 3800, "032 60 670 17", enumType.course, typeVehicule.Bus);
        Voiture5 = new Voiture(4, 20, 3800, "032 60 670 17", enumType.voyage, typeVehicule.Bus);
        Voiture5 = new Voiture(5, 2, 140, "032 60 670 17", enumType.voyage, typeVehicule.Moto);
         trajetVoyage1 = new Trajet(1, "Antananarivo", "Toamasina", 372.0);
         trajetVoyage2 = new Trajet(2, "Antananarivo", "Fianarantsoa", 405.0);
        course1 = new Trajet(3, "Ankadifotsy", "Ankatso", 3.5);
        course2 = new Trajet(4, "Ankadifotsy", "Ivandry", 7.2);
         bagages1 = new Bagages(1, 15.0);  // valise cabine
         bagages2 = new Bagages(2, 23.0);  // valise soute
         bagages3 = new Bagages(3, 8.5);   // sac à dos
         bagages4 = new Bagages(4, 35.0);
         KOLOINA = new Client(1, "...", "Koloina"); 
         George = new Client(2, "...", "George"); 
         JAONA = new Client(3, "...", "Jaona"); 
         RAZAO = new Client(4, "...", "Razao");

    }   
    @Test 
    void PrendreDesbagages (){ 
        KOLOINA.ajoutBagages(bagages1);
        KOLOINA.ajoutBagages(bagages2);
        assertEquals(2, KOLOINA.LesBagages.size());
    }
    
    
}