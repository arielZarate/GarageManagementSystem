package com.arielzarate.GarageManagementSystem.infraestructure.adapters;

import com.arielzarate.GarageManagementSystem.domain.model.Customer;
import com.arielzarate.GarageManagementSystem.domain.ports.out.CustomerProvider;
import com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers.CustomerMapper;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.CustomerEntity;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomerAdapter implements CustomerProvider {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public Customer create(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Customer update(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findByDni(String dni) {
        return repository.findByDni(dni).map(mapper::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Customer> searchByDniOrCuit(String query) {
        return repository.searchByDniOrCuit(query)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public long countCustomers() {
        return repository.count();
    }
}
