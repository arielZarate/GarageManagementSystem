package com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers;

import com.arielzarate.GarageManagementSystem.domain.model.Address;
import com.arielzarate.GarageManagementSystem.domain.model.Company;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.AddressEmbeddable;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {


    // ── Domain ↔ Entity ──
    CompanyEntity toEntity(Company company);
    AddressEmbeddable toEmbeddable(Address address);





    // ── Entity ↔ Domain ──
    Company toDomain(CompanyEntity entity);
    Address toDomain(AddressEmbeddable embeddable);
}
