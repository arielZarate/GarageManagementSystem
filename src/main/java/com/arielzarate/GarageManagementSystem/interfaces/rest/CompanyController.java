package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.ports.in.CompanyService;
import com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers.CompanyMapper;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.company.CompanyRequest;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.company.CompanyResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService service;
    private final CompanyMapper mapper;

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("companyObject", new CompanyRequest());
        return "companyForm";
    }

    @PostMapping
    public String create(@ModelAttribute CompanyRequest request) {
        var domain = mapper.toDomain(request);
        var saved = service.addCompany(domain);
        return "redirect:/company/" + saved.getId();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        var company = service.getCompany(id);
        var response = mapper.toResponse(company);
        model.addAttribute("company", response);
        return "companyView";
    }
}
