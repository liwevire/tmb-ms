package com.tmb.ms.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmb.ms.dto.response.GoldRateResponse;
import com.tmb.ms.service.scrape.ScraperService;

@RestController
public class GoldRateController {
	@Autowired
	private ScraperService scraperService;
	private Logger logger = LoggerFactory.getLogger(GoldRateController.class);

	@GetMapping("/goldrate")
	private GoldRateResponse getReport(HttpServletRequest request) {
		logger.info(request.getRequestURI());
		GoldRateResponse goldRateResponse = scraperService.getGoldRate();
		logger.info(goldRateResponse.toString());
		return goldRateResponse;
	}

}
