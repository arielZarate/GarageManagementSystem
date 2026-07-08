package com.arielzarate.GarageManagementSystem.interfaces.rest.dto.company;

import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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
public class CompanyRequest {
    private Long id;

    @NotBlank(message = "El nombre de fantasía es obligatorio")
    @Size(max = 100, message = "El nombre de fantasía no puede exceder 100 caracteres")
    private String businessName;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 100, message = "La razón social no puede exceder 100 caracteres")
    private String legalName;

    private String logo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    private String phone;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe proporcionar un email válido")
    @Size(max = 100, message = "El email no puede exceder 100 caracteres")
    private String email;

    private String horario;

    @NotBlank(message = "El CUIT es obligatorio")
    @Size(min = 11, max = 13, message = "El CUIT debe tener entre 11 y 13 caracteres")
    private String cuit;

    @Valid
    private AddressDTO address = new AddressDTO();
}
