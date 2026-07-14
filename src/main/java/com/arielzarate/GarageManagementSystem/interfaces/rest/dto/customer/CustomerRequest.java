package com.arielzarate.GarageManagementSystem.interfaces.rest.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class CustomerRequest {

    private Long id;

    private String customerCode;

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{7,8}", message = "El DNI debe tener entre 7 y 8 dígitos")
    private String dni;

    @NotBlank(message = "El CUIT es obligatorio")
    @Pattern(regexp = "\\d{11}|\\d{2}-\\d{8}-\\d", message = "El CUIT debe tener 11 dígitos (ej: 20-12345678-9)")
    private String cuit;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;

    private Boolean active;

    // Address fields
    private String province;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
    private String postalCode;
    private String country = "Argentina";
}
