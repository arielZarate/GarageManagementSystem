package com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories;

import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
