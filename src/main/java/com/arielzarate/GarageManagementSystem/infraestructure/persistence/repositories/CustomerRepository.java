package com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories;

import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByCustomerCode(String customerCode);

    Optional<CustomerEntity> findByDni(String dni);

    @Query("SELECT c FROM CustomerEntity c WHERE c.dni LIKE %:query% OR c.cuit LIKE %:query%")
    List<CustomerEntity> searchByDniOrCuit(@Param("query") String query);
}
