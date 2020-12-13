package com.axiata.voucherpool.demo.repository;

import com.axiata.voucherpool.demo.model.Recipient;
import com.axiata.voucherpool.demo.model.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {
}
