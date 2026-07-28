package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.ports.in.BrandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createBrand(@RequestParam("name") String name, RedirectAttributes redirectAttributes) {
        service.addBrand(name);
        log.info("Brand created: {}", name);
        redirectAttributes.addFlashAttribute("successMsg", "Marca creada exitosamente");
        return "redirect:/brand";
    }

    @PostMapping("/update")
    public String updateBrand(@RequestParam("id") Long id,
                               @RequestParam("name") String name,
                               RedirectAttributes redirectAttributes) {
        service.updateBrand(id, name);
        log.info("Brand updated: {} -> {}", id, name);
        redirectAttributes.addFlashAttribute("successMsg", "Marca actualizada exitosamente");
        return "redirect:/brand";
    }

    @PostMapping("/{id}/delete")
    public String deleteBrand(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.deleteBrand(id);
        log.info("Brand deleted: {}", id);
        redirectAttributes.addFlashAttribute("infoMsg", "Marca eliminada exitosamente");
        return "redirect:/brand";
    }
}
