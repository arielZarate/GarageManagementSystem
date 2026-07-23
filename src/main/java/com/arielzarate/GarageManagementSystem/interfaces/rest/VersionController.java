package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.ports.in.ModelService;
import com.arielzarate.GarageManagementSystem.domain.ports.in.VersionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/model/{modelId}/versions")
public class VersionController {

    private final VersionService versionService;
    private final ModelService modelService;

    @PostMapping
    public String createVersion(@PathVariable Long modelId,
                                @RequestParam("name") String name,
                                RedirectAttributes redirectAttributes) {
        versionService.addVersion(name, modelId);
        log.info("Version created: {} -> model {}", name, modelId);
        redirectAttributes.addFlashAttribute("successMsg", "Versión creada exitosamente");
        return redirectToModels(modelId);
    }

    @PostMapping("/update")
    public String updateVersion(@PathVariable Long modelId,
                                @RequestParam("id") Long id,
                                @RequestParam("name") String name,
                                RedirectAttributes redirectAttributes) {
        versionService.updateVersion(id, name);
        log.info("Version updated: {} -> {}", id, name);
        redirectAttributes.addFlashAttribute("successMsg", "Versión actualizada exitosamente");
        return redirectToModels(modelId);
    }

    @PostMapping("/{id}/delete")
    public String deleteVersion(@PathVariable Long modelId,
                                @PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        versionService.deleteVersion(id);
        log.info("Version deleted: {}", id);
        redirectAttributes.addFlashAttribute("infoMsg", "Versión eliminada exitosamente");
        return redirectToModels(modelId);
    }

    private String redirectToModels(Long modelId) {
        Long brandId = modelService.getModelById(modelId).getBrand().getId();
        return "redirect:/brand/" + brandId + "/models";
    }
}
