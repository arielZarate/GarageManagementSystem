package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "repair_detail")
public class RepairDetail extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "repair_order_id")
    private RepairOrder repairOrder;

    private String description;

    private String partName;

    private BigDecimal partPrice;

    private Integer quantity;

    private BigDecimal laborPrice;

    private BigDecimal subtotal;

    @ElementCollection
    @CollectionTable(name = "repair_detail_photos", joinColumns = @JoinColumn(name = "repair_detail_id"))
    @Column(name = "photo_path")
    private List<String> photos = new ArrayList<>();

}


/**
 * @see
 *
 * Subtotal= laborPrice + (partPrice*quantity)
 * **/