package com.arielzarate.GarageManagementSystem.domain.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Brand {
    private Long id;
    private String name;  // Honda, Ford, Toyota...
}
