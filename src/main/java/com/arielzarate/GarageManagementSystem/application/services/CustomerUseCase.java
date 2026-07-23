package com.arielzarate.GarageManagementSystem.application.services;

import com.arielzarate.GarageManagementSystem.application.errors.ApplicationError;
import com.arielzarate.GarageManagementSystem.application.errors.ApplicationErrorException;
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
        if (customer.getFirstName() == null || customer.getFirstName().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El nombre no puede estar vacío."));
        }
        if (customer.getLastName() == null || customer.getLastName().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El apellido no puede estar vacío."));
        }
        if (customer.getDni() == null || customer.getDni().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El DNI/CUIT no puede estar vacío."));
        }
        if (customer.getPhone() == null || customer.getPhone().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El teléfono no puede estar vacío."));
        }
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
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError("Cliente no encontrado con el id: " + id)));
    }

    @Override
    public Customer getCustomerByDni(String dni) {
        return provider.findByDni(dni)
                .orElseThrow(() -> new ApplicationErrorException(ApplicationError.notFoundError("Cliente no encontrado con el DNI: " + dni)));
    }


    @Override
    public Customer updateCustomer(Customer customer) {
        if (customer.getFirstName() == null || customer.getFirstName().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El nombre no puede estar vacío."));
        }
        if (customer.getLastName() == null || customer.getLastName().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El apellido no puede estar vacío."));
        }
        if (customer.getDni() == null || customer.getDni().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El DNI/CUIT no puede estar vacío."));
        }
        if (customer.getPhone() == null || customer.getPhone().isBlank()) {
            throw new ApplicationErrorException(ApplicationError.badRequest("El teléfono no puede estar vacío."));
        }
        return provider.update(customer);
    }
}
