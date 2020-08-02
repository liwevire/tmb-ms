package com.tmb.ms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.CommonResponse;
import com.tmb.ms.service.KycService;

@RestController
public class KycController {
	@Autowired
	private KycService kycService;
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@PostMapping("/kyc/customerphoto/getById")
	private CommonResponse get(@RequestBody CommonRequest request) {
		logger.info(request.toString());
		CommonResponse commonResponse = null;
		logger.info(commonResponse.toString());
		return commonResponse;
	}

	@PostMapping("/kyc/customerphoto/update")
	private CommonResponse add(@RequestBody CommonRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			commonResponse = kycService.update(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		logger.info(commonResponse.toString());
		return commonResponse;
	}

	@DeleteMapping("/kyc/customerphoto/delete")
	private CommonResponse delete(@RequestParam long id) {
		logger.info(Long.toString(id));
		CommonResponse commonResponse = null;
		logger.info(commonResponse.toString());
		return commonResponse;
	}
}
