package com.arielzarate.GarageManagementSystem.domain.ports.in;

import com.arielzarate.GarageManagementSystem.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);

    List<Customer> getCustomers(String query);

    Page<Customer> getCustomersPage(String query, Pageable pageable);

    Customer getCustomerById(Long id);

    Customer getCustomerByDni(String dni);

    Customer updateCustomer(Customer customer);
}
