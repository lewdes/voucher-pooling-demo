package com.axiata.voucherpool.demo;

import com.axiata.voucherpool.demo.model.Recipient;
import com.axiata.voucherpool.demo.model.SpecialOffer;
import com.axiata.voucherpool.demo.repository.RecipientRepository;
import com.axiata.voucherpool.demo.repository.SpecialOfferRepository;
import com.axiata.voucherpool.demo.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//This class is for simulating purpose, to init all neccessary entities to test voucher pooling
@Component
public class InitVoucherPool {
	@Autowired
	RecipientRepository recipientRepository;

	@Autowired
	SpecialOfferRepository  specialOfferRepository;

	@Autowired
	VoucherService voucherService;

	@PostConstruct
	public void initRecipient(){
		//simulate get recipient from system and assign to an entity "Recipient"
		//Recipient
		Recipient recipient = new Recipient();
		recipient.setName("Ali");
		recipient.setEmail("ali@axiata.com");
		recipientRepository.save(recipient);


		Recipient recipient2 = new Recipient();
		recipient2.setName("Baker");
		recipient2.setEmail("baker@axiata.com");
		recipientRepository.save(recipient2);

		//Special Offer
		SpecialOffer offer = new SpecialOffer();
		offer.setName("Special Christmas Offer");
		offer.setPercentDiscount(0.2f);
		specialOfferRepository.save(offer);

		SpecialOffer offer2 = new SpecialOffer();
		offer2.setName("Big Year End Offer");
		offer2.setPercentDiscount(0.3f);
		specialOfferRepository.save(offer2);

		//Voucher
		voucherService.generateVoucher(offer);
		voucherService.generateVoucher(offer2);


	}
}
