package com.tmb.ms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.ItemResponse;
import com.tmb.ms.service.ItemService;

@RestController
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	private Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@PostMapping("/item/getById")
	private ItemResponse get(@RequestBody CommonRequest request) {
		logger.info(request.toString());
		ItemResponse itemResponse = itemService.getbyId(request);
		logger.info(itemResponse.toString());
		return itemResponse;
	}
}
