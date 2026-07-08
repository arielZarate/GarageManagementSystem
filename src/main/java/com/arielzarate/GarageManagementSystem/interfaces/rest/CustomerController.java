package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.model.Customer;
import com.arielzarate.GarageManagementSystem.domain.ports.in.CustomerService;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.customer.CustomerRequest;
import com.arielzarate.GarageManagementSystem.interfaces.rest.mappers.CustomerDTOMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;
    private final CustomerDTOMapper mapper;

    @GetMapping
    public String getCustomers(Model model) {
        List<Customer> list = service.getCustomers();
        model.addAttribute("customers", list);
        return "customer/customerView";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("customerObject", new CustomerRequest());
        model.addAttribute("editMode", false);
        return "customer/customerForm";
    }

    @PostMapping
    public String createCustomer(@Valid @ModelAttribute("customerObject") CustomerRequest request,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("editMode", false);
            return "customer/customerForm";
        }

        Customer customer = mapper.toDomain(request);
        service.addCustomer(customer);
        log.info("Customer created");

        return "redirect:/customer";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Customer customer = service.getCustomerById(id);
            CustomerRequest request = mapper.toRequest(customer);
            model.addAttribute("customerObject", request);
            model.addAttribute("editMode", true);
            return "customer/customerForm";
        } catch (RuntimeException e) {
            log.warn("Customer not found for edit: {}", id);
            return "redirect:/customer";
        }
    }

    @PostMapping("/update")
    public String updateCustomer(@Valid @ModelAttribute("customerObject") CustomerRequest request,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("editMode", true);
            return "customer/customerForm";
        }

        Customer customer = mapper.toDomain(request);
        service.updateCustomer(customer);
        log.info("Customer updated");

        return "redirect:/customer";
    }

    @PostMapping("/toggle/{id}")
    public String toggleCustomer(@PathVariable Long id) {
        service.toggleStatusCustomer(id);
        log.info("Customer {} toggled", id);
        return "redirect:/customer";
    }

    @GetMapping("/search")
    public String searchByDni(@RequestParam("dni") String dni, Model model) {
        try {
            Customer customer = service.getCustomerByDni(dni);
            model.addAttribute("customers", List.of(customer));
            model.addAttribute("searchDni", dni);
        } catch (RuntimeException e) {
            log.warn("Customer not found by dni: {}", dni);
            model.addAttribute("customers", List.of());
            model.addAttribute("searchDni", dni);
            model.addAttribute("notFound", true);
        }
        return "customer/customerView";
    }
}
