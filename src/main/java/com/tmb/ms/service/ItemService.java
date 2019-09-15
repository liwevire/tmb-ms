package com.tmb.ms.service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.ItemResponse;

public interface ItemService {
	public ItemResponse getbyId(CommonRequest request);
}