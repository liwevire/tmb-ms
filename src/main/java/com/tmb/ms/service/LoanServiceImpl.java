package com.tmb.ms.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.LoanResponse;
import com.tmb.ms.entity.Activity;
import com.tmb.ms.entity.Customer;
import com.tmb.ms.entity.Item;
import com.tmb.ms.entity.Loan;
import com.tmb.ms.repo.ActivityRepo;
import com.tmb.ms.repo.CustomerRepo;
import com.tmb.ms.repo.ItemRepo;
import com.tmb.ms.repo.LoanRepo;
import com.tmb.ms.util.LoanUtil;
import com.tmb.ms.util.MsConstant;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepo loanRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private ItemRepo itemRepo;
	@Autowired
	private ActivityRepo activityRepo;
	@Autowired
	private LoanUtil loanUtil;
	private ModelMapper mapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

	@Override
	public List<Loan> get() {
		List<Loan> loans = new ArrayList<Loan>();
		try {
			loans = loanRepo.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return loans;
	}

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
				customer.assign(loan.getCustomer());
				loan.setCustomer(customer);
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
			// prepare customer
			if (loan.getCustomer() != null && loan.getCustomer().getId() != 0) {
				Customer customer = (customerRepo.findById(loan.getCustomer().getId()).get());
				customer.assign(loan.getCustomer());
				loan.setCustomer(customer);
			}
			// prepare items
			Set<Item> exstItems = itemRepo.findByLoanId(loan.getId());
			loan.setItems(loanUtil.prepareItems(exstItems, loan.getItems(), loan.getId()));
			//prepare activities
			Set<Activity> exstActivities = activityRepo.findByLoanId(loan.getId());
			loan.setActivities(loanUtil.prepareActivities(exstActivities, loan.getActivities(), loan.getId()));

			//db interactions
			itemRepo.deleteAll(exstItems);
			activityRepo.deleteAll(exstActivities);
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