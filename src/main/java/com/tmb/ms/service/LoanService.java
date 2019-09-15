package com.tmb.ms.service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.LoanResponse;

public interface LoanService {
	public LoanResponse getbyId(CommonRequest request);
}