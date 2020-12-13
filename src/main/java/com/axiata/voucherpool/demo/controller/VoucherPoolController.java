package com.axiata.voucherpool.demo.controller;

import com.axiata.voucherpool.demo.model.Voucher;
import com.axiata.voucherpool.demo.payload.RedeemRequest;
import com.axiata.voucherpool.demo.payload.RedeemResponse;
import com.axiata.voucherpool.demo.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoucherPoolController {

	@Autowired
	VoucherService voucherService;

	@PostMapping("/vouchers/redeem")
	public ResponseEntity<RedeemResponse> redeem(@RequestBody RedeemRequest request) {
		return ResponseEntity.ok().body(voucherService.redeem(request));
	}

	@GetMapping("/vouchers")
	public ResponseEntity<List<Voucher>> getAllProduct(){
		return ResponseEntity.ok().body(voucherService.getAll());
	}



}
