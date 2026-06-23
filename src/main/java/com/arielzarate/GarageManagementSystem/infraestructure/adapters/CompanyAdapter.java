package com.arielzarate.GarageManagementSystem.infraestructure.adapters;

import com.arielzarate.GarageManagementSystem.domain.model.Company;
import com.arielzarate.GarageManagementSystem.domain.ports.out.CompanyProvider;
import com.arielzarate.GarageManagementSystem.infraestructure.adapters.mappers.CompanyMapper;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CompanyAdapter implements CompanyProvider {

     private final CompanyRepository companyRepository;
     private final CompanyMapper mapper;

    @Override
    public Company create(Company company) {
        var entity = mapper.toEntity(company);
        entity = companyRepository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Company update(Company company) {
        var entity = mapper.toEntity(company);
        entity = companyRepository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Optional<Company> getCompany() {
        return companyRepository.findAll()
                .stream()
                .findFirst()
                .map(mapper::toDomain);
    }


}
