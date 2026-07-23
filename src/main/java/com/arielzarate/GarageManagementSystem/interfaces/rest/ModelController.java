package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.model.Model;
import com.arielzarate.GarageManagementSystem.domain.model.Version;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.domain.ports.in.BrandService;
import com.arielzarate.GarageManagementSystem.domain.ports.in.ModelService;
import com.arielzarate.GarageManagementSystem.domain.ports.in.VersionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/brand/{brandId}/models")
public class ModelController {

    private final ModelService service;
    private final BrandService brandService;
    private final VersionService versionService;

    @GetMapping
    public String getModels(@PathVariable Long brandId,
                            @RequestParam(value = "type", required = false) VehicleType vehicleType,
                           org.springframework.ui.Model model) {
        List<Model> models = service.getModels(brandId, vehicleType);
        model.addAttribute("pageTitle", "Modelos");
        model.addAttribute("content", "model/list");
        model.addAttribute("brand", brandService.getBrands(null).stream()
                .filter(b -> b.getId().equals(brandId))
                .findFirst()
                .orElse(null));
        model.addAttribute("models", models);
        model.addAttribute("selectedType", vehicleType);
        model.addAttribute("vehicleTypes", VehicleType.values());

        List<Long> modelIds = models.stream().map(Model::getId).toList();
        Map<Long, List<Version>> versionsByModel = modelIds.isEmpty()
                ? Map.of()
                : versionService.getVersionsByModels(modelIds);
        model.addAttribute("versionsByModel", versionsByModel);
        return "fragments/base";
    }

    @PostMapping
    public String createModel(@PathVariable Long brandId,
                              @RequestParam("name") String name,
                              @RequestParam(value = "vehicleType", required = false) VehicleType vehicleType,
                              RedirectAttributes redirectAttributes) {
        service.addModel(name, brandId, vehicleType);
        log.info("Model created: {} -> brand {}", name, brandId);
        redirectAttributes.addFlashAttribute("successMsg", "Modelo creado exitosamente");
        return "redirect:/brand/" + brandId + "/models";
    }

    @PostMapping("/update")
    public String updateModel(@PathVariable Long brandId,
                              @RequestParam("id") Long id,
                              @RequestParam("name") String name,
                              @RequestParam(value = "vehicleType", required = false) VehicleType vehicleType,
                              RedirectAttributes redirectAttributes) {
        service.updateModel(id, name, vehicleType);
        log.info("Model updated: {} -> {}", id, name);
        redirectAttributes.addFlashAttribute("successMsg", "Modelo actualizado exitosamente");
        return "redirect:/brand/" + brandId + "/models";
    }

    @PostMapping("/{id}/delete")
    public String deleteModel(@PathVariable Long brandId,
                              @PathVariable Long id,
                              RedirectAttributes redirectAttributes) {
        service.deleteModel(id);
        log.info("Model deleted: {}", id);
        redirectAttributes.addFlashAttribute("infoMsg", "Modelo eliminado exitosamente");
        return "redirect:/brand/" + brandId + "/models";
    }
}
