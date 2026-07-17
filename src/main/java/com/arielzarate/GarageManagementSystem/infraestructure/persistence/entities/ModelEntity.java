package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "model")
public class ModelEntity extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
}
