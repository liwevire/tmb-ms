package com.tmb.ms.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemResponse extends CommonResponse {
	private long id;
	private long loanId;
	private String name;
	private String quantity;
}
