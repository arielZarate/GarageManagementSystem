package com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories;

import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByCustomerCode(String customerCode);

    Optional<CustomerEntity> findByDni(String dni);

    @Query("""
       SELECT c 
       FROM CustomerEntity c 
       WHERE c.dni LIKE %:query% 
       OR c.cuit LIKE %:query% 
       OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :query, '%')) 
       OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
     """)
    List<CustomerEntity> searchByDniOrNameOrLastName(@Param("query") String query);

    @Query("""
       SELECT c 
       FROM CustomerEntity c 
       WHERE c.dni LIKE %:query% 
       OR c.cuit LIKE %:query% 
       OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :query, '%')) 
       OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
     """)
    Page<CustomerEntity> searchByDniOrNameOrLastName(@Param("query") String query, Pageable pageable);
}
