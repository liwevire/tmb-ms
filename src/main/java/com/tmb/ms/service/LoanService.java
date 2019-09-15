package com.tmb.ms.service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.LoanResponse;
import com.tmb.ms.entity.Loan;

public interface LoanService {
	public LoanResponse getbyId(CommonRequest request);
	public LoanResponse addLoan(Loan loan);
}