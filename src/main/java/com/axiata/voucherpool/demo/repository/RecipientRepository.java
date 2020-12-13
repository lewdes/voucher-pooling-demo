package com.axiata.voucherpool.demo.repository;

import com.axiata.voucherpool.demo.model.Recipient;
import com.axiata.voucherpool.demo.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecipientRepository extends JpaRepository<Recipient, Long> {
	Recipient findByEmail(String email);
}
