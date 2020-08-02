package com.tmb.ms.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KycRequest extends CommonRequest{
	private String data;
}
