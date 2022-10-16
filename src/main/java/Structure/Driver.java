package Structure;

import Serialization.DriverCustomDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = DriverCustomDeserialize.class)
public class Driver {
    private List<Attribut> attributs;
    private List<Plate> plates;
    private String id;
    private String name;

    public Driver(List<Attribut> attributs, List<Plate> plates, String id, String name) {
        this.attributs = attributs;
        this.plates = plates;
        this.id = id;
        this.name = name;
    }

    public List<Attribut> getAttributs() {
        return attributs;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}