package com.tmb.ms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.TransactionResponse;
import com.tmb.ms.service.TransactionService;

@RestController
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	private Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@PostMapping("/transaction/getById")
	private TransactionResponse get(@RequestBody CommonRequest request) {
		logger.info(request.toString());
		TransactionResponse transactionResponse = transactionService.getbyId(request);
		logger.info(transactionResponse.toString());
		return transactionResponse;
	}
}
