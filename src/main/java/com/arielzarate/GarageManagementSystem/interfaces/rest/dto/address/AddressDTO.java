package com.arielzarate.GarageManagementSystem.interfaces.rest.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDTO {

    @NotBlank(message = "La provincia es obligatoria")
    @Size(max = 50, message = "La provincia no puede exceder 50 caracteres")
    private String province;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 50, message = "La ciudad no puede exceder 50 caracteres")
    private String city;

    private String neighborhood;

    @NotBlank(message = "La calle es obligatoria")
    @Size(max = 100, message = "La calle no puede exceder 100 caracteres")
    private String street;

    @Size(max = 10, message = "La altura no puede exceder 10 caracteres")
    private String number;

    @Size(max = 10, message = "El código postal no puede exceder 10 caracteres")
    private String postalCode;

    @Size(max = 50, message = "El país no puede exceder 50 caracteres")
    private String country;
}
