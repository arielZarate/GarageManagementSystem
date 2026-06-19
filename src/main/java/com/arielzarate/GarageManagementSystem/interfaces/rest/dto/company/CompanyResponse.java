package com.arielzarate.GarageManagementSystem.interfaces.rest.dto.company;

import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.address.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private Long id;
    private String businessName;
    private String legalName;
    private String logo;
    private String phone;
    private String email;
    private String horario;
    private String cuit;
    private AddressDTO address;
}
