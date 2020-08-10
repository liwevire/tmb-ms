package com.tmb.ms.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoanOutstandingResponse extends CommonResponse{
	private long principal;
	private long outstandingPrincipal;
	private long interest;
	private long outstandingInterest;
}
