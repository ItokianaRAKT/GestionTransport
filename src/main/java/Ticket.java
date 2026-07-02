import java.time.LocalDate;

public class Ticket {
    Client client; 
    Place siege ; 
    Reservation.Payement payement ;
    double Prix ; 
    LocalDate jourPayement ;
    public Ticket(Client client, Place siege, Reservation.Payement payement, double prix) {
        this.client = client;
        this.siege = siege;
        this.payement = payement;
        Prix = prix;
        this.jourPayement = LocalDate.now() ;
    }
    
}
