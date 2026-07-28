package com.arielzarate.GarageManagementSystem.domain.ports.out;

import com.arielzarate.GarageManagementSystem.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerProvider {

    Customer create(Customer customer);

    Customer update(Customer customer);

    Optional<Customer> findById(Long id);

    Optional<Customer> findByDni(String dni);

    List<Customer> findAll();

    Page<Customer> findAll(Pageable pageable);

    List<Customer> searchByDniOrNameOrLastName(String query);

    Page<Customer> searchByDniOrNameOrLastName(String query, Pageable pageable);

    long countCustomers();
}
