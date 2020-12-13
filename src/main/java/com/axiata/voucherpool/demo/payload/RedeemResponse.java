package com.axiata.voucherpool.demo.payload;

import com.axiata.voucherpool.demo.model.Voucher;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class RedeemResponse {

	private float discountPercentage;
	private Date usedDate;
	private List<ValidVoucher> validVoucherList = new ArrayList<>();


}
