package TransportTerrestre;


import lombok.Data;

@Data


public class Place {
    enum Rangee { 
        devant, premier, deuxieme, troisieme, quatre 
    }
    enum Colonne { 
        fenetreG, fenetreD, CouloirG, CouloirD, milieu
    }
    
    private int numero;
    
    private Rangee rangee;
    private Colonne colonne;
    private boolean disponibilite;

    public Place(int numero, Rangee rangee, Colonne colonne) {
        this.numero = numero;
        this.rangee = rangee;
        this.colonne = colonne;
        this.disponibilite = true;
    }
}

