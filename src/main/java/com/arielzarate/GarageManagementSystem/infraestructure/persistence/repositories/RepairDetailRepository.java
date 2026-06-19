package com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories;

import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.RepairDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairDetailRepository extends JpaRepository<RepairDetail, Long> {

    List<RepairDetail> findByRepairOrderId(Long repairOrderId);
}
