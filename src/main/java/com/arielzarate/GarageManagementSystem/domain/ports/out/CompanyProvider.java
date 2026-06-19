package com.arielzarate.GarageManagementSystem.domain.ports.out;

import com.arielzarate.GarageManagementSystem.domain.model.Company;
import java.util.List;

public interface CompanyProvider {

    Company create(Company company);
    Company update(Company company);
    void deleteCompany(Long id);
    Company getCompany(Long id);
    List<Company> list();

}
