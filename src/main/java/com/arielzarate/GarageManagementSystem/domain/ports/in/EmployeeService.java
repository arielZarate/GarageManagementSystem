package com.arielzarate.GarageManagementSystem.domain.ports.in;

import com.arielzarate.GarageManagementSystem.domain.model.Company;
import com.arielzarate.GarageManagementSystem.domain.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    List<Employee> getEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Employee employee);

    void toggleStatusEmployee(Long id);
}
