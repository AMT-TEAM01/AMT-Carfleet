import Structure.Car;
import Structure.Driver;
import Exception.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeSerialisationTest {
    private static ObjectMapper mapper;

    @BeforeAll
    public static void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    public void car_deserialize_Success() throws IOException {
        Car car = mapper.readValue(new File("data/dataCar.json"), Car.class);

        assertEquals(car.getAttributs().get(0).getValue(), "Volkswagen California");
    }

    @Test
    public void driver_deserialize_Success() throws IOException {
        Driver driver = mapper.readValue(new File("data/dataDriver.json"), Driver.class);

        assertEquals(driver.getAttributs().get(1).getValue(), "41276519164");
    }

    @Test
    public void car_json_empty_ThrowsException() throws IOException {
        assertThrows(EmptyJsonException.class, () -> {
           mapper.readValue(new File("data/empty.json"), Car.class);
        });
    }

    @Test
    public void driver_json_malformed_ThrowsException() throws IOException {
        assertThrows(MalformedJsonException.class, () -> {
            mapper.readValue(new File("data/malformed.json"), Driver.class);
        });
    }

    @Test
    public void car_missing_attribut_json_ThrowsException() throws IOException {
        assertThrows(MissingAttributJsonException.class, () -> {
            mapper.readValue(new File("data/carMissingAttribut.json"), Car.class);
        });
    }

    @Test
    public void driver_attribut_not_correct_json_ThrowsException() throws IOException {
        assertThrows(AttributNotCorrectJsonException.class, () -> {
            mapper.readValue(new File("data/driverAttributNotCorrect.json"), Driver.class);
        });
    }
}
