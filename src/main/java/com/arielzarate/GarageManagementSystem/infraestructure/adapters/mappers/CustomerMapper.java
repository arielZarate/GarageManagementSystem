package com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers;

import com.arielzarate.GarageManagementSystem.domain.model.Address;
import com.arielzarate.GarageManagementSystem.domain.model.Customer;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.AddressEmbeddable;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // ── Domain ↔ Entity ──
    CustomerEntity toEntity(Customer customer);

    Customer toDomain(CustomerEntity entity);

    AddressEmbeddable toEmbeddable(Address address);

    Address toDomain(AddressEmbeddable embeddable);
}
