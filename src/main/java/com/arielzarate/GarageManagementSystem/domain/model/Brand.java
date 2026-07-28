package com.arielzarate.GarageManagementSystem.domain.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Brand {
    private Long id;
    private String name;  // Honda, Ford, Toyota...
}
