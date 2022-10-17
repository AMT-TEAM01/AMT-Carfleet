import Structure.Car;
import Structure.Fleet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FleetIntegration {
    private static ObjectMapper mapper;

    @BeforeAll
    public static void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    public void createFleetWith2CarIsSize2() throws IOException {
        ArrayList<Car> cars = new ArrayList<>(Arrays.asList(mapper.readValue(new File("src/test/data/dataCar.json"),
                Car.class), mapper.readValue(new File("src/test/data/dataCar.json"), Car.class)));

        Fleet fleet = new Fleet(cars);
        assertEquals(fleet.getCars().size(),2);
    }

    @Test
    public void createFleetWith2CarTestValue() throws IOException {
        ArrayList<Car> cars = new ArrayList<>(Arrays.asList(mapper.readValue(new File("src/test/data/dataCar.json"),
                Car.class), mapper.readValue(new File("src/test/data/dataCar.json"), Car.class)));

        Fleet fleet = new Fleet(cars);
        assertEquals(fleet.getCars().get(1).getAttributs().get(0).getValue(), "Volkswagen California");
    }
}
