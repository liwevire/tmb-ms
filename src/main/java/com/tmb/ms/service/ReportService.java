package com.tmb.ms.service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.LoanOutstandingResponse;
import com.tmb.ms.dto.response.ReportOutstandingResponse;

public interface ReportService {
	public ReportOutstandingResponse getPrincipalOutstanding();
	public LoanOutstandingResponse calculateInterest(CommonRequest request);
}