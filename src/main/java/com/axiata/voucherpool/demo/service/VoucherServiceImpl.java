package com.axiata.voucherpool.demo.service;

import com.axiata.voucherpool.demo.exception.ResourceNotFoundException;
import com.axiata.voucherpool.demo.model.Recipient;
import com.axiata.voucherpool.demo.model.SpecialOffer;
import com.axiata.voucherpool.demo.model.Voucher;
import com.axiata.voucherpool.demo.payload.RedeemRequest;
import com.axiata.voucherpool.demo.payload.RedeemResponse;
import com.axiata.voucherpool.demo.payload.ValidVoucher;
import com.axiata.voucherpool.demo.repository.RecipientRepository;
import com.axiata.voucherpool.demo.repository.VoucherRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class VoucherServiceImpl implements VoucherService {

	@Autowired
	VoucherRespository voucherRespository;

	@Autowired
	RecipientRepository recipientRepository;


	@Override
	public Voucher updateVoucher(Voucher voucher) {
		Optional<Voucher> voucherDb = this.voucherRespository.findById(voucher.getId());

		if (voucherDb.isPresent()) {
			Voucher voucherUpdate = voucherDb.get();

			voucherRespository.save(voucherUpdate);
			return voucherUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : ");
		}
	}

	@Override
	public List<Voucher> getAll() {
		return voucherRespository.findAll();
	}


	/**
	 * Based on the special offer, and generate vouchers and assign to all recipient
	 *
	 * @param specialOffer
	 */
	@Override
	public void generateVoucher(SpecialOffer specialOffer) {

		//hardcode expire date from 3 months of the current date
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate localExpiredDate = LocalDate.now().plusMonths(3);
		Date expiredDate = Date.from(localExpiredDate.atStartOfDay(defaultZoneId).toInstant());

		List<Recipient> recipientList = recipientRepository.findAll();
		for (Recipient r : recipientList) {
			Voucher voucher = new Voucher();
			voucher.setCode(generateVoucherCode());
			voucher.setSpecialOffer(specialOffer);
			voucher.setRecipient(r);
			voucher.setExpiredDate(expiredDate);

			voucherRespository.save(voucher);
		}
	}

	@Override
	public RedeemResponse redeem(RedeemRequest request) {
		RedeemResponse response = new RedeemResponse();
		Optional<Voucher> voucher = voucherRespository.findByCode(request.getVoucherCode());
		if (voucher.isPresent()) {
			Voucher vDb = voucher.get();
			Date now = new Date();
			if (!vDb.isUsed()) {
				response.setUsedDate(now);
				response.setDiscountPercentage(vDb.getSpecialOffer().getPercentDiscount());

				vDb.setUpdateDate(now);
				vDb.setUsedDate(now);
				vDb.setUsed(true);

				voucherRespository.saveAndFlush(vDb);
			}

			if(request.getEmail() != null){
				Recipient r = recipientRepository.findByEmail(request.getEmail());
				Predicate<Voucher> beforeExpiredUnusedVoucher = v -> !v.isUsed() && v.getExpiredDate().after(now);
				List<Voucher> validVouchers = r.getVouchers().stream().filter(beforeExpiredUnusedVoucher).collect(Collectors.toList());
				List<ValidVoucher> voucherList = new ArrayList<>();
				for(Voucher v: validVouchers){
					ValidVoucher validVoucher = new ValidVoucher();
					//v.setOfferName();
					validVoucher.setValidVoucherCode(v.getCode());
					validVoucher.setOfferName(v.getSpecialOffer().getName());
					voucherList.add(validVoucher);
				}
				response.setValidVoucherList(voucherList);
			}
		}

		return response;
	}


	private String generateVoucherCode() {
		//TODO temporary use UUID to generate unique voucher code, may replace with other algorithm in future
		return UUID.randomUUID().toString().replace("-", "");
	}


}
