package com.tmb.ms.service;

import java.util.NoSuchElementException;
import java.util.Set;

import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.LoanOutstandingResponse;
import com.tmb.ms.dto.response.ReportOutstandingResponse;
import com.tmb.ms.entity.Activity;
import com.tmb.ms.repo.ActivityRepo;
import com.tmb.ms.repo.ReportRepo;
import com.tmb.ms.util.ReportUtil;
import com.tmb.ms.util.TmbMsErrorCode;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepo reportRepo;
	@Autowired
	private ActivityRepo activityRepo;
	@Autowired
	private ReportUtil reportUtil;
	private Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Override
	public ReportOutstandingResponse getPrincipalOutstanding() {
		ReportOutstandingResponse reportResponse = new ReportOutstandingResponse();
		try {
			double principalOpen = reportRepo.getAmountAgg("open", "principal");
			double principalPaidOpen = reportRepo.getAmountAgg("open", "ppayment");
			double interestPaidOpen = reportRepo.getAmountAgg("open", "ipayment");
			double principalClosed = reportRepo.getAmountAgg("closed", "principal");
			double principalPaidClosed = reportRepo.getAmountAgg("closed", "ppayment");
			double interestPaidClosed = reportRepo.getAmountAgg("closed", "ipayment");
			reportResponse.setPrincipalOpen(principalOpen);
			reportResponse.setPrincipalPaidOpen(principalPaidOpen);
			reportResponse.setInterestPaidOpen(interestPaidOpen);
			reportResponse.setPrincipalClosed(principalClosed);
			reportResponse.setPrincipalPaidClosed(principalPaidClosed);
			reportResponse.setInterestPaidClosed(interestPaidClosed);
			reportResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			reportResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
		} catch (NoSuchElementException nse) {
			reportResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			reportResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + ":" + nse.getMessage());
			logger.error(reportResponse.toString() + nse.getMessage(), nse);
		} catch (IllegalArgumentException iae) {
			reportResponse.setStatusCode(TmbMsErrorCode.VALIDATION_ERR.getErrCode());
			reportResponse.setStatusMessage(TmbMsErrorCode.VALIDATION_ERR.getErrMessage() + ":" + iae.getMessage());
			logger.error(reportResponse.toString() + iae.getMessage(), iae);
		} catch (ConfigurationException | MappingException ceme) {
			reportResponse.setStatusCode(TmbMsErrorCode.MAPPER_ERR.getErrCode());
			reportResponse.setStatusMessage(TmbMsErrorCode.MAPPER_ERR.getErrMessage() + ":" + ceme.getMessage());
			logger.error(reportResponse.toString() + ceme.getMessage(), ceme);
		} catch (Exception e) {
			reportResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			reportResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(reportResponse.toString() + e.getMessage(), e);
		}
		return reportResponse;
	}
	@Override
	public LoanOutstandingResponse calculateInterest(CommonRequest request) {
		LoanOutstandingResponse outstandingResponse = new LoanOutstandingResponse();
		Set<Activity> activities = null;
		try {
			activities = activityRepo.findByLoanId(request.getId());
			System.out.println(activities.isEmpty());
			if(activities!=null && !activities.isEmpty())
				outstandingResponse = reportUtil.calulateOutstanding(activities);
			
			outstandingResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			outstandingResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
		} catch (IllegalArgumentException iae) {
			outstandingResponse.setStatusCode(TmbMsErrorCode.VALIDATION_ERR.getErrCode());
			outstandingResponse.setStatusMessage(TmbMsErrorCode.VALIDATION_ERR.getErrMessage() + ":" + iae.getMessage());
			logger.error(outstandingResponse.toString() + iae.getMessage(), iae);
		} catch (NoSuchElementException nse) {
			outstandingResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			outstandingResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage());
			logger.error(outstandingResponse.toString() + nse.getMessage(), nse);
		} catch (Exception e) {
			outstandingResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			outstandingResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(outstandingResponse.toString() + e.getMessage(), e);
		}
		return outstandingResponse;
	}
}