package com.tmb.ms.service;

import org.springframework.core.io.Resource;

import com.tmb.ms.dto.request.KycRequest;
import com.tmb.ms.dto.response.CommonResponse;

public interface KycService {
	public Resource getCustomerPhoto(long id);
	public CommonResponse updateCustomerPhoto(KycRequest request);
}