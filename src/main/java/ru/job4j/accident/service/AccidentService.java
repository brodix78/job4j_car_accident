package ru.job4j.accident.service;

import org.springframework.stereotype.Controller;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentService {
    AccidentMem mem =  new AccidentMem();

    public List<Accident> allAccidents() {
        return new ArrayList<>(mem.getAccidents().values());
    }
}
