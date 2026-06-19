package com.arielzarate.GarageManagementSystem.interfaces.rest;

import com.arielzarate.GarageManagementSystem.domain.ports.in.CompanyService;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.address.AddressDTO;
import com.arielzarate.GarageManagementSystem.interfaces.rest.dto.company.CompanyRequest;
import com.arielzarate.GarageManagementSystem.interfaces.rest.mappers.CompanyDTOMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService service;
    private final CompanyDTOMapper mapper;

    @GetMapping
    public String index(Model model) {
        var company = service.getCompany();
        if (company == null) {
            return "redirect:/company/new";
        }
        model.addAttribute("company", mapper.toResponse(company));
        return "companyView";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("companyObject", new CompanyRequest());
        return "companyForm";
    }

    @PostMapping
    public String create(@ModelAttribute CompanyRequest request) {
        log.info("Request recibida: {}", request);
        var domain = mapper.toDomain(request);
        var saved = service.addCompany(domain);
        log.info("Company guardada: {}", saved);
        return "redirect:/company";
    }

    @GetMapping("/edit")
    public String editForm(Model model) {
        var company = service.getCompany();
        if (company == null) {
            return "redirect:/company/new";
        }
        var request = mapper.toRequest(company);
        if (request.getAddress() == null) {
            request.setAddress(new AddressDTO());
        }
        model.addAttribute("companyObject", request);
        model.addAttribute("editMode", true);
        return "companyForm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute CompanyRequest request) {
        log.info("Update request: {}", request);
        var domain = mapper.toDomain(request);
        service.updateCompany(domain);
        log.info("Company updated");
        return "redirect:/company";
    }

    @PostMapping("/delete")
    public String delete() {
        var company = service.getCompany();
        if (company != null) {
            service.deleteCompany(company.getId());
        }
        log.info("Company deleted successfully");
        return "redirect:/company";
    }
}
