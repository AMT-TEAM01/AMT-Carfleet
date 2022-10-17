package Serialization;

import Structure.Attribut;
import Structure.Car;
import Structure.Plate;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import Exception.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarCustomDeserialize extends EntityCustomDeserialize {
    public CarCustomDeserialize() {
        this(null);
    }

    public CarCustomDeserialize(Class vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        super.deserialize(jsonParser, deserializationContext);

        for (JsonNode board : boards) {
            JsonNode items = board.get("items");

            if (items == null) {
                throw new MalformedJsonException();
            }

            for (JsonNode item : items) {
                super.checkIdName(plates, item);

                for (JsonNode attribut : item.get("column_values")) {
                    attributs.add(new Attribut(attribut.get("title").asText(), attribut.get("text").asText()));
                }
            }

        }

        return new Car(attributs, plates);
    }
}
