package com.tmb.ms.service;

import java.util.List;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.request.DatedReportRequest;
import com.tmb.ms.dto.response.LoanOutstandingResponse;
import com.tmb.ms.dto.response.ReportOutstandingResponse;
import com.tmb.ms.dto.response.ValuationResponse;
import com.tmb.ms.entity.Customer;

public interface ReportService {
	public ReportOutstandingResponse getInceptionOutstanding();
	public ReportOutstandingResponse getDatedOutstanding(DatedReportRequest request);
	public LoanOutstandingResponse calculateInterest(CommonRequest request);
	public ValuationResponse getValuationReport();
	public List<Customer> getCustomerByOpenValue();
}