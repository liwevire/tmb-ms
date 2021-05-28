package com.tmb.ms.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.request.DatedReportRequest;
import com.tmb.ms.dto.response.LoanOutstandingResponse;
import com.tmb.ms.dto.response.LoanResponse;
import com.tmb.ms.dto.response.ReportOutstandingResponse;
import com.tmb.ms.entity.Loan;
import com.tmb.ms.repo.LoanRepo;
import com.tmb.ms.repo.ReportRepo;
import com.tmb.ms.util.ReportUtil;
import com.tmb.ms.util.TmbMsConstant;
import com.tmb.ms.util.TmbMsErrorCode;
import com.tmb.ms.util.TmbMsException;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepo reportRepo;
	@Autowired
	private LoanRepo loanRepo;
	@Autowired
	private LoanService loanService;
	@Autowired
	private ReportUtil reportUtil;
	private ModelMapper mapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Override
	public ReportOutstandingResponse getOutstanding() {
		ReportOutstandingResponse reportResponse = new ReportOutstandingResponse();
		try {
			double principalOpen = reportRepo.getAmountAgg(TmbMsConstant.LOAN_STS_OPEN, TmbMsConstant.ACT_CAT_PRINCIPAL);
			double principalPaidOpen = reportRepo.getAmountAgg(TmbMsConstant.LOAN_STS_OPEN, TmbMsConstant.ACT_CAT_PPAYMENT);
			double interestPaidOpen = reportRepo.getAmountAgg(TmbMsConstant.LOAN_STS_OPEN, TmbMsConstant.ACT_CAT_IPAYMENT);
			double initInterestOpen = reportRepo.getAmountAgg(TmbMsConstant.LOAN_STS_OPEN, TmbMsConstant.ACT_CAT_FINTEREST);
			double principalClosed = reportRepo.getAmountAgg(TmbMsConstant.LOAN_STS_CLOSED, TmbMsConstant.ACT_CAT_PRINCIPAL);
			double principalPaidClosed = reportRepo.getAmountAgg(TmbMsConstant.LOAN_STS_CLOSED, TmbMsConstant.ACT_CAT_PPAYMENT);
			double interestPaidClosed = reportRepo.getAmountAgg(TmbMsConstant.LOAN_STS_CLOSED, TmbMsConstant.ACT_CAT_IPAYMENT);
			double initInterestClosed = reportRepo.getAmountAgg(TmbMsConstant.LOAN_STS_CLOSED, TmbMsConstant.ACT_CAT_FINTEREST);
			double loanCountOpen = 0;
			double loanCountClosed = 0;
			double interestOutstandingOpen = 0;
			reportResponse.setPrincipalOpen(principalOpen);
			reportResponse.setPrincipalPaidOpen(principalPaidOpen);
			reportResponse.setInterestPaidOpen(interestPaidOpen + initInterestOpen);
			reportResponse.setPrincipalClosed(principalClosed);
			reportResponse.setPrincipalPaidClosed(principalPaidClosed);
			reportResponse.setInterestPaidClosed(interestPaidClosed + initInterestClosed);
			List<Loan> loans = new ArrayList<Loan>();
			loans = loanRepo.findAll();
			for (Loan loan : loans) {
				if (loan.getStatus().equalsIgnoreCase(TmbMsConstant.LOAN_STS_OPEN)) {
					loanCountOpen++;
					interestOutstandingOpen += reportUtil.calulateOutstanding(loan).getInterestOutstanding();
				}
				if (loan.getStatus().equalsIgnoreCase(TmbMsConstant.LOAN_STS_CLOSED))
					loanCountClosed++;
			}
			reportResponse.setInterestOutstandingOpen(interestOutstandingOpen);
			reportResponse.setLoanCountOpen(loanCountOpen);
			reportResponse.setLoanCountClosed(loanCountClosed);
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
	public ReportOutstandingResponse getDatedOutstanding(DatedReportRequest request) {
		ReportOutstandingResponse reportResponse = new ReportOutstandingResponse();
		try {
			request.validate();
			double principalOpen = reportRepo.getAmountAggDated(TmbMsConstant.LOAN_STS_OPEN, TmbMsConstant.ACT_CAT_PRINCIPAL, request.getStartDate(), request.getEndDate());
			double principalPaidOpen = reportRepo.getAmountAggDated(TmbMsConstant.LOAN_STS_OPEN, TmbMsConstant.ACT_CAT_PPAYMENT, request.getStartDate(), request.getEndDate());
			double interestPaidOpen = reportRepo.getAmountAggDated(TmbMsConstant.LOAN_STS_OPEN, TmbMsConstant.ACT_CAT_IPAYMENT, request.getStartDate(), request.getEndDate());
			double initInterestOpen = reportRepo.getAmountAggDated(TmbMsConstant.LOAN_STS_OPEN, TmbMsConstant.ACT_CAT_FINTEREST, request.getStartDate(), request.getEndDate());
			double principalClosed = reportRepo.getAmountAggDated(TmbMsConstant.LOAN_STS_CLOSED, TmbMsConstant.ACT_CAT_PRINCIPAL, request.getStartDate(), request.getEndDate());
			double principalPaidClosed = reportRepo.getAmountAggDated(TmbMsConstant.LOAN_STS_CLOSED, TmbMsConstant.ACT_CAT_PPAYMENT, request.getStartDate(), request.getEndDate());
			double interestPaidClosed = reportRepo.getAmountAggDated(TmbMsConstant.LOAN_STS_CLOSED, TmbMsConstant.ACT_CAT_IPAYMENT, request.getStartDate(), request.getEndDate());
			double initInterestClosed = reportRepo.getAmountAggDated(TmbMsConstant.LOAN_STS_CLOSED, TmbMsConstant.ACT_CAT_FINTEREST, request.getStartDate(), request.getEndDate());
			double loanCountOpen = 0;
			double loanCountClosed = 0;
			double interestOutstandingOpen = 0;
			reportResponse.setPrincipalOpen(principalOpen);
			reportResponse.setPrincipalPaidOpen(principalPaidOpen);
			reportResponse.setInterestPaidOpen(interestPaidOpen + initInterestOpen);
			reportResponse.setPrincipalClosed(principalClosed);
			reportResponse.setPrincipalPaidClosed(principalPaidClosed);
			reportResponse.setInterestPaidClosed(interestPaidClosed + initInterestClosed);
			Set<Loan> loans = new HashSet<Loan>();
			loans = loanRepo.getLoanDated(request.getStartDate(), request.getEndDate());
			List<Loan> loanList = new ArrayList<Loan>();
			loanList.addAll(loans);
			for (Loan loan : loanList) {
				if (loan.getStatus().equalsIgnoreCase(TmbMsConstant.LOAN_STS_OPEN)) {
					loanCountOpen++;
					interestOutstandingOpen += reportUtil.calulateOutstanding(loan).getInterestOutstanding();
				}
				if (loan.getStatus().equalsIgnoreCase(TmbMsConstant.LOAN_STS_CLOSED))
					loanCountClosed++;
			}
			reportResponse.setInterestOutstandingOpen(interestOutstandingOpen);
			reportResponse.setLoanCountOpen(loanCountOpen);
			reportResponse.setLoanCountClosed(loanCountClosed);
			reportResponse.setLoans(loanList);
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
		try {
			LoanResponse loanResponse = loanService.getbyId(request);
			outstandingResponse= mapper.map(loanResponse, LoanOutstandingResponse.class);
			Loan loan = mapper.map(loanResponse, Loan.class); 
			if(outstandingResponse.getStatusCode()!=TmbMsErrorCode.SUCCESS.getErrCode())
				throw new TmbMsException(TmbMsErrorCode.UNKNOWN_ERR, outstandingResponse.getStatusMessage());
			if (loan.getActivities() == null || loan.getActivities().isEmpty())
				throw new TmbMsException(TmbMsErrorCode.DB_NO_RECORD, TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + " | no activity found for this id: " + request.getId());
			outstandingResponse = reportUtil.calulateOutstanding(loan);
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