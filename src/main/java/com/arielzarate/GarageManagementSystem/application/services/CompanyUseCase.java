package com.arielzarate.GarageManagementSystem.application.services;

import com.arielzarate.GarageManagementSystem.application.errors.ApplicationError;
import com.arielzarate.GarageManagementSystem.application.errors.ApplicationErrorException;
import com.arielzarate.GarageManagementSystem.domain.model.Company;
import com.arielzarate.GarageManagementSystem.domain.ports.in.CompanyService;
import com.arielzarate.GarageManagementSystem.domain.ports.out.CompanyProvider;
import com.arielzarate.GarageManagementSystem.domain.services.validators.CUITValidator;
import com.arielzarate.GarageManagementSystem.domain.services.validators.EmailValidator;
import com.arielzarate.GarageManagementSystem.domain.services.validators.PhoneValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CompanyUseCase implements CompanyService {

    private final CompanyProvider provider;

    @Override
    public Company addCompany(Company company) {
        validateBusinessRules(company);
        return provider.create(company);
    }

    @Override
    public Company getCompany() {
        return provider.getCompany().orElse(null);
    }

    @Override
    public Company editCompany(Company company) {
        validateBusinessRules(company);
        return provider.update(company);
    }

    @Override
    public void deleteCompany(Long id) {
        provider.deleteCompany(id);
    }

    private void validateBusinessRules(Company company) {
        try {
            CUITValidator.validate(company.getCuit());
            EmailValidator.validate(company.getEmail());
            PhoneValidator.validate(company.getPhone());
        } catch (IllegalArgumentException e) {
            throw new ApplicationErrorException(ApplicationError.badRequest(e.getMessage()));
        }
    }
}
