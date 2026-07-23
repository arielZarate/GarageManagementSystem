package com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories;

import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

    List<ModelEntity> findByBrandId(Long brandId);

    List<ModelEntity> findByVehicleType(VehicleType vehicleType);

    List<ModelEntity> findByBrandIdAndVehicleType(Long brandId, VehicleType vehicleType);

    long countByBrandId(Long brandId);

    @Query("SELECT m.brand.id, COUNT(m) FROM ModelEntity m GROUP BY m.brand.id")
    List<Object[]> countModelsGroupedByBrand();
}
