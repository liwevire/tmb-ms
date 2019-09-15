package com.tmb.ms.service;


import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.CustomerResponse;

public interface CustomerService {
	public CustomerResponse getbyId(CommonRequest request);
}