public class Place { 
    int id ; 
    enum Ranger { 
        devant , first , deuxieme , troisieme , quatre 
    }
    enum Colonne { 
        fenetreG , fenetreD , CouloirG , CouloirD , Strapotin ,milieu
    }
    Ranger ranger ; 
    Colonne colonne ;
    boolean disponibilite ; 
    public Place(int id, Ranger ranger, Colonne colonne) {
        this.id = id;
        this.ranger = ranger;
        this.colonne = colonne;
        this.disponibilite = true ;
    }
    public Place(int id) {
        this.id = id;
    } 
    
    
}
