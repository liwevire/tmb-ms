package com.tmb.ms.service;

import java.util.Optional;

import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

	public LoanResponse getbyId(CommonRequest request) {
		LoanResponse loanResponse = new LoanResponse();
		Optional<Loan> loanOp = null;
		try {
			loanOp = loanRepo.findById(request.getId());
			if (loanOp != null && loanOp.isPresent()) {
				Loan loan = loanOp.get();
				loanResponse = mapper.map(loan, LoanResponse.class);
				loanResponse.setStatusCode(MsConstant.SUCCESS_CODE);
				loanResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
			} else {
				loanResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
				loanResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
			}
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			loanResponse.setStatusCode(MsConstant.VALIDATION_ERR_CODE);
			loanResponse.setStatusMessage(MsConstant.VALIDATION_ERR_MSG + ":" + iae.getMessage());
		} catch (ConfigurationException ce) {
			logger.error(ce.getMessage(), ce);
			loanResponse.setStatusCode(MsConstant.MAPPER_CONFIG_ERR_CODE);
			loanResponse.setStatusMessage(MsConstant.MAPPER_CONFIG_ERR_MSG + ":" + ce.getMessage());
		} catch (MappingException me) {
			logger.error(me.getMessage(), me);
			loanResponse.setStatusCode(MsConstant.MAPPER_ERR_CODE);
			loanResponse.setStatusMessage(MsConstant.MAPPER_ERR_MSG + ":" + me.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			loanResponse.setStatusCode(MsConstant.UNKNOWN_ERR_CODE);
			loanResponse.setStatusMessage(MsConstant.UNKNOWN_ERR_MSG + ":" + e.getMessage());
		} finally {
			if (loanResponse.getStatusCode() < 300)
				logger.info(loanResponse.toString());
			else
				logger.error(loanResponse.toString());
		}
		return loanResponse;
	}

}