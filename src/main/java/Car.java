import java.util.List;

public class Car {
    private List<Attribut> attributs;
    private List<Plate> plates;

    public Car(List<Attribut> attributs, List<Plate> plates) {
        this.attributs = attributs;
        this.plates = plates;
    }

    public List<Attribut> getAttributs() {
        return attributs;
    }

    public List<Plate> getPlates() {
        return plates;
    }
}
