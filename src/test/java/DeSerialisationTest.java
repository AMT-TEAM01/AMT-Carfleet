import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeSerialisationTest {
    @Test
    public void car_deserialize_Success() throws IOException {
        Car car = new ObjectMapper().readValue(new File("data/dataCar.json"), Car.class);

        assertEquals(car.getAttributs().get(0).getValue(), "Volkswagen California");
    }

    @Test
    public void driver_deserialize_Success() throws IOException {
        Driver driver = new ObjectMapper().readValue(new File("data/dataDriver.json"), Driver.class);

        assertEquals(driver.getAttributs().get(1).getValue(), "41276519164");
    }
}
