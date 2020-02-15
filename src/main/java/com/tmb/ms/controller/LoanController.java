package com.tmb.ms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.CommonResponse;
import com.tmb.ms.dto.response.LoanResponse;
import com.tmb.ms.entity.Loan;
import com.tmb.ms.service.LoanService;

@RestController
public class LoanController {
	@Autowired
	private LoanService loanService;

	private Logger logger = LoggerFactory.getLogger(LoanController.class);

	@GetMapping("/loan/get")
	private List<Loan> get(HttpServletRequest request) {
		logger.info(request.getRequestURI());
		List<Loan> loans = loanService.get();
		logger.info(loans.toString());
		return loans;
	}

	@PostMapping("/loan/getById")
	private LoanResponse get(@RequestBody CommonRequest request) {
		logger.info(request.toString());
		LoanResponse loanResponse = loanService.getbyId(request);
		logger.info(loanResponse.toString());
		return loanResponse;
	}

	@PostMapping("/loan/add")
	private CommonResponse add(@RequestBody Loan loan) {
		logger.info(loan.toString());
		LoanResponse loanResponse = loanService.addLoan(loan);
		logger.info(loanResponse.toString());
		return loanResponse;
	}

	@PutMapping("/loan/update")
	private CommonResponse update(@RequestBody Loan loan) {
		logger.info(loan.toString());
		LoanResponse loanResponse = loanService.updateLoan(loan);
		logger.info(loanResponse.toString());
		return loanResponse;
	}
}
