package com.tmb.ms.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
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

	public CustomerResponse getbyId(CommonRequest request) {
		CustomerResponse customerResponse = new CustomerResponse();
		Optional<Customer> customerOp = null;
		try {
			customerOp = customerRepo.findById(request.getId());
		} catch (Exception e) {
			customerResponse.setStatusCode(MsConstant.DB_REPO_FAILURE_CODE);
			customerResponse.setStatusMessage(MsConstant.DB_REPO_FAILURE_MSG + ":" + e.getMessage());
		}
		if (customerOp != null && customerOp.isPresent()) {
			Customer customer = customerOp.get();
			customerResponse = mapper.map(customer, CustomerResponse.class);
			customerResponse.setStatusCode(MsConstant.SUCCESS_CODE);
			customerResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
		}else {
			customerResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
			customerResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
		}
		return customerResponse;
	}

}