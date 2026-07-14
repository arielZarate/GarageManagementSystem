package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "version")
public class VersionEntity extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private ModelEntity model;
}
