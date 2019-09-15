package com.tmb.ms.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.LoanResponse;
import com.tmb.ms.entity.Loan;
import com.tmb.ms.repo.LoanRepo;
import com.tmb.ms.util.MsConstant;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepo loanRepo;
	private ModelMapper mapper = new ModelMapper();

	public LoanResponse getbyId(CommonRequest request) {
		LoanResponse loanResponse = new LoanResponse();
		Loan loan = null;
		try {
			Optional<Loan> loanOp = loanRepo.findById(1L);
			if (loanOp.isPresent()) {
				loan = loanOp.get();
			} else {
				loanResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
				loanResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
			}
			System.out.println(loan.toString());
		} catch (Exception e) {
			loanResponse.setStatusCode(MsConstant.DB_REPO_FAILURE_CODE);
			loanResponse.setStatusMessage(MsConstant.DB_REPO_FAILURE_MSG + ":" + e.getMessage());
		}
//		try {
//			System.out.println("@##@#@#@");
//			System.out.println(loan.toString());
//			
//			loanResponse = mapper.map(loan, LoanResponse.class);
//			loanResponse.setStatusCode(MsConstant.SUCCESS_CODE);
//			loanResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		return loanResponse;
	}
}