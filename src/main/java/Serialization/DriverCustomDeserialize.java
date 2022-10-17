package Serialization;

import Structure.Attribut;
import Structure.Driver;
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

public class DriverCustomDeserialize extends EntityCustomDeserialize {

    public DriverCustomDeserialize() {
        this(null);
    }

    public DriverCustomDeserialize(Class vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        super.deserialize(jsonParser, deserializationContext);

        String name = null;
        int id = 0;

        for (JsonNode board : boards) {
            JsonNode items = board.get("items");

            if (items == null) {
                throw new MalformedJsonException();
            }

            for (JsonNode item : items) {
                super.checkIdName(plates, item);

                JsonNode subitems = item.get("subitems");

                if (subitems == null) {
                    throw new MalformedJsonException();
                }

                for (JsonNode subs : subitems) {
                    if (!subs.has("id") || !subs.has("name")) {
                        throw new MissingAttributJsonException();
                    }

                    if (!subs.get("id").isTextual() || !subs.get("name").isTextual()) {
                        throw  new AttributNotCorrectJsonException();
                    }

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
