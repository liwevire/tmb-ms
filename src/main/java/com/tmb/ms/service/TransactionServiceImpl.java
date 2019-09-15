package com.tmb.ms.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.TransactionResponse;
import com.tmb.ms.entity.Transaction;
import com.tmb.ms.repo.TransactionRepo;
import com.tmb.ms.util.MsConstant;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;
	private ModelMapper mapper = new ModelMapper();

	public TransactionResponse getbyId(CommonRequest request) {
		TransactionResponse transactionResponse = new TransactionResponse();
		Optional<Transaction> transactionOp = null;
		try {
			transactionOp = transactionRepo.findById(request.getId());
		} catch (Exception e) {
			transactionResponse.setStatusCode(MsConstant.DB_REPO_FAILURE_CODE);
			transactionResponse.setStatusMessage(MsConstant.DB_REPO_FAILURE_MSG + ":" + e.getMessage());
		}
		if (transactionOp != null && transactionOp.isPresent()) {
			Transaction transaction = transactionOp.get();
			transactionResponse = mapper.map(transaction, TransactionResponse.class);
			transactionResponse.setStatusCode(MsConstant.SUCCESS_CODE);
			transactionResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
		}else {
			transactionResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
			transactionResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
		}
		return transactionResponse;
	}

}