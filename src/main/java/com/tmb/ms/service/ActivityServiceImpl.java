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
import com.tmb.ms.dto.response.ActivityResponse;
import com.tmb.ms.entity.Activity;
import com.tmb.ms.repo.ActivityRepo;
import com.tmb.ms.util.TmbMsErrorCode;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepo activityRepo;
	private ModelMapper mapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

	public ActivityResponse getbyId(CommonRequest request) {
		ActivityResponse activityResponse = new ActivityResponse();
		Activity activity = null;
		try {
			activity = activityRepo.findById(request.getId()).get();
			activityResponse = mapper.map(activity, ActivityResponse.class);
			activityResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			activityResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
		} catch (NoSuchElementException nse) {
			activityResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			activityResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + ":" + nse.getMessage());
			logger.error(activityResponse.toString() + nse.getMessage(), nse);
		} catch (IllegalArgumentException iae) {
			activityResponse.setStatusCode(TmbMsErrorCode.VALIDATION_ERR.getErrCode());
			activityResponse.setStatusMessage(TmbMsErrorCode.VALIDATION_ERR.getErrMessage() + ":" + iae.getMessage());
			logger.error(activityResponse.toString() + iae.getMessage(), iae);
		} catch (ConfigurationException | MappingException ceme) {
			activityResponse.setStatusCode(TmbMsErrorCode.MAPPER_ERR.getErrCode());
			activityResponse.setStatusMessage(TmbMsErrorCode.MAPPER_ERR.getErrMessage() + ":" + ceme.getMessage());
			logger.error(activityResponse.toString() + ceme.getMessage(), ceme);
		} catch (Exception e) {
			activityResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			activityResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(activityResponse.toString() + e.getMessage(), e);
		}
		return activityResponse;
	}
	public ActivityResponse delete(long id) {
		ActivityResponse activityResponse = new ActivityResponse();
		try {
			activityRepo.deleteById(id);
			activityResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			activityResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
		} catch (NoSuchElementException nse) {
			activityResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			activityResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + ":" + nse.getMessage());
			logger.error(activityResponse.toString() + nse.getMessage(), nse);
		} catch (IllegalArgumentException iae) {
			activityResponse.setStatusCode(TmbMsErrorCode.VALIDATION_ERR.getErrCode());
			activityResponse.setStatusMessage(TmbMsErrorCode.VALIDATION_ERR.getErrMessage() + ":" + iae.getMessage());
			logger.error(activityResponse.toString() + iae.getMessage(), iae);
		} catch (EmptyResultDataAccessException erdae) {
			activityResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			activityResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + ":" + erdae.getMessage());
			logger.error(activityResponse.toString() + erdae.getMessage(), erdae);
		} catch (Exception e) {
			activityResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			activityResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(activityResponse.toString() + e.getMessage(), e);
		}
		return activityResponse;
	}
}