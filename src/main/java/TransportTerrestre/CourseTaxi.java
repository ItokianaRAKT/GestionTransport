package TransportTerrestre;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CourseTaxi extends Transport {
    enum TypeCourse {
        IMMEDIATE, PROGRAMMEE
    }

    private Client client;
    private TypeCourse typeCourse;
}
