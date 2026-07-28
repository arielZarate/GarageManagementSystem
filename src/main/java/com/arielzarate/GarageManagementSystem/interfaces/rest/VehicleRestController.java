package com.arielzarate.GarageManagementSystem.interfaces.rest;


import com.arielzarate.GarageManagementSystem.domain.model.Model;
import com.arielzarate.GarageManagementSystem.domain.model.Version;
import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.domain.ports.in.BrandService;
import com.arielzarate.GarageManagementSystem.domain.ports.in.ModelService;
import com.arielzarate.GarageManagementSystem.domain.ports.in.VersionService;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.brand.BrandResponse;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.model.ModelResponse;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.version.VersionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/api/vehicle")
@AllArgsConstructor
public class VehicleRestController {
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

    // ──────────────────────────────────────────────────────────────
    // Quick-create endpoints (inline creation from vehicle form)
    // ──────────────────────────────────────────────────────────────

    @PostMapping("/brand/quick")
    public BrandResponse quickCreateBrand(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        log.info("se agrego marca " +  name);
        return new BrandResponse(brandService.addBrand(name).getId(), name);
    }

    @PostMapping("/model/quick")
    public ModelResponse quickCreateModel(@RequestBody Map<String, Object> body) {
        String name = (String) body.get("name");
        Long brandId = ((Number) body.get("brandId")).longValue();
        VehicleType type = VehicleType.valueOf((String) body.get("vehicleType"));
        log.info("se agrego model = {} , brandId ={} tipo ,  type={}" ,  name,brandId, type);

        Model model = modelService.addModel(name, brandId, type);
        return new ModelResponse(model.getId(), model.getName());
    }

    @PostMapping("/version/quick")
    public VersionResponse quickCreateVersion(@RequestBody Map<String, Object> body) {
        String name = (String) body.get("name");
        Long modelId = ((Number) body.get("modelId")).longValue();
        Version version = versionService.addVersion(name, modelId);
        return new VersionResponse(version.getId(), version.getName());
    }

}
