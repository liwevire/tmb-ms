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
import com.tmb.ms.dto.response.ItemResponse;
import com.tmb.ms.entity.Item;
import com.tmb.ms.repo.ItemRepo;
import com.tmb.ms.util.MsConstant;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepo itemRepo;
	private ModelMapper mapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

	public ItemResponse getbyId(CommonRequest request) {
		ItemResponse itemResponse = new ItemResponse();
		Optional<Item> itemOp = null;
		try {
			itemOp = itemRepo.findById(request.getId());
			if (itemOp != null && itemOp.isPresent()) {
				Item item = itemOp.get();
				itemResponse = mapper.map(item, ItemResponse.class);
				itemResponse.setStatusCode(MsConstant.SUCCESS_CODE);
				itemResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
			} else {
				itemResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
				itemResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG);
			}
		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage(), iae);
			itemResponse.setStatusCode(MsConstant.VALIDATION_ERR_CODE);
			itemResponse.setStatusMessage(MsConstant.VALIDATION_ERR_MSG + ":" + iae.getMessage());
		} catch (ConfigurationException|MappingException ceme) {
			logger.error(ceme.getMessage(), ceme);
			itemResponse.setStatusCode(MsConstant.MAPPER_ERR_CODE);
			itemResponse.setStatusMessage(MsConstant.MAPPER_ERR_MSG + ":" + ceme.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			itemResponse.setStatusCode(MsConstant.UNKNOWN_ERR_CODE);
			itemResponse.setStatusMessage(MsConstant.UNKNOWN_ERR_MSG + ":" + e.getMessage());
		} finally {
			if (itemResponse.getStatusCode() < 300)
				logger.info(itemResponse.toString());
		}
		return itemResponse;
	}

}