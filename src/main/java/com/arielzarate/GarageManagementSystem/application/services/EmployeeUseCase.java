package com.arielzarate.GarageManagementSystem.application.services;


import com.arielzarate.GarageManagementSystem.domain.model.Employee;
import com.arielzarate.GarageManagementSystem.domain.ports.in.EmployeeService;
import com.arielzarate.GarageManagementSystem.domain.ports.out.EmployeeProvider;
import com.arielzarate.GarageManagementSystem.domain.services.CodeGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class EmployeeUseCase implements EmployeeService {
    private final EmployeeProvider provider;

    @Override
    public Employee addEmployee(Employee employee) {
        employee.setLegajo(CodeGenerator.generateEmployee(provider::countEmployees));
        employee.setActive(true);
        employee.setJoinDate(LocalDate.now());
        return provider.create(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return provider.getEmployees();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return provider.findEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        //hay que crear excepciones customizaddas
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return provider.update(employee);
    }
}
