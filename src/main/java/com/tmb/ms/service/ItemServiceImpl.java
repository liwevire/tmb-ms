package com.tmb.ms.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.ItemResponse;
import com.tmb.ms.entity.Item;
import com.tmb.ms.repo.ItemRepo;
import com.tmb.ms.util.MsConstant;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepo itemRepo;
	private ModelMapper mapper = new ModelMapper();

	public ItemResponse getbyId(CommonRequest request) {
		ItemResponse itemResponse = new ItemResponse();
		Optional<Item> itemOp = null;
		try {
			itemOp = itemRepo.findById(request.getId());
		} catch (Exception e) {
			itemResponse.setStatusCode(MsConstant.DB_REPO_FAILURE_CODE);
			itemResponse.setStatusMessage(MsConstant.DB_REPO_FAILURE_MSG + ":" + e.getMessage());
		}
		if (itemOp != null && itemOp.isPresent()) {
			Item item = itemOp.get();
			itemResponse = mapper.map(item, ItemResponse.class);
			itemResponse.setStatusCode(MsConstant.SUCCESS_CODE);
			itemResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
		}else {
			itemResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
			itemResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
		}
		return itemResponse;
	}

}