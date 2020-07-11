package com.tmb.ms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.CustomerResponse;
import com.tmb.ms.entity.Customer;
import com.tmb.ms.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);

	
	@GetMapping("/customer/get")
	private Flux<Customer> get() {
		List<Customer> customers = customerService.get();
		logger.info(customers.toString());
		return customers;
	}
	
//	@GetMapping("/customer/get")
//	private List<Customer> get(HttpServletRequest request) {
//		logger.info(request.getRequestURI());
//		List<Customer> customers = customerService.get();
//		logger.info(customers.toString());
//		return customers;
//	}
//	
//	@PostMapping("/customer/getById")
//	private CustomerResponse get(@RequestBody CommonRequest request) {
//		logger.info(request.toString());
//		CustomerResponse customerResponse = customerService.getbyId(request);
//		logger.info(customerResponse.toString());
//		return customerResponse;
//	}
//	
//	@PostMapping("/customer/update")
//	private CustomerResponse add(@RequestBody Customer customer) {
//		logger.info(customer.toString());
//		CustomerResponse customerResponse = customerService.add(customer);
//		logger.info(customerResponse.toString());
//		return customerResponse;
//	}
//	
//	@PutMapping("/customer/update")
//	private CustomerResponse update(@RequestBody Customer customer) {
//		logger.info(customer.toString());
//		CustomerResponse customerResponse = customerService.update(customer);
//		logger.info(customerResponse.toString());
//		return customerResponse;
//	}
//	
//	@DeleteMapping("/customer/delete")
//	private CustomerResponse delete(@RequestParam long id) {
//		logger.info(Long.toString(id));
//		CustomerResponse customerResponse = customerService.delete(id);
//		logger.info(customerResponse.toString());
//		return customerResponse;
//	}
}
