package com.tmb.ms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.ActivityResponse;
import com.tmb.ms.service.ActivityService;

@RestController
public class ActivityController {
	@Autowired
	private ActivityService activityService;
	private Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
	@PostMapping("/activity/getById")
	private ActivityResponse get(@RequestBody CommonRequest request) {
		logger.info(request.toString());
		ActivityResponse activityResponse = activityService.getbyId(request);
		logger.info(activityResponse.toString());
		return activityResponse;
	}
}
