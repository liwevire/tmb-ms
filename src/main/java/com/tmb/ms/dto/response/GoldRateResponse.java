package com.tmb.ms.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoldRateResponse extends CommonResponse{
	private long goldRate;
	private Date date = new Date();
}
