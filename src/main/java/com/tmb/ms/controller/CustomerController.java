package com.tmb.ms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmb.ms.dto.Customer;
import com.tmb.ms.repo.CustomerRepo;

@RestController
public class CustomerController {
	@Autowired
	CustomerRepo customerRepo;
	
	@PostMapping
	private void getById() {
		System.out.println("get");
		System.out.println(new Date());
		Customer customer = new Customer();
		customer.setName("Prem");
		customer.setDate(new Date());
		try {
			customerRepo.save(customer);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}