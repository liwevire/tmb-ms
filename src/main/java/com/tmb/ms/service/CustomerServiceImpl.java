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
		Optional<Customer> customerOp = null;
		try {
			customerOp = customerRepo.findById(request.getId());
			if (customerOp != null && customerOp.isPresent()) {
				Customer customer = customerOp.get();
				customerResponse = mapper.map(customer, CustomerResponse.class);
				customerResponse.setStatusCode(MsConstant.SUCCESS_CODE);
				customerResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
			} else {
				customerResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
				customerResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
			}
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			customerResponse.setStatusCode(MsConstant.VALIDATION_ERR_CODE);
			customerResponse.setStatusMessage(MsConstant.VALIDATION_ERR_MSG + ":" + iae.getMessage());
		} catch (ConfigurationException|MappingException ceme) {
			logger.error(ceme.getMessage(), ceme);
			customerResponse.setStatusCode(MsConstant.MAPPER_ERR_CODE);
			customerResponse.setStatusMessage(MsConstant.MAPPER_ERR_MSG + ":" + ceme.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			customerResponse.setStatusCode(MsConstant.UNKNOWN_ERR_CODE);
			customerResponse.setStatusMessage(MsConstant.UNKNOWN_ERR_MSG + ":" + e.getMessage());
		} finally {
			if (customerResponse.getStatusCode() < 300)
				logger.info(customerResponse.toString());
		}
		return customerResponse;
	}

}