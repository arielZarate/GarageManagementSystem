package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.model.Brand;
import com.arielzarate.GarageManagementSystem.domain.ports.in.BrandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/brand")
public class BrandController {

    private final BrandService service;

    @GetMapping
    public String getBrands(@RequestParam(value = "q", required = false) String query, Model model) {
        model.addAttribute("pageTitle", "Marcas");
        model.addAttribute("content", "brand/list");
        model.addAttribute("brands", service.getBrands(query));
        model.addAttribute("searchQuery", query);
        return "fragments/base";
    }

    @PostMapping
    public String createBrand(@RequestParam("name") String name) {
        service.addBrand(name);
        log.info("Brand created: {}", name);
        return "redirect:/brand";
    }

    @PostMapping("/update")
    public String updateBrand(@RequestParam("id") Long id,
                               @RequestParam("name") String name) {
        service.updateBrand(id, name);
        log.info("Brand updated: {} -> {}", id, name);
        return "redirect:/brand";
    }

    @PostMapping("/{id}/delete")
    public String deleteBrand(@PathVariable Long id) {
        service.deleteBrand(id);
        log.info("Brand deleted: {}", id);
        return "redirect:/brand";
    }
}
