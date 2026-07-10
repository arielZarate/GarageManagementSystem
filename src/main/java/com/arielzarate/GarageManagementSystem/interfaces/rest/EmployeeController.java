package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.model.Employee;
import com.arielzarate.GarageManagementSystem.domain.model.enums.Role;
import com.arielzarate.GarageManagementSystem.domain.ports.in.EmployeeService;
import com.arielzarate.GarageManagementSystem.interfaces.rest.constants.Country;
import com.arielzarate.GarageManagementSystem.interfaces.rest.constants.Province;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.employee.EmployeeRequest;
import com.arielzarate.GarageManagementSystem.interfaces.rest.mappers.EmployeeDTOMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;
    private final EmployeeDTOMapper mapper;

    @GetMapping
    public String getEmployees(Model model) {
        List<Employee> list = service.getEmployees();
        model.addAttribute("pageTitle", "Empleados");
        model.addAttribute("content", "employee/list");
        model.addAttribute("employees", list);
        return "fragments/base";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Employee employee = service.getEmployeeById(id);
        model.addAttribute("pageTitle", "Detalle de Empleado");
        model.addAttribute("content", "employee/detail");
        model.addAttribute("employee", employee);
        return "fragments/base";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("pageTitle", "Nuevo Empleado");
        model.addAttribute("content", "employee/form");
        model.addAttribute("employeeObject", new EmployeeRequest());
        model.addAttribute("roles", Role.values());
        model.addAttribute("provinces", Province.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("editMode", false);
        return "fragments/base";
    }

    @PostMapping
    public String createEmployee(@Valid @ModelAttribute("employeeObject") EmployeeRequest request,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Nuevo Empleado");
            model.addAttribute("content", "employee/form");
            model.addAttribute("roles", Role.values());
            model.addAttribute("provinces", Province.values());
            model.addAttribute("countries", Country.values());
            model.addAttribute("editMode", false);
            return "fragments/base";
        }

        Employee e = mapper.toDomain(request);
        service.addEmployee(e);
        log.info("employee created");

        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String showForm(Model model, @PathVariable Long id) {
        Employee e = service.getEmployeeById(id);
        EmployeeRequest request = mapper.toRequest(e);

        model.addAttribute("pageTitle", "Editar Empleado");
        model.addAttribute("content", "employee/form");
        model.addAttribute("employeeObject", request);
        model.addAttribute("roles", Role.values());
        model.addAttribute("provinces", Province.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("editMode", true);
        return "fragments/base";
    }

    @PostMapping("/update")
    public String updateEmployee(@Valid @ModelAttribute("employeeObject") EmployeeRequest request,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Editar Empleado");
            model.addAttribute("content", "employee/form");
            model.addAttribute("roles", Role.values());
            model.addAttribute("provinces", Province.values());
            model.addAttribute("countries", Country.values());
            model.addAttribute("editMode", true);
            return "fragments/base";
        }

        Employee e = mapper.toDomain(request);
        service.updateEmployee(e);
        return "redirect:/employee";
    }

}
