package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.model.Vehicle;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.domain.ports.in.VehicleService;
import com.arielzarate.GarageManagementSystem.interfaces.rest.mappers.VehicleDTOMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService service;
    private final VehicleDTOMapper mapper;

    @GetMapping
    public String getVehicles(@RequestParam(value = "q", required = false) String query,
                              @RequestParam(value = "type", required = false) VehicleType type,
                              Model model) {
        model.addAttribute("pageTitle", "Vehículos");
        model.addAttribute("content", "vehicle/list");
        model.addAttribute("vehicles", service.getVehicles(query, type).stream()
                .map(mapper::toResponse)
                .toList());
        model.addAttribute("vehicleTypes", VehicleType.values());
        model.addAttribute("selectedType", type);
        model.addAttribute("searchQuery", query);
        return "fragments/base";
    }

    @GetMapping("/detail/{id}")
    public String getVehicleDetail(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Detalle del Vehículo");
        model.addAttribute("content", "vehicle/detail");
        model.addAttribute("vehicle", mapper.toResponse(service.getVehicleById(id)));
        return "fragments/base";
    }

    @GetMapping("/form")
    public String newVehicleForm(Model model) {
        model.addAttribute("pageTitle", "Nuevo Vehículo");
        model.addAttribute("content", "vehicle/form");
        model.addAttribute("vehicle", new com.arielzarate.GarageManagementSystem.interfaces.rest.dto.vehicle.VehicleRequest());
        model.addAttribute("vehicleTypes", VehicleType.values());
        model.addAttribute("fuelTypes", com.arielzarate.GarageManagementSystem.domain.model.enums.FuelType.values());
        return "fragments/base";
    }

    @GetMapping("/edit/{id}")
    public String editVehicleForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = service.getVehicleById(id);
        com.arielzarate.GarageManagementSystem.interfaces.rest.dto.vehicle.VehicleRequest request = new com.arielzarate.GarageManagementSystem.interfaces.rest.dto.vehicle.VehicleRequest();
        request.setId(vehicle.getId());
        request.setLicensePlate(vehicle.getLicensePlate());
        if (vehicle.getBrandName() != null) request.setBrandId(vehicle.getBrandName().getId());
        if (vehicle.getModelName() != null) request.setModelId(vehicle.getModelName().getId());
        if (vehicle.getVersionName() != null) request.setVersionId(vehicle.getVersionName().getId());
        request.setYear(vehicle.getYear());
        request.setVehicleType(vehicle.getVehicleType());
        request.setColor(vehicle.getColor());
        request.setFuelType(vehicle.getFuelType());
        request.setKilometers(vehicle.getKilometers());
        request.setChassisNumber(vehicle.getChassisNumber());
        request.setEngineNumber(vehicle.getEngineNumber());
        request.setCustomerId(vehicle.getCustomerId());

        model.addAttribute("pageTitle", "Editar Vehículo");
        model.addAttribute("content", "vehicle/form");
        model.addAttribute("vehicle", request);
        model.addAttribute("vehicleTypes", VehicleType.values());
        model.addAttribute("fuelTypes", com.arielzarate.GarageManagementSystem.domain.model.enums.FuelType.values());
        return "fragments/base";
    }

    @PostMapping
    public String createVehicle(@ModelAttribute com.arielzarate.GarageManagementSystem.interfaces.rest.dto.vehicle.VehicleRequest request,
                                RedirectAttributes redirectAttributes) {
        service.addVehicle(mapper.toDomain(request));
        log.info("Vehicle created: {}", request.getLicensePlate());
        redirectAttributes.addFlashAttribute("successMsg", "Vehículo creado exitosamente");
        return "redirect:/vehicle";
    }

    @PostMapping("/update")
    public String updateVehicle(@ModelAttribute com.arielzarate.GarageManagementSystem.interfaces.rest.dto.vehicle.VehicleRequest request,
                                RedirectAttributes redirectAttributes) {
        service.updateVehicle(mapper.toDomain(request));
        log.info("Vehicle updated: {}", request.getId());
        redirectAttributes.addFlashAttribute("successMsg", "Vehículo actualizado exitosamente");
        return "redirect:/vehicle";
    }

    @PostMapping("/{id}/delete")
    public String deleteVehicle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.deleteVehicle(id);
        log.info("Vehicle deleted: {}", id);
        redirectAttributes.addFlashAttribute("infoMsg", "Vehículo eliminado exitosamente");
        return "redirect:/vehicle";
    }
}
