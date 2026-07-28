package com.arielzarate.GarageManagementSystem.interfaces.rest;


import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.domain.ports.in.BrandService;
import com.arielzarate.GarageManagementSystem.domain.ports.in.ModelService;
import com.arielzarate.GarageManagementSystem.domain.ports.in.VersionService;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.brand.BrandResponse;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.model.ModelResponse;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.version.VersionResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@AllArgsConstructor
public class VehicleLookupController {
    private final BrandService brandService;
    private final ModelService modelService;
    private final VersionService versionService;

    @GetMapping("/brands")
    public List<BrandResponse> getBrands(@RequestParam VehicleType type) {
        return brandService.getBrandsByVehicleType(type).stream()
                .map(b -> new BrandResponse(b.getId(), b.getName()))
                .toList();
    }

    @GetMapping("/models")
    public List<ModelResponse> getModels(
            @RequestParam Long brandId,
            @RequestParam VehicleType type) {

        return modelService.getModels(brandId, type)
                .stream()
                .map(m -> new ModelResponse(
                        m.getId(),
                        m.getName()))
                .toList();
    }


    @GetMapping("/versions")
    public List<VersionResponse> getVersions(
            @RequestParam Long modelId) {

        return versionService
                .getVersionsByModels(List.of(modelId))
                .getOrDefault(modelId, List.of())
                .stream()
                .map(v -> new VersionResponse(
                        v.getId(),
                        v.getName()))
                .toList();
    }


}
