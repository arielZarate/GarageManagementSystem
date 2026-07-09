package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import com.arielzarate.GarageManagementSystem.domain.model.enums.QuoteStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "quote")
public class Quote extends BaseEntity {


    @Column(unique = true, nullable = false)
    private String quoteNumber;

    private LocalDate quoteDate;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;


    @Enumerated(EnumType.STRING)
    private QuoteStatus status;

    private BigDecimal total;

    private LocalDate validUntil;

}
