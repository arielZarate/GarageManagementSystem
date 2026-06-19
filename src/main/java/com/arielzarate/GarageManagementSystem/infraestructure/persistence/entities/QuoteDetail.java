package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "quote_detail")
public class QuoteDetail extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;

    private String description;

    private String partName;

    private BigDecimal partPrice;

    private Integer quantity;

    private BigDecimal laborPrice;

    private BigDecimal subtotal;

}
