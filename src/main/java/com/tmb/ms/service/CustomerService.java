package com.tmb.ms.service;


import java.util.List;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.CustomerResponse;
import com.tmb.ms.entity.Customer;

public interface CustomerService {
	public List<Customer> get();
	public CustomerResponse getbyId(CommonRequest request);
	public CustomerResponse add(Customer customer);
	public CustomerResponse update(Customer customer);
}