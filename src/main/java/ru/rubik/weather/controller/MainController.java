/*package ru.rubik.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rubik.weather.DAO.PersonDAO;

@Controller
public class MainController {
    @Autowired
    private PersonDAO personDAO;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("persons", personDAO.getPersons());
        return "index";
    }
}*/
