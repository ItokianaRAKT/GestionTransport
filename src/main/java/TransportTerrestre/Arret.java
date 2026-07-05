package TransportTerrestre;

import java.util.UUID;

public class Arret {
    private UUID id;
    private String ville;
    
    public Arret(UUID id, String ville) {
        this.id = id;
        this.ville = ville;
    }
    public UUID getId() {
        return id;
    }
    public String getVille() {
        return ville;
    }
    
}
