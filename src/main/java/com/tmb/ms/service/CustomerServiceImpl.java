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
import com.tmb.ms.dto.response.CustomerResponse;
import com.tmb.ms.entity.Customer;
import com.tmb.ms.repo.CustomerRepo;
import com.tmb.ms.util.MsConstant;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	private ModelMapper mapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	public CustomerResponse getbyId(CommonRequest request) {
		CustomerResponse customerResponse = new CustomerResponse();
		Customer customer = null;
		try {
			customer = customerRepo.findById(request.getId()).get();
			customerResponse = mapper.map(customer, CustomerResponse.class);
			customerResponse.setStatusCode(MsConstant.SUCCESS_CODE);
			customerResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
			logger.info(customerResponse.toString());
		} catch (NoSuchElementException nse) {
			customerResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
			customerResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
			logger.error(customerResponse.toString() + nse.getMessage(), nse);
		} catch (IllegalArgumentException iae) {
			customerResponse.setStatusCode(MsConstant.VALIDATION_ERR_CODE);
			customerResponse.setStatusMessage(MsConstant.VALIDATION_ERR_MSG + ":" + iae.getMessage());
			logger.error(customerResponse.toString() + iae.getMessage(), iae);
		} catch (ConfigurationException | MappingException ceme) {
			customerResponse.setStatusCode(MsConstant.MAPPER_ERR_CODE);
			customerResponse.setStatusMessage(MsConstant.MAPPER_ERR_MSG + ":" + ceme.getMessage());
			logger.error(customerResponse.toString() + ceme.getMessage(), ceme);
		} catch (Exception e) {
			customerResponse.setStatusCode(MsConstant.UNKNOWN_ERR_CODE);
			customerResponse.setStatusMessage(MsConstant.UNKNOWN_ERR_MSG + ":" + e.getMessage());
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
			customerResponse.setStatusCode(MsConstant.SUCCESS_CODE);
			customerResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
			logger.info(customerResponse.toString());
		} catch (Exception e) {
			customerResponse.setStatusCode(MsConstant.UNKNOWN_ERR_CODE);
			customerResponse.setStatusMessage(MsConstant.UNKNOWN_ERR_MSG + ":" + e.getMessage());
			logger.error(customerResponse.toString() + e.getMessage(), e);
		}
		return customerResponse;
	}

	@Override
	public CustomerResponse update(Customer customer) {
		CustomerResponse customerResponse = new CustomerResponse();
		try {
			customer = customerRepo.save(customer);
			customerResponse = mapper.map(customer, CustomerResponse.class);
			customerResponse.setStatusCode(MsConstant.SUCCESS_CODE);
			customerResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
			logger.info(customerResponse.toString());
		} catch (Exception e) {
			customerResponse.setStatusCode(MsConstant.UNKNOWN_ERR_CODE);
			customerResponse.setStatusMessage(MsConstant.UNKNOWN_ERR_MSG + ":" + e.getMessage());
			logger.error(customerResponse.toString() + e.getMessage(), e);
		}
		return customerResponse;
	}
}