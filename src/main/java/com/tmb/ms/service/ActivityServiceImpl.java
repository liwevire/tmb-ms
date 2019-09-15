package com.tmb.ms.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
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

	public ActivityResponse getbyId(CommonRequest request) {
		ActivityResponse activityResponse = new ActivityResponse();
		Optional<Activity> activityOp = null;
		try {
			activityOp = activityRepo.findById(request.getId());
		} catch (Exception e) {
			activityResponse.setStatusCode(MsConstant.DB_REPO_FAILURE_CODE);
			activityResponse.setStatusMessage(MsConstant.DB_REPO_FAILURE_MSG + ":" + e.getMessage());
		}
		if (activityOp != null && activityOp.isPresent()) {
			Activity activity = activityOp.get();
			activityResponse = mapper.map(activity, ActivityResponse.class);
			activityResponse.setStatusCode(MsConstant.SUCCESS_CODE);
			activityResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
		}else {
			activityResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
			activityResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
		}
		return activityResponse;
	}

}