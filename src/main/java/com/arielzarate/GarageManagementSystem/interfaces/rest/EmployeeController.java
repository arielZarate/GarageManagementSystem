package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.model.Employee;
import com.arielzarate.GarageManagementSystem.domain.model.enums.Role;
import com.arielzarate.GarageManagementSystem.domain.ports.in.EmployeeService;
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
        model.addAttribute("employees", list);
        return "employee/employeeView";

    }


    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("employeeObject", new EmployeeRequest());
        model.addAttribute("roles",Role.values()); //send list al front
        model.addAttribute("editMode", false);
        return "employee/employeeForm";
    }


    @PostMapping
    public String createEmployee(@Valid @ModelAttribute("employeeObject") EmployeeRequest request,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            model.addAttribute("editMode", false);
            return "employee/employeeForm";
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

        model.addAttribute("employeeObject", request);
        model.addAttribute("roles", Role.values());
        model.addAttribute("editMode", true);
        return "employee/employeeForm";
    }

    @PostMapping("/update")
    public String updateEmployee(@Valid @ModelAttribute("employeeObject") EmployeeRequest request,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            model.addAttribute("editMode", true);
            return "employee/employeeForm";
        }

        Employee e = mapper.toDomain(request);
        service.updateEmployee(e);
        return "redirect:/employee";
    }

    @PostMapping("/toggle/{id}")
    public String toggleEmployee(@PathVariable Long id) {
        service.toggleStatusEmployee(id);
        return "redirect:/employee";
    }


}
