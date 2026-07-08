package com.arielzarate.GarageManagementSystem.infraestructure.adapters;


import com.arielzarate.GarageManagementSystem.domain.model.Employee;
import com.arielzarate.GarageManagementSystem.domain.ports.out.EmployeeProvider;
import com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers.EmployeeMapper;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.EmployeeEntity;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class EmployeeAdapter implements EmployeeProvider {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    public Employee create(Employee employee) {
        EmployeeEntity entity = mapper.toEntity(employee);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Employee update(Employee employee) {
        EmployeeEntity entity = mapper.toEntity(employee);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Employee> getEmployees() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public long countEmployees() {
        return repository.count();
    }
}
