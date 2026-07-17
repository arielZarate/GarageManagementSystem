package com.arielzarate.GarageManagementSystem.domain.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Version {
    private Long id;
    private String name;           // EX-S, XR150 L, 1.6 SE
    private Model model;           // FK → Model
}
