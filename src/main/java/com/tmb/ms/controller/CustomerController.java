package com.tmb.ms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.CustomerResponse;
import com.tmb.ms.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@PostMapping("/customer/getById")
	private CustomerResponse get(@RequestBody CommonRequest request) {
		logger.info(request.toString());
		CustomerResponse customerResponse = customerService.getbyId(request);
		logger.info(customerResponse.toString());
		return customerResponse;
	}
}
