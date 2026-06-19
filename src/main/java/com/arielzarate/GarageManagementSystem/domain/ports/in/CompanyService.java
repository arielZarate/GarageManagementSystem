package com.arielzarate.GarageManagementSystem.domain.ports.in;

import com.arielzarate.GarageManagementSystem.domain.model.Company;
import java.util.List;

public interface CompanyService {

    Company addCompany(Company company);

    Company getCompany();

    Company updateCompany(Company company);

    void deleteCompany(Long id);
}
