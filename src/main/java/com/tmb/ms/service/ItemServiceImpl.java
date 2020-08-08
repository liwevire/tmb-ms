package com.tmb.ms.service;

import java.util.NoSuchElementException;

import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.ItemResponse;
import com.tmb.ms.entity.Item;
import com.tmb.ms.repo.ItemRepo;
import com.tmb.ms.util.TmbMsErrorCode;

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
			itemResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			itemResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
		} catch (NoSuchElementException nse) {
			itemResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			itemResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + ":" + nse.getMessage());
			logger.error(itemResponse.toString() + nse.getMessage(), nse);
		} catch (IllegalArgumentException iae) {
			itemResponse.setStatusCode(TmbMsErrorCode.VALIDATION_ERR.getErrCode());
			itemResponse.setStatusMessage(TmbMsErrorCode.VALIDATION_ERR.getErrMessage() + ":" + iae.getMessage());
			logger.error(itemResponse.toString() + iae.getMessage(), iae);
		} catch (ConfigurationException | MappingException ceme) {
			itemResponse.setStatusCode(TmbMsErrorCode.MAPPER_ERR.getErrCode());
			itemResponse.setStatusMessage(TmbMsErrorCode.MAPPER_ERR.getErrMessage() + ":" + ceme.getMessage());
			logger.error(itemResponse.toString() + ceme.getMessage(), ceme);
		} catch (Exception e) {
			itemResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			itemResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(itemResponse.toString() + e.getMessage(), e);
		}
		return itemResponse;
	}
	public ItemResponse delete(long id) {
		ItemResponse itemResponse = new ItemResponse();
		try {
			itemRepo.deleteById(id);
			itemResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			itemResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
		} catch (NoSuchElementException nse) {
			itemResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			itemResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + ":" + nse.getMessage());
			logger.error(itemResponse.toString() + nse.getMessage(), nse);
		} catch (IllegalArgumentException iae) {
			itemResponse.setStatusCode(TmbMsErrorCode.VALIDATION_ERR.getErrCode());
			itemResponse.setStatusMessage(TmbMsErrorCode.VALIDATION_ERR.getErrMessage() + ":" + iae.getMessage());
			logger.error(itemResponse.toString() + iae.getMessage(), iae);
		} catch (EmptyResultDataAccessException erdae) {
			itemResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			itemResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + ":" + erdae.getMessage());
			logger.error(itemResponse.toString() + erdae.getMessage(), erdae);
		} catch (Exception e) {
			itemResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			itemResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(itemResponse.toString() + e.getMessage(), e);
		}
		return itemResponse;
	}
}