package com.tmb.ms.dto.response;

import java.util.HashSet;
import java.util.Set;

import com.tmb.ms.entity.Customer;
import com.tmb.ms.entity.Item;
import com.tmb.ms.entity.Activity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoanResponse extends CommonResponse {
	private long id;
	private Customer customer;
	private String status;
	private String weight;
	private String comment;
	private Set<Item> items = new HashSet<Item>();
	private Set<Activity> activities = new HashSet<Activity>();
}