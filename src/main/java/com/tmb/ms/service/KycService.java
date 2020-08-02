package com.tmb.ms.service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.CommonResponse;

public interface KycService {
	public CommonResponse getbyId(CommonRequest request);
	public CommonResponse update(CommonRequest request);
	public CommonResponse delete(long id);
}