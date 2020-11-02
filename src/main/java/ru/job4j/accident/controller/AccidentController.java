package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

@Controller
public class AccidentController {

    private final AccidentRepository accidents;

    public AccidentController(AccidentRepository accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/change")
    public String change(@RequestParam String id, Model model) {
        int i = Integer.parseInt(id);
        var accident = accidents.findById(i);
        if (accident.isPresent()) {
            model.addAttribute("accident", accident.get());
            return "accident/change";
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        accidents.save(accident);
        return "redirect:/";
    }
}