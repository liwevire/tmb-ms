package com.tmb.ms.service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.TransactionResponse;

public interface TransactionService {
	public TransactionResponse getbyId(CommonRequest request);
}