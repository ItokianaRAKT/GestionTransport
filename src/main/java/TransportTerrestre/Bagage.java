package TransportTerrestre;

import lombok.Data;

import java.util.UUID;

@Data
public class Bagage {

    public static final double PREMIER_PLAFOND = 10.0;
    public static final double SECOND_PLAFOND = 30.0;
    public static final int FRAIS_PAR_KG = 1000;

    private UUID id;
    private double poids;
    private String description;

    public Bagage(UUID id, double poids, String description) {
        this.id = id;
        this.poids = poids;
        this.description = description;
    }

    public boolean estEnSurpoids() {
        return poids > PREMIER_PLAFOND;
    }

    public boolean estInterdit() {
        return poids > SECOND_PLAFOND;
    }

    public double calculerFrais() {
        if (poids > SECOND_PLAFOND) {
            throw new IllegalStateException("Poids depasse le second plafond de " + SECOND_PLAFOND + "kg");
        }
        if (poids > PREMIER_PLAFOND) {
            return (poids - PREMIER_PLAFOND) * FRAIS_PAR_KG;
        }
        return 0;
    }

}
