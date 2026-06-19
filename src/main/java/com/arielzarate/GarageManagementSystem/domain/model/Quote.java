package com.arielzarate.GarageManagementSystem.domain.model;

import com.arielzarate.GarageManagementSystem.domain.model.enums.QuoteStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    private Long id;
    private String quoteNumber;
    private LocalDate quoteDate;
    private Long vehicleId;
    private Long customerId;
    private QuoteStatus status;
    private BigDecimal total;
    private LocalDate validUntil;
}
