package com.axiata.voucherpool.demo.repository;

import com.axiata.voucherpool.demo.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface VoucherRespository extends JpaRepository<Voucher, Long> {
	Optional<Voucher> findByCode(String code);

}
