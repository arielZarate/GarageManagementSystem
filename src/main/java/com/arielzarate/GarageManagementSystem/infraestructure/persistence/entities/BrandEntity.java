package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "brand")
public class BrandEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;
}
