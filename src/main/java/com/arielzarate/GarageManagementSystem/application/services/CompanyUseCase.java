package com.arielzarate.GarageManagementSystem.application.services;

import com.arielzarate.GarageManagementSystem.domain.model.Company;
import com.arielzarate.GarageManagementSystem.domain.ports.in.CompanyService;
import com.arielzarate.GarageManagementSystem.domain.ports.out.CompanyProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CompanyUseCase implements CompanyService {

    private final CompanyProvider provider;

    @Override
    public Company addCompany(Company company) {
        return provider.create(company);
    }

    @Override
    public Company getCompany(Long id) {
        return provider.getCompany(id);
    }

    @Override
    public Company updateCompany(Company company) {
        return provider.update(company);
    }
}
