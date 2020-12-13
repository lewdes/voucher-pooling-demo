package com.axiata.voucherpool.demo.payload;

import com.axiata.voucherpool.demo.model.Voucher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RedeemRequest {

	private String voucherCode;
	private String email;
}
