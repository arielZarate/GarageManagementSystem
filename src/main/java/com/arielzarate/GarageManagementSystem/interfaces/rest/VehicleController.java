package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.model.Vehicle;
import com.arielzarate.GarageManagementSystem.domain.model.enums.FuelType;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.domain.ports.in.VehicleService;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.vehicle.VehicleRequest;
import com.arielzarate.GarageManagementSystem.interfaces.rest.mappers.VehicleDTOMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleDTOMapper mapper;

    @GetMapping
    public String getVehicles(@RequestParam(value = "q", required = false) String query,
                              @RequestParam(value = "type", required = false) VehicleType type,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "20") int size,
                              Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Vehicle> vehiclePage = vehicleService.getVehicles(query, type, pageRequest);
        model.addAttribute("pageTitle", "Vehículos");
        model.addAttribute("content", "vehicle/list");
        model.addAttribute("vehicles", vehiclePage.stream().map(mapper::toResponse).toList());
        model.addAttribute("currentPage", vehiclePage.getNumber());
        model.addAttribute("totalPages", vehiclePage.getTotalPages());
        model.addAttribute("vehicleTypes", VehicleType.values());
        model.addAttribute("selectedType", type);
        model.addAttribute("searchQuery", query);
        return "fragments/base";
    }

    @GetMapping("/detail/{id}")
    public String getVehicleDetail(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "Detalle del Vehículo");
        model.addAttribute("content", "vehicle/detail");
        model.addAttribute("vehicle", mapper.toResponse(vehicleService.getVehicleById(id)));
        return "fragments/base";
    }

    @GetMapping("/form")
    public String newVehicleForm(Model model) {
        model.addAttribute("pageTitle", "Nuevo Vehículo");
        model.addAttribute("content", "vehicle/form");
        model.addAttribute("vehicle", new VehicleRequest());
        model.addAttribute("vehicleTypes", VehicleType.values());
        model.addAttribute("fuelTypes", FuelType.values());
        return "fragments/base";
    }

    /***
     *
     * Se debe limpiar estoo porque esta sobrecargado
     * */
    @GetMapping("/edit/{id}")
    public String editVehicleForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        VehicleRequest request = new VehicleRequest();
        request.setId(vehicle.getId());
        request.setLicensePlate(vehicle.getLicensePlate());
        
        Long brandId = null;
        Long modelId = null;
        
        if (vehicle.getBrand() != null) {
            brandId = vehicle.getBrand().getId();
            request.setBrandId(brandId);
        }
        if (vehicle.getModel() != null) {
            modelId = vehicle.getModel().getId();
            request.setModelId(modelId);
        }
        if (vehicle.getVersion() != null) {
            request.setVersionId(vehicle.getVersion().getId());
        }
        
        request.setYear(vehicle.getYear());
        if (vehicle.getModel() != null) {
            request.setVehicleType(vehicle.getModel().getVehicleType());
        }
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
        model.addAttribute("fuelTypes", FuelType.values());
        return "fragments/base";
    }

    @PostMapping
    public String createVehicle(@ModelAttribute VehicleRequest request,
                                RedirectAttributes redirectAttributes) {
        vehicleService.addVehicle(mapper.toDomain(request));
        log.info("Vehicle created with licence plate: {}", request.getLicensePlate());
        redirectAttributes.addFlashAttribute("successMsg", "Vehículo creado exitosamente");
        return "redirect:/vehicle";
    }

    @PostMapping("/update")
    public String updateVehicle(@ModelAttribute VehicleRequest request,
                                RedirectAttributes redirectAttributes) {
        vehicleService.updateVehicle(mapper.toDomain(request));
        log.info("Vehicle updated: {}", request.getId());
        redirectAttributes.addFlashAttribute("successMsg", "Vehículo actualizado exitosamente");
        return "redirect:/vehicle";
    }

    @PostMapping("/{id}/delete")
    public String deleteVehicle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        vehicleService.deleteVehicle(id);
        log.info("Vehicle deleted: {}", id);
        redirectAttributes.addFlashAttribute("infoMsg", "Vehículo eliminado exitosamente");
        return "redirect:/vehicle";
    }
}
