package Models;

import java.util.ArrayList;
import java.util.List;

public class ZooManager {
    private final List<Enclosure> enclosures;

    public ZooManager() {
        enclosures = new ArrayList<>();
    }

    public void addEnclosure(Enclosure enclosure) {
        enclosures.add(enclosure);
    }

    public void listAllEnclosures() {
        for (Enclosure enclosure : enclosures) {
            System.out.println(enclosure.getId() + "- Enclos " + enclosure.getName());
        }
    }

    public List<Enclosure> getEnclosures(){
        return enclosures;
    }

    public Enclosure getEnclosureById(int id) {
        return enclosures.get(id);
    }
}