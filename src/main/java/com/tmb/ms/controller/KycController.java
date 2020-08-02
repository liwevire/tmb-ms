package com.tmb.ms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmb.ms.dto.request.KycRequest;
import com.tmb.ms.dto.response.CommonResponse;
import com.tmb.ms.service.KycService;

@RestController
public class KycController {
	@Autowired
	private KycService kycService;
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@GetMapping(value="/kyc/customerphoto/getById", produces = MediaType.IMAGE_JPEG_VALUE)
	private ResponseEntity<Resource> getCustomerPhoto(@RequestParam long id) {
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + kycService.getCustomerPhoto(id).getFilename() + "\"")
				.body(kycService.getCustomerPhoto(id));
	}

	@PostMapping("/kyc/customerphoto/update")
	private CommonResponse updateCustomerPhoto(@RequestBody KycRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			commonResponse = kycService.updateCustomerPhoto(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		logger.info(commonResponse.toString());
		return commonResponse;
	}

}
