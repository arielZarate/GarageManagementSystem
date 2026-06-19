package com.arielzarate.GarageManagementSystem.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepairDetail {

    private Long id;
    private Long repairOrderId;
    private String description;
    private String partName;
    private BigDecimal partPrice;
    private Integer quantity;
    private BigDecimal laborPrice;
    private BigDecimal subtotal;
    private List<String> photos;
}
