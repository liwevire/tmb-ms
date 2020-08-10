package com.tmb.ms.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoanOutstandingResponse extends CommonResponse{
	private double principal;
	private double principalPaid;
	private double principalOutstanding;
	private double interest;
	private double interestPaid;
	private double interestOutstanding;
	private int totalDays;
	private String calcComment;
}
