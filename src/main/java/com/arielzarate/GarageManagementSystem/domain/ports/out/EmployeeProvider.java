package com.arielzarate.GarageManagementSystem.domain.ports.out;

import com.arielzarate.GarageManagementSystem.domain.model.Company;
import com.arielzarate.GarageManagementSystem.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeProvider {

    Employee create(Employee employee);

    Employee update(Employee employee);

    Optional<Employee> findEmployeeById(Long id);

    List<Employee> getEmployees();

    long countEmployees();
}
