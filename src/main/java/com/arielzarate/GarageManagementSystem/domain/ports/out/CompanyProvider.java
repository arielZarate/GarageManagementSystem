package com.arielzarate.GarageManagementSystem.domain.ports.out;

import com.arielzarate.GarageManagementSystem.domain.model.Company;
import java.util.Optional;

public interface CompanyProvider {

    Company create(Company company);
    Company update(Company company);
    void deleteCompany(Long id);
    Optional<Company> getCompany();

}
