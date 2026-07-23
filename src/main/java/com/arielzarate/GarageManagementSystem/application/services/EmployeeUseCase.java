package com.arielzarate.GarageManagementSystem.application.services;


import com.arielzarate.GarageManagementSystem.application.errors.ApplicationError;
import com.arielzarate.GarageManagementSystem.application.errors.ApplicationErrorException;
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
        if (employee.getFirstName() == null || employee.getFirstName().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El nombre no puede estar vacío."));
        }
        if (employee.getLastName() == null || employee.getLastName().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El apellido no puede estar vacío."));
        }
        if (employee.getDni() == null || employee.getDni().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El DNI no puede estar vacío."));
        }
        if (employee.getPhone() == null || employee.getPhone().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El teléfono no puede estar vacío."));
        }
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
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError("Empleado no encontrado con el id: " + id)));
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if (employee.getFirstName() == null || employee.getFirstName().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El nombre no puede estar vacío."));
        }
        if (employee.getLastName() == null || employee.getLastName().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El apellido no puede estar vacío."));
        }
        if (employee.getDni() == null || employee.getDni().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El DNI no puede estar vacío."));
        }
        if (employee.getPhone() == null || employee.getPhone().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El teléfono no puede estar vacío."));
        }
        return provider.update(employee);
    }
}
