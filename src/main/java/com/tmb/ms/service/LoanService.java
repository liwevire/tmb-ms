package com.tmb.ms.service;

import java.util.List;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.LoanResponse;
import com.tmb.ms.entity.Loan;

public interface LoanService {
	public List<Loan> get();
	public LoanResponse getbyId(CommonRequest request);
	public List<Loan> getbyCustId(CommonRequest request);
	public LoanResponse add(Loan loan);
	public LoanResponse update(Loan loan);
	public LoanResponse delete(long id);
}