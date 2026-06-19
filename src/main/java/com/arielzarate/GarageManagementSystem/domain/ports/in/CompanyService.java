package com.arielzarate.GarageManagementSystem.domain.ports.in;

import com.arielzarate.GarageManagementSystem.domain.model.Company;

public interface CompanyService {

    Company addCompany(Company company);

    Company getCompany(Long id);

    Company updateCompany(Company company);
}
