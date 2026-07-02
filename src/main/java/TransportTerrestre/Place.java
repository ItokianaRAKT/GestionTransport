package TransportTerrestre;


import lombok.Data;

@Data


public class Place {
    enum Ranger { 
        devant, first, deuxieme, troisieme, quatre 
    }
    enum Colonne { 
        fenetreG, fenetreD, CouloirG, CouloirD, milieu
    }
    
    private int numero;
    private Vehicule vehicule;
    private Ranger ranger;
    private Colonne colonne;
    private boolean disponibilite;

    public Place(int numero, Ranger ranger, Colonne colonne) {
        this.numero = numero;
        this.ranger = ranger;
        this.colonne = colonne;
        this.disponibilite = true;
    }

    public Place(int numero) {
        this.numero = numero;
    }
}

