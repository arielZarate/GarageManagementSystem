package com.arielzarate.GarageManagementSystem.domain.ports.in;

import com.arielzarate.GarageManagementSystem.domain.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomerById(Long id);

    Customer getCustomerByDni(String dni);

    List<Customer> searchByDniOrCuit(String query);

    Customer updateCustomer(Customer customer);

    void toggleStatusCustomer(Long id);
}
