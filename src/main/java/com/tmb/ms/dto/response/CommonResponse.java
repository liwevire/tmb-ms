package com.tmb.ms.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonResponse {
	private int statusCode;
	private String statusMessage;
}