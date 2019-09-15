package com.tmb.ms.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse extends CommonResponse {
	private long id;
	private long loanId;
	private Date date = new Date();
	private int category;
	private double amount;
}
