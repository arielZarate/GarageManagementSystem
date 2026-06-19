package com.arielzarate.GarageManagementSystem.interfaces.rest.mappers;


import com.arielzarate.GarageManagementSystem.domain.model.Address;
import com.arielzarate.GarageManagementSystem.domain.model.Company;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.address.AddressDTO;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.company.CompanyRequest;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.company.CompanyResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyDTOMapper {

    Company toDomain(CompanyRequest request);
    Address toDomain(AddressDTO dto);
    CompanyRequest toRequest(Company company);

    CompanyResponse toResponse(Company company);
    AddressDTO toDTO(Address address);

}
