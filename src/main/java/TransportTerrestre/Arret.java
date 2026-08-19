package TransportTerrestre;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arret arret = (Arret) o;
        return Objects.equals(id, arret.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
