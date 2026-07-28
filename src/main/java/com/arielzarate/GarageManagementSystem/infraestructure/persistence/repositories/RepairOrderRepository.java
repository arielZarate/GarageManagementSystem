package com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories;

import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.RepairOrder;
import com.arielzarate.GarageManagementSystem.domain.model.enums.RepairStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {

    Optional<RepairOrder> findByOrderNumber(String orderNumber);

    @Query("SELECT r FROM RepairOrder r WHERE r.vehicleEntity.id = :vehicleId")
    List<RepairOrder> findByVehicleId(@Param("vehicleId") Long vehicleId);

    List<RepairOrder> findByCustomerId(Long customerId);

    List<RepairOrder> findByEmployeeId(Long employeeId);

    List<RepairOrder> findByStatus(RepairStatus status);

    @Query("SELECT r FROM RepairOrder r WHERE r.vehicleEntity.licensePlate LIKE CONCAT('%', :licensePlate, '%')")
    List<RepairOrder> findByVehicleLicensePlate(@Param("licensePlate") String licensePlate);
}
