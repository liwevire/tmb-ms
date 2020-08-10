package com.tmb.ms.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.LoanOutstandingResponse;
import com.tmb.ms.dto.response.ReportOutstandingResponse;
import com.tmb.ms.service.ReportService;

@RestController
public class ReportController {
	@Autowired
	private ReportService reportService;

	private Logger logger = LoggerFactory.getLogger(ReportController.class);

	@GetMapping("/report/get")
	private ReportOutstandingResponse get(HttpServletRequest request) {
		logger.info(request.getRequestURI());
		ReportOutstandingResponse outstandingResponse = reportService.getPrincipalOutstanding();
		logger.info(outstandingResponse.toString());
		return outstandingResponse;
	}

	@PostMapping("/report/getInterestById")
	private LoanOutstandingResponse get(@RequestBody CommonRequest request) {
		logger.info(request.toString());
		LoanOutstandingResponse outstandingResponse = reportService.calculateInterest(request);
		logger.info(outstandingResponse.toString());
		return outstandingResponse;
	}
}
