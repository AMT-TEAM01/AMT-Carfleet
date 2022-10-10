import Serialization.CarCustomDeserialize;
import Serialization.DriverCustomDeserialize;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeSerialisationTest {
    private ObjectMapper mapper;

    @Before
    public void setup() {
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
}
