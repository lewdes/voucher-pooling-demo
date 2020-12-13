package com.axiata.voucherpool.demo.payload;

import com.axiata.voucherpool.demo.model.Voucher;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidVoucher {
	private String validVoucherCode;
	private String offerName;
}
