package com.arielzarate.GarageManagementSystem.interfaces.rest.mappers;


import com.arielzarate.GarageManagementSystem.domain.model.Address;
import com.arielzarate.GarageManagementSystem.domain.model.Employee;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.address.AddressDTO;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.employee.EmployeeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface EmployeeDTOMapper {

    @Mapping(target = "address.province", source = "province")
    @Mapping(target = "address.city", source = "city")
    @Mapping(target = "address.neighborhood", source = "neighborhood")
    @Mapping(target = "address.street", source = "street")
    @Mapping(target = "address.number", source = "number")
    @Mapping(target = "address.postalCode", source = "postalCode")
    @Mapping(target = "address.country", source = "country")
    @Mapping(target = "CUIT", source = "cuit")
    @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "parseBirthDate")
    Employee toDomain(EmployeeRequest request);

    @Mapping(target = "province", source = "address.province")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "neighborhood", source = "address.neighborhood")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "number", source = "address.number")
    @Mapping(target = "postalCode", source = "address.postalCode")
    @Mapping(target = "country", source = "address.country")
    @Mapping(target = "cuit", source = "CUIT")
    @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "formatBirthDate")
    EmployeeRequest toRequest(Employee employee);

    Address toDomain(AddressDTO dto);

    @Named("parseBirthDate")
    default LocalDate mapStringToLocalDate(String date) {
        if (date == null || date.isBlank()) return null;
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Named("formatBirthDate")
    default String mapLocalDateToString(LocalDate date) {
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
