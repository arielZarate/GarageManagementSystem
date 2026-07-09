package com.arielzarate.GarageManagementSystem.interfaces.rest.mappers;

import com.arielzarate.GarageManagementSystem.domain.model.Address;
import com.arielzarate.GarageManagementSystem.domain.model.Customer;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.address.AddressDTO;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.customer.CustomerRequest;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.customer.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerDTOMapper {

    @Mapping(target = "address.province", source = "province")
    @Mapping(target = "address.city", source = "city")
    @Mapping(target = "address.neighborhood", source = "neighborhood")
    @Mapping(target = "address.street", source = "street")
    @Mapping(target = "address.number", source = "number")
    @Mapping(target = "address.postalCode", source = "postalCode")
    @Mapping(target = "address.country", source = "country")
    Customer toDomain(CustomerRequest request);

    @Mapping(target = "province", source = "address.province")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "neighborhood", source = "address.neighborhood")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "number", source = "address.number")
    @Mapping(target = "postalCode", source = "address.postalCode")
    @Mapping(target = "country", source = "address.country")
    CustomerResponse toResponse(Customer customer);

    @Mapping(target = "province", source = "address.province")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "neighborhood", source = "address.neighborhood")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "number", source = "address.number")
    @Mapping(target = "postalCode", source = "address.postalCode")
    @Mapping(target = "country", source = "address.country")
    CustomerRequest toRequest(Customer customer);

    Address toDomain(AddressDTO dto);

    AddressDTO toDTO(Address address);
}
