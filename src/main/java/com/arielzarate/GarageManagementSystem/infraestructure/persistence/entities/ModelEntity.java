package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "model", cascade = CascadeType.REMOVE)
    private List<VersionEntity> versions = new ArrayList<>();

}
