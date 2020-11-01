package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;

@Controller
public class AccidentController {

    private final AccidentHibernate accidents;

    public AccidentController(AccidentHibernate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidents.addAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/change")
    public String change(@RequestParam String id, Model model) {
        int i = Integer.parseInt(id);
        model.addAttribute("accident", accidents.getAccidentById(i));
        return "accident/change";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        accidents.updateAccident(accident);
        return "redirect:/";
    }
}
