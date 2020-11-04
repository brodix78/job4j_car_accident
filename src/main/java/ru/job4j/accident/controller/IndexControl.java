package ru.job4j.accident.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    
    private final AccidentRepository accidents;

    public IndexControl(AccidentRepository accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> rsl = new ArrayList<>();
        accidents.findAll().forEach(rsl::add);
        model.addAttribute("accidents", rsl);
        model.addAttribute("user", SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
        return "index";
    }
}
