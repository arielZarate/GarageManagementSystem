package com.arielzarate.GarageManagementSystem.infraestructure.persistence.repositories;

import com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities.QuoteDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteDetailRepository extends JpaRepository<QuoteDetail, Long> {

    List<QuoteDetail> findByQuoteId(Long quoteId);
}
