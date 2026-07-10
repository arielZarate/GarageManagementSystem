package com.arielzarate.GarageManagementSystem.interfaces.rest.dto.employee;


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
public class EmployeeRequest {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    //@NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{7,8}", message = "El DNI debe tener entre 7 y 8 dígitos")
    private String dni;

    private String birthDate;

    @NotBlank(message = "El CUIT es obligatorio")
    @Pattern(regexp = "\\d{11}|\\d{2}-\\d{8}-\\d", message = "El CUIT debe tener 11 dígitos (ej: 20-12345678-9)")
    private String cuit;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String email;

    //validator password
    private String password;

    @NotBlank(message = "El teléfono es obligatorio")
  //  @Pattern(regexp = "\\d{7,15}", message = "El teléfono debe tener entre 7 y 15 dígitos")
    private String phone;

    @NotBlank(message = "El Role es obligatorio")
    private String role;


    private Boolean active;

    // Address fields
  //  @NotBlank(message = "La provincia es obligatoria")
    private String province;
  //  @NotBlank(message = "La ciudad es obligatoria")
    private String city;
  //  @NotBlank(message = "El barrio es obligatorio")
    private String neighborhood;
   // @NotBlank(message = "La calle es obligatoria")
    private String street;
   // @NotBlank(message = "El numero de calle es obligatorio")
    private String number;
  //  @NotBlank(message = "El código postal es obligatorio")
    private String postalCode;
    private String country;

}
