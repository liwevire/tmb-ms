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
	private int valuation;
	private int valuationPrcnt;
	
	public LoanOutstandingResponse addOutstanding (LoanOutstandingResponse lor) {
		this.principal+= lor.principal;
		this.principalPaid+= lor.principalPaid;
		this.principalOutstanding+= lor.principalOutstanding;
		this.interest+= lor.interest;
		this.interestPaid+= lor.interestPaid;
		this.interestOutstanding+= lor.interestOutstanding;
		this.totalDays+= lor.totalDays;
		this.valuation+= lor.valuation;
		return this;
	}
	
	public int calculateValuationPrcnt() {
		this.valuationPrcnt = (int)(100 - Math.round(((this.principalOutstanding+ this.interestOutstanding)*100)/this.valuation));
		return this.valuationPrcnt;
	}
}