package com.arielzarate.GarageManagementSystem.interfaces.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Pagina Principal");
        /*** load principal page*/
        model.addAttribute("content", "index");
        return "fragments/base";
    }
}
