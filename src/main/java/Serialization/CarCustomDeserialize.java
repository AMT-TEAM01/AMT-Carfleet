package Serialization;

import Structure.Attribut;
import Structure.Car;
import Structure.Plate;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarCustomDeserialize extends StdDeserializer {
    public CarCustomDeserialize() {
        this(null);
    }

    public CarCustomDeserialize(Class vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode boards = node.get("data").get("boards");

        List<Plate> plates = new ArrayList<>();
        List<Attribut> attributs = new ArrayList<>();

        for (JsonNode board : boards) {
            JsonNode items = board.get("items");

            for (JsonNode item : items) {
                plates.add(new Plate(item.get("id").asInt(), item.get("name").asText()));


                for (JsonNode attribut : item.get("column_values")) {
                    attributs.add(new Attribut(attribut.get("title").asText(), attribut.get("text").asText()));
                }
            }

        }

        return new Car(attributs, plates);
    }
}
