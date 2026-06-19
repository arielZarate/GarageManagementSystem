package com.arielzarate.GarageManagementSystem.domain.ports.out;

import com.arielzarate.GarageManagementSystem.domain.model.Company;

public interface CompanyProvider {

    public Company create(Company company);
    public Company update(Company company);
    public void deleteCompany(Long id);
    public Company getCompany(Long id);

}
