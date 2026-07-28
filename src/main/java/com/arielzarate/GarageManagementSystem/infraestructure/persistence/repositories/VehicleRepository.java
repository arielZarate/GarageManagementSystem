package com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories;

import com.arielzarate.GarageManagementSystem.domain.model.enums.VehicleType;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.VehicleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    @Override
    @Query("""
        SELECT DISTINCT v FROM VehicleEntity v
        LEFT JOIN FETCH v.brand
        LEFT JOIN FETCH v.model
        LEFT JOIN FETCH v.version
        LEFT JOIN FETCH v.customer
    """)
    Page<VehicleEntity> findAll(Pageable pageable);

    @Query("""
        SELECT DISTINCT v 
        FROM VehicleEntity v
        LEFT JOIN FETCH v.brand
        LEFT JOIN FETCH v.model
        LEFT JOIN FETCH v.version
        LEFT JOIN FETCH v.customer c
        WHERE v.licensePlate LIKE CONCAT('%', :query, '%')
        OR c.dni LIKE CONCAT('%', :query, '%')
        OR c.firstName LIKE CONCAT('%', :query, '%')
        OR c.lastName LIKE CONCAT('%', :query, '%')
    """)
    Page<VehicleEntity> searchByLicensePlateOrDNI(@Param("query") String query, Pageable pageable);

    @Query("""
        SELECT DISTINCT v FROM VehicleEntity v
        LEFT JOIN FETCH v.brand
        LEFT JOIN FETCH v.model m
        LEFT JOIN FETCH v.version
        LEFT JOIN FETCH v.customer
        WHERE m.vehicleType = :type
    """)
    Page<VehicleEntity> findByVehicleType(@Param("type") VehicleType type, Pageable pageable);

}
