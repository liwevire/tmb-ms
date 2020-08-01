package com.tmb.ms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.CustomerResponse;
import com.tmb.ms.entity.Customer;
import com.tmb.ms.entity.Loan;
import com.tmb.ms.repo.CustomerRepo;
import com.tmb.ms.repo.LoanRepo;
import com.tmb.ms.util.TmbMsErrorCode;
import com.tmb.ms.util.TmbMsException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private LoanRepo loanRepo;
	private ModelMapper mapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	public List<Customer> get() {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			customers = customerRepo.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return customers;
	}

	public CustomerResponse getbyId(CommonRequest request) {
		CustomerResponse customerResponse = new CustomerResponse();
		Customer customer = null;
		try {
			customer = customerRepo.findById(request.getId()).get();
			customerResponse = mapper.map(customer, CustomerResponse.class);
			customerResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
			logger.info(customerResponse.toString());
		} catch (NoSuchElementException nse) {
			customerResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage()+ ":" + nse.getMessage());
			logger.error(customerResponse.toString() + nse.getMessage(), nse);
		} catch (IllegalArgumentException iae) {
			customerResponse.setStatusCode(TmbMsErrorCode.VALIDATION_ERR.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.VALIDATION_ERR.getErrMessage() + ":" + iae.getMessage());
			logger.error(customerResponse.toString() + iae.getMessage(), iae);
		} catch (ConfigurationException | MappingException ceme) {
			customerResponse.setStatusCode(TmbMsErrorCode.MAPPER_ERR.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.MAPPER_ERR.getErrMessage() + ":" + ceme.getMessage());
			logger.error(customerResponse.toString() + ceme.getMessage(), ceme);
		} catch (Exception e) {
			customerResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(customerResponse.toString() + e.getMessage(), e);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse add(Customer customer) {
		CustomerResponse customerResponse = new CustomerResponse();
		try {
			customer = customerRepo.save(customer);
			customerResponse = mapper.map(customer, CustomerResponse.class);
			customerResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
			logger.info(customerResponse.toString());
		} catch (Exception e) {
			customerResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(customerResponse.toString() + e.getMessage(), e);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse update(Customer customer) {
		CustomerResponse customerResponse = new CustomerResponse();
		Customer customerEntity;
		try {
			customerEntity = customerRepo.save(customer);
			customerResponse = mapper.map(customerEntity, CustomerResponse.class);
			customerResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
			logger.info(customerResponse.toString());
		} catch (NoSuchElementException nse) {
			customerResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + ":" + nse.getMessage());
			logger.error(customerResponse.toString() + nse.getMessage(), nse);
		} catch (IllegalArgumentException iae) {
			customerResponse.setStatusCode(TmbMsErrorCode.VALIDATION_ERR.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.VALIDATION_ERR.getErrMessage() + ":" + iae.getMessage());
			logger.error(customerResponse.toString() + iae.getMessage(), iae);
		} catch (ConfigurationException | MappingException ceme) {
			customerResponse.setStatusCode(TmbMsErrorCode.MAPPER_ERR.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.MAPPER_ERR.getErrMessage() + ":" + ceme.getMessage());
			logger.error(customerResponse.toString() + ceme.getMessage(), ceme);
		} catch (Exception e) {
			customerResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(customerResponse.toString() + e.getMessage(), e);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse delete(long id) {
		CustomerResponse customerResponse = new CustomerResponse();
		Customer customerEntity;
		try {
			customerEntity = customerRepo.findById(id).get();
			List<Loan> loans = loanRepo.findByCustomer(customerEntity);
			if (loans.isEmpty())
				customerRepo.delete(customerEntity);
			else {
				 throw new TmbMsException(TmbMsErrorCode.DB_CONSTRIANT, "First delete the loans of this customer. Loans IDs are"
						 + loans.stream().map(loan -> loan.getId()).collect(Collectors.toList()).toString());
			}
			customerResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
		} catch (NoSuchElementException nse) {
			customerResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + ":" + nse.getMessage());
			logger.error(customerResponse.toString() + nse.getMessage(), nse);
		} catch (IllegalArgumentException iae) {
			customerResponse.setStatusCode(TmbMsErrorCode.VALIDATION_ERR.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.VALIDATION_ERR.getErrMessage() + ":" + iae.getMessage());
			logger.error(customerResponse.toString() + iae.getMessage(), iae);
		} catch (EmptyResultDataAccessException erdae) {
			customerResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + ":" + erdae.getMessage());
			logger.error(customerResponse.toString() + erdae.getMessage(), erdae);
		} catch (TmbMsException tme) {
			customerResponse.setStatusCode(tme.getErrCode().getErrCode());
			customerResponse.setStatusMessage(tme.getErrMessage());
		} catch (Exception e) {
			customerResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			customerResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(customerResponse.toString() + e.getMessage(), e);
		}
		return customerResponse;
	}
}