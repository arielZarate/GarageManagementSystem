package com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories;

import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.RepairOrder;
import com.arielzarate.GarageManagementSystem.domain.model.enums.RepairStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {

    Optional<RepairOrder> findByOrderNumber(String orderNumber);

    List<RepairOrder> findByVehicleId(Long vehicleId);

    List<RepairOrder> findByCustomerId(Long customerId);

    List<RepairOrder> findByEmployeeId(Long employeeId);

    List<RepairOrder> findByStatus(RepairStatus status);

    List<RepairOrder> findByVehicleLicensePlate(String licensePlate);
}
