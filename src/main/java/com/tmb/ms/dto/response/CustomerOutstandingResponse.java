package com.tmb.ms.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerOutstandingResponse {
	private long id;
	private String name;
	private String secondaryName;
	private int loanCounter;
	private LoanOutstandingResponse loanOutstandingResponse;
	public void addLoanCounter() {
		this.loanCounter++;
	}
}