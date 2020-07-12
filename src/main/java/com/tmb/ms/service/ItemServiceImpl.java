package com.tmb.ms.service;

import java.util.NoSuchElementException;

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
		Item item = null;
		try {
			item = itemRepo.findById(request.getId()).get();
			itemResponse = mapper.map(item, ItemResponse.class);
			itemResponse.setStatusCode(MsConstant.SUCCESS_CODE);
			itemResponse.setStatusMessage(MsConstant.SUCCESS_MSG);
			logger.info(itemResponse.toString());
		} catch (NoSuchElementException nse) {
			itemResponse.setStatusCode(MsConstant.DB_NO_RECORD_CODE);
			itemResponse.setStatusMessage(MsConstant.DB_NO_RECORD_MSG + ":" + nse.getMessage());
			logger.error(itemResponse.toString() + nse.getMessage(), nse);
		} catch (IllegalArgumentException iae) {
			itemResponse.setStatusCode(MsConstant.VALIDATION_ERR_CODE);
			itemResponse.setStatusMessage(MsConstant.VALIDATION_ERR_MSG + ":" + iae.getMessage());
			logger.error(itemResponse.toString() + iae.getMessage(), iae);
		} catch (ConfigurationException | MappingException ceme) {
			itemResponse.setStatusCode(MsConstant.MAPPER_ERR_CODE);
			itemResponse.setStatusMessage(MsConstant.MAPPER_ERR_MSG + ":" + ceme.getMessage());
			logger.error(itemResponse.toString() + ceme.getMessage(), ceme);
		} catch (Exception e) {
			itemResponse.setStatusCode(MsConstant.UNKNOWN_ERR_CODE);
			itemResponse.setStatusMessage(MsConstant.UNKNOWN_ERR_MSG + ":" + e.getMessage());
			logger.error(itemResponse.toString() + e.getMessage(), e);
		}
		return itemResponse;
	}

}