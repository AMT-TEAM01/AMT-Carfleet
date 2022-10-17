package Serialization;

import Structure.Attribut;
import Structure.Plate;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Exception.*;

public class EntityCustomDeserialize extends StdDeserializer {
    JsonNode node, boards;
    List<Plate> plates = new ArrayList<>();
    List<Attribut> attributs = new ArrayList<>();

    public EntityCustomDeserialize(Class vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        node = jsonParser.getCodec().readTree(jsonParser);

        if (node.isEmpty()) {
            throw new EmptyJsonException();
        }

        boards = node.get("data").get("boards");

        if (boards == null) {
            throw new MalformedJsonException();
        }

        return null;
    }

    public void checkIdName(List<Plate> plates, JsonNode item) {
        if (!item.has("id") || !item.has("name")) {
            throw new MissingAttributJsonException();
        }

        if (!item.get("id").isTextual() || !item.get("name").isTextual()) {
            throw  new AttributNotCorrectJsonException();
        }

        plates.add(new Plate(item.get("id").asInt(), item.get("name").asText()));
    }
}
