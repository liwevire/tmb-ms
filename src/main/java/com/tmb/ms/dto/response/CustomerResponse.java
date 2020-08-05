package com.tmb.ms.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerResponse extends CommonResponse {
	private long id;
	private String name;
	private String secondaryName;
	private Date date = new Date();
	private String address;
	private String post;
	private String pin;
	private String phone;
	private String comment;
}