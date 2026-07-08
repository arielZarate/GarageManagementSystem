package com.arielzarate.GarageManagementSystem.domain.ports.out;

import com.arielzarate.GarageManagementSystem.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerProvider {

    Customer create(Customer customer);

    Customer update(Customer customer);

    Optional<Customer> findById(Long id);

    Optional<Customer> findByDni(String dni);

    List<Customer> findAll();

    long countCustomers();
}
