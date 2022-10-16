package Serialization;

import Structure.Attribut;
import Structure.Driver;
import Structure.Plate;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DriverCustomDeserialize extends StdDeserializer {

    public DriverCustomDeserialize() {
        this(null);
    }

    public DriverCustomDeserialize(Class vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode boards = node.get("data").get("boards");

        List<Plate> plates = new ArrayList<>();
        List<Attribut> attributs = new ArrayList<>();
        String name = null;
        int id = 0;

        for (JsonNode board : boards) {
            JsonNode items = board.get("items");

            for (JsonNode item : items) {
                plates.add(new Plate(item.get("id").asInt(), item.get("name").asText()));


                for (JsonNode subs : item.get("subitems")) {
                    name = subs.get("name").asText();
                    id = subs.get("id").asInt();
                    
                    for (JsonNode attribut : subs.get("column_values")) {
                        attributs.add(new Attribut(attribut.get("title").asText(), attribut.get("text").asText()));
                    }
                }
            }

        }

        return new Driver(attributs, plates, id, name);
    }
}
