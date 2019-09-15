package com.tmb.ms.dto.response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	private List<Item> items = new ArrayList<Item>();
	private List<Activity> activities = new ArrayList<Activity>();
}