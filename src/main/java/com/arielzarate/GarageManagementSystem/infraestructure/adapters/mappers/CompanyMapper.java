package com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers;

import com.arielzarate.GarageManagementSystem.domain.model.Address;
import com.arielzarate.GarageManagementSystem.domain.model.Company;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.AddressEmbeddable;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.CompanyEntity;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.address.AddressDTO;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.company.CompanyRequest;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.company.CompanyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    // ── DTO ↔ Domain ──
    Company toDomain(CompanyRequest request);

    CompanyResponse toResponse(Company company);

    Address toDomain(AddressDTO dto);

    AddressDTO toDTO(Address address);

    // ── Domain ↔ Entity ──
    CompanyEntity toEntity(Company company);

    Company toDomain(CompanyEntity entity);

    AddressEmbeddable toEmbeddable(Address address);

    Address toDomain(AddressEmbeddable embeddable);
}
