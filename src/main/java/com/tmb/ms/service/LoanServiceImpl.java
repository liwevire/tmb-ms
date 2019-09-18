package com.tmb.ms.service;

import java.util.NoSuchElementException;

import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.LoanResponse;
import com.tmb.ms.entity.Customer;
import com.tmb.ms.entity.Loan;
import com.tmb.ms.repo.CustomerRepo;
import com.tmb.ms.repo.LoanRepo;
import com.tmb.ms.util.MsConstant;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepo loanRepo;
	@Autowired
	private CustomerRepo customerRepo;
	private ModelMapper mapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

	public LoanResponse getbyId(CommonRequest request) {
		LoanResponse loanResponse = new LoanResponse();
		Loan loan = null;
		try {
			loan = loanRepo.findById(request.getId()).get();
			loanResponse = mapper.map(loan, LoanResponse.class);
			loanResponse.setStatusCode(MsConstant.SUCCESS_CODE);
			loanResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
			logger.info(loanResponse.toString());
		} catch (NoSuchElementException nse) {
			loanResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
			loanResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG + ":" + nse.getMessage());
			logger.error(loanResponse.toString() + nse.getMessage(), nse);
		} catch (IllegalArgumentException iae) {
			loanResponse.setStatusCode(MsConstant.VALIDATION_ERR_CODE);
			loanResponse.setStatusMessage(MsConstant.VALIDATION_ERR_MSG + ":" + iae.getMessage());
			logger.error(loanResponse.toString() + iae.getMessage(), iae);
		} catch (ConfigurationException | MappingException ceme) {
			loanResponse.setStatusCode(MsConstant.MAPPER_ERR_CODE);
			loanResponse.setStatusMessage(MsConstant.MAPPER_ERR_MSG + ":" + ceme.getMessage());
			logger.error(loanResponse.toString() + ceme.getMessage(), ceme);
		} catch (Exception e) {
			loanResponse.setStatusCode(MsConstant.UNKNOWN_ERR_CODE);
			loanResponse.setStatusMessage(MsConstant.UNKNOWN_ERR_MSG + ":" + e.getMessage());
			logger.error(loanResponse.toString() + e.getMessage(), e);
		}
		return loanResponse;
	}

	@Override
	public LoanResponse addLoan(Loan loan) {
		LoanResponse loanResponse = new LoanResponse();
		try {
			if (loan.getCustomer() != null && loan.getCustomer().getId() != 0) {
				Customer customer = (customerRepo.findById(loan.getCustomer().getId()).get());
				customer.setAddress(loan.getCustomer().getAddress());
				customer.setName(loan.getCustomer().getName());
				customer.setPhone(loan.getCustomer().getPhone());
				customer.setPin(loan.getCustomer().getPin());
				customer.setPost(loan.getCustomer().getPost());
				customer.setSecondaryName(loan.getCustomer().getSecondaryName());
			}
			loan = loanRepo.save(loan);
			loanResponse = mapper.map(loan, LoanResponse.class);
			loanResponse.setStatusCode(MsConstant.SUCCESS_CODE);
			loanResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
			logger.info(loanResponse.toString());
		} catch (NoSuchElementException nse) {
			loanResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
			loanResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
			logger.error(loanResponse.toString() + nse.getMessage(), nse);
		} catch (Exception e) {
			loanResponse.setStatusCode(MsConstant.UNKNOWN_ERR_CODE);
			loanResponse.setStatusMessage(MsConstant.UNKNOWN_ERR_MSG + ":" + e.getMessage());
			logger.error(loanResponse.toString() + e.getMessage(), e);
		}
		return loanResponse;
	}

	@Override
	public LoanResponse updateLoan(Loan loan) {
		LoanResponse loanResponse = new LoanResponse();
		try {
			if (loan.getCustomer() != null && loan.getCustomer().getId() != 0) {
				Customer customer = (customerRepo.findById(loan.getCustomer().getId()).get());
				customer.setAddress(loan.getCustomer().getAddress());
				customer.setName(loan.getCustomer().getName());
				customer.setPhone(loan.getCustomer().getPhone());
				customer.setPin(loan.getCustomer().getPin());
				customer.setPost(loan.getCustomer().getPost());
				customer.setSecondaryName(loan.getCustomer().getSecondaryName());
			}
			loan = loanRepo.save(loan);
			loanResponse = mapper.map(loan, LoanResponse.class);
			loanResponse.setStatusCode(MsConstant.SUCCESS_CODE);
			loanResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
			logger.info(loanResponse.toString());
		} catch (NoSuchElementException nse) {
			loanResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
			loanResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
			logger.error(loanResponse.toString() + nse.getMessage(), nse);
		} catch (Exception e) {
			loanResponse.setStatusCode(MsConstant.UNKNOWN_ERR_CODE);
			loanResponse.setStatusMessage(MsConstant.UNKNOWN_ERR_MSG + ":" + e.getMessage());
			logger.error(loanResponse.toString() + e.getMessage(), e);
		}
		return loanResponse;
	}

}