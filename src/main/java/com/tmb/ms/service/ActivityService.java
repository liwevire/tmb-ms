package com.tmb.ms.service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.ActivityResponse;

public interface ActivityService {
	public ActivityResponse getbyId(CommonRequest request);
	public ActivityResponse delete(long id);
}