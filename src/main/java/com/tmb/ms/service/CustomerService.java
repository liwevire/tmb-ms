package com.tmb.ms.service;


import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.CustomerResponse;
import com.tmb.ms.entity.Customer;

public interface CustomerService {
	public CustomerResponse getbyId(CommonRequest request);
	public CustomerResponse add(Customer customer);
	public CustomerResponse update(Customer customer);
}