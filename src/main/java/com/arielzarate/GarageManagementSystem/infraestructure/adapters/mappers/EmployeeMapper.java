package com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers;


import com.arielzarate.GarageManagementSystem.domain.model.Address;
import com.arielzarate.GarageManagementSystem.domain.model.Employee;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.AddressEmbeddable;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    // ── Domain ↔ Entity ──
    EmployeeEntity toEntity(Employee employee);

    AddressEmbeddable toEmbeddable(Address address);


    // ── Entity ↔ Domain ──
    Employee toDomain(EmployeeEntity entity);

    Address toDomain(AddressEmbeddable embeddable);
}
