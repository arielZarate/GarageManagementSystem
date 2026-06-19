package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class BaseEntity {

    @Id
    private Long id;
}
