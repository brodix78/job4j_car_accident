package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> accidents = new HashMap<>(Map.of(
            1, new Accident(1, "First", "Small accident", "village"),
            2, new Accident(2, "Second", "Medium accident", "route"),
            3, new Accident(3, "Third", "Funny accident", "city")
    ));

    public HashMap<Integer, Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(HashMap<Integer, Accident> accidents) {
        this.accidents = accidents;
    }

    public void addAccident(Accident accident) {
        int id = accidents.keySet().stream().max(Integer::compare).get() + 1;
        accident.setId(id);
        accidents.put(id, accident);
    }

    public void updateAccident(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }
}
