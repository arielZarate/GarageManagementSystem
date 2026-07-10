package com.arielzarate.GarageManagementSystem.application.services;

import com.arielzarate.GarageManagementSystem.domain.model.Customer;
import com.arielzarate.GarageManagementSystem.domain.ports.in.CustomerService;
import com.arielzarate.GarageManagementSystem.domain.ports.out.CustomerProvider;
import com.arielzarate.GarageManagementSystem.domain.services.CodeGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerUseCase implements CustomerService {

    private final CustomerProvider provider;

    @Override
    public Customer addCustomer(Customer customer) {
        customer.setCustomerCode(CodeGenerator.generateCustomer(provider::countCustomers));
        customer.setActive(true);
        return provider.create(customer);
    }

    @Override
    public List<Customer> getCustomers(String query) {
        if (query != null && !query.isBlank()) {
            return provider.searchByDniOrCuit(query.trim());
        }

        return provider.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return provider.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public Customer getCustomerByDni(String dni) {
        return provider.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Customer not found with dni: " + dni));
    }


    @Override
    public Customer updateCustomer(Customer customer) {
        return provider.update(customer);
    }
}
