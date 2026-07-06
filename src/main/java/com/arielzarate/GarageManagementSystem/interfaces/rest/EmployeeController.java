package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.model.Address;
import com.arielzarate.GarageManagementSystem.domain.model.Employee;
import com.arielzarate.GarageManagementSystem.domain.model.enums.Role;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.employee.EmployeeRequest;
import com.arielzarate.GarageManagementSystem.interfaces.rest.mappers.EmployeeDTOMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeDTOMapper mapper;

    @GetMapping
    public String index(Model model) {

        Address ad1 = new Address("cordoba", "Cordoba", "granja de funes", "potel junot", "6872", "5000", "Argentina");
        Employee e1 = new Employee(13245L, "12345Lehajo", "Ariel", "Zarate", "3278551", LocalDate.of(1987, 1, 7), "20327855515",
                "arieltecnico@gmail.com", "admin", "35122266656", ad1, Role.MANAGER, true, LocalDate.now());
        List<Employee> list = new ArrayList<Employee>();

        list.add(e1);

        model.addAttribute("employees", list);
        return "employee/employeeView";

    }


    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("employeeObject", new EmployeeRequest());
        model.addAttribute("editMode", false);
        return "employee/employeeForm";
    }


    @PostMapping
    public String createEmployee(@ModelAttribute("employeeObject") EmployeeRequest request) {
        Employee e = mapper.toDomain(request);
        log.info("Empleado recibido: {}", e.toString());
        return "redirect:/employee";
    }


    @PostMapping("/edit/{id}")
    public String editModeEmployee(Model model, Long id) {
        //buscar id en db

        //Employee e=service.getEmployeeById(id);
        //var request=  mapper.mapToRequest(e);

        model.addAttribute("employeeObject", new Employee());
        model.addAttribute("editMode", false);
        return "/employee/employeeForm";
    }

    @PutMapping("/update")
    public String updateEmployee(@ModelAttribute("employeeObject") EmployeeRequest request) {
        return "";
    }

}
