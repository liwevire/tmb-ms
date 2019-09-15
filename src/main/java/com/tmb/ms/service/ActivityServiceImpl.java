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
import com.tmb.ms.dto.response.ActivityResponse;
import com.tmb.ms.entity.Activity;
import com.tmb.ms.repo.ActivityRepo;
import com.tmb.ms.util.MsConstant;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepo activityRepo;
	private ModelMapper mapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

	public ActivityResponse getbyId(CommonRequest request) {
		ActivityResponse activityResponse = new ActivityResponse();
		Optional<Activity> activityOp = null;
		try {
			activityOp = activityRepo.findById(request.getId());
			if (activityOp != null && activityOp.isPresent()) {
				Activity activity = activityOp.get();
				activityResponse = mapper.map(activity, ActivityResponse.class);
				activityResponse.setStatusCode(MsConstant.SUCCESS_CODE);
				activityResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
			} else {
				activityResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
				activityResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
			}
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			activityResponse.setStatusCode(MsConstant.VALIDATION_ERR_CODE);
			activityResponse.setStatusMessage(MsConstant.VALIDATION_ERR_MSG + ":" + iae.getMessage());
		} catch (ConfigurationException|MappingException ceme) {
			logger.error(ceme.getMessage(), ceme);
			activityResponse.setStatusCode(MsConstant.MAPPER_ERR_CODE);
			activityResponse.setStatusMessage(MsConstant.MAPPER_ERR_MSG + ":" + ceme.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			activityResponse.setStatusCode(MsConstant.UNKNOWN_ERR_CODE);
			activityResponse.setStatusMessage(MsConstant.UNKNOWN_ERR_MSG + ":" + e.getMessage());
		} finally {
			if (activityResponse.getStatusCode() < 300)
				logger.info(activityResponse.toString());
		}
		return activityResponse;
	}

}