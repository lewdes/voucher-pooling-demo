package com.axiata.voucherpool.demo.service;

import com.axiata.voucherpool.demo.model.SpecialOffer;
import com.axiata.voucherpool.demo.model.Voucher;
import com.axiata.voucherpool.demo.payload.RedeemRequest;
import com.axiata.voucherpool.demo.payload.RedeemResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface VoucherService {
	List<Voucher> getAll();

	Voucher updateVoucher(Voucher voucher);

	void generateVoucher(SpecialOffer specialOffer);

	RedeemResponse redeem(RedeemRequest request);

}
