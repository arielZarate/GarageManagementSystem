package com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories;

import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.Quote;
import com.arielzarate.GarageManagementSystem.infraestructure.persistence.enums.QuoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Optional<Quote> findByQuoteNumber(String quoteNumber);

    List<Quote> findByCustomerId(Long customerId);

    List<Quote> findByStatus(QuoteStatus status);
}
