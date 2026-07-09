package TransportTerrestre;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode


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
    @EqualsAndHashCode.Exclude
    private boolean disponibilite;

    public Place(int numero, Rangee rangee, Colonne colonne) {
        this.numero = numero;
        this.rangee = rangee;
        this.colonne = colonne;
        this.disponibilite = true;
    }
}

