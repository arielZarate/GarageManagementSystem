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
    public String getCustomers(@RequestParam(value = "q", required = false) String query, Model model) {
        List<Customer> list;
        if (query != null && !query.isBlank()) {
            list = service.searchByDniOrCuit(query.trim());
            model.addAttribute("searchQuery", query.trim());
        } else {
            list = service.getCustomers();
        }
        model.addAttribute("pageTitle", "Clientes");
        model.addAttribute("content", "customer/list");
        model.addAttribute("customers", list);
        return "layout/base";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        try {
            Customer customer = service.getCustomerById(id);
            model.addAttribute("pageTitle", "Detalle del Cliente");
            model.addAttribute("content", "customer/detail");
            model.addAttribute("customer", customer);
            return "layout/base";
        } catch (RuntimeException e) {
            log.warn("Customer not found: {}", id);
            return "redirect:/customer";
        }
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("pageTitle", "Nuevo Cliente");
        model.addAttribute("content", "customer/form");
        model.addAttribute("customerObject", new CustomerRequest());
        model.addAttribute("editMode", false);
        return "layout/base";
    }

    @PostMapping
    public String createCustomer(@Valid @ModelAttribute("customerObject") CustomerRequest request,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Nuevo Cliente");
            model.addAttribute("content", "customer/form");
            model.addAttribute("editMode", false);
            return "layout/base";
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
            model.addAttribute("pageTitle", "Editar Cliente");
            model.addAttribute("content", "customer/form");
            model.addAttribute("customerObject", request);
            model.addAttribute("editMode", true);
            return "layout/base";
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
            model.addAttribute("pageTitle", "Editar Cliente");
            model.addAttribute("content", "customer/form");
            model.addAttribute("editMode", true);
            return "layout/base";
        }

        Customer customer = mapper.toDomain(request);
        service.updateCustomer(customer);
        log.info("Customer updated");

        return "redirect:/customer";
    }

}
