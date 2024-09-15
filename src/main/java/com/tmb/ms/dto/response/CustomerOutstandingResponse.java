package com.tmb.ms.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerOutstandingResponse extends CommonResponse{
	private long id;
	private String name;
	private String secondaryName;
	private LoanOutstandingResponse loanOutstandingResponse;
}