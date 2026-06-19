package com.arielzarate.GarageManagementSystem.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuoteDetail {

    private Long id;
    private Long quoteId;
    private String description;
    private String partName;
    private BigDecimal partPrice;
    private Integer quantity;
    private BigDecimal laborPrice;
    private BigDecimal subtotal;
}
