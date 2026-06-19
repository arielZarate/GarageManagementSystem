package com.arielzarate.GarageManagementSystem.domain.model;

import com.arielzarate.GarageManagementSystem.domain.model.enums.RepairStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepairOrder {

    private Long id;
    private String orderNumber;
    private Long vehicleId;
    private Long customerId;
    private Long employeeId;
    private RepairStatus status;
    private String diagnosis;
    private LocalDateTime admissionDate;
    private LocalDateTime estimatedFinish;
    private BigDecimal totalCost;
    private String notes;
}
