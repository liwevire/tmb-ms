package com.tmb.ms.util;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.tmb.ms.dto.response.LoanOutstandingResponse;
import com.tmb.ms.entity.Activity;
import com.tmb.ms.entity.Loan;

@Service
public class ReportUtil {

	public LoanOutstandingResponse calulateOutstanding(Loan loan) throws TmbMsException{
		LoanOutstandingResponse outstandingResponse = new LoanOutstandingResponse();
		Set<Activity> activities = loan.getActivities();
		Comparator<Activity> byDate=(Activity a1, Activity a2) -> a1.compareTo(a2);
		List<Activity> activityList = loan.setToActivityList(activities);
		Collections.sort(activityList, byDate);
		String runningString = "";
		Date loanDate = null;
		Date endDate = new Date();
		if(activityList.isEmpty())
			throw new TmbMsException(TmbMsErrorCode.CAL_INTEREST_NO_ACTIVITY);
		if(loan.getStatus().equalsIgnoreCase(TmbMsConstant.LOAN_STS_CLOSED)) {
			endDate = activityList.get(activityList.size()-1).getDate();
		}
		double principal = 0;
		double principalPaid = 0;
		double interestAccrued = 0;
		double interestPaid = 0;
		for (Activity activity : activities) {
			switch (activity.getCategory()) {
			case TmbMsConstant.ACT_CAT_PRINCIPAL:
				principal = activity.getAmount();
				loanDate = activity.getDate();
				break;
			case TmbMsConstant.ACT_CAT_FINTEREST:
			case TmbMsConstant.ACT_CAT_IPAYMENT:
				interestPaid += activity.getAmount();
				break;	
			case TmbMsConstant.ACT_CAT_PPAYMENT:
				principalPaid += activity.getAmount();
				break;
			default:
				break;
			}
		}
		if(loanDate== null) 
			throw new TmbMsException(TmbMsErrorCode.CAL_INTEREST_NO_PRINCIPAL);
		Date dateCursor = loanDate;
		double runningPrincipal = principal;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(calculateDays(loanDate, endDate)<=TmbMsConstant.MIN_INTEREST_DAYS) {
			interestAccrued = principal*TmbMsConstant.INTEREST_RATE_DAY*TmbMsConstant.MIN_INTEREST_DAYS;
			runningString = "| " + sdf.format(loanDate) + " - " + sdf.format(endDate) + " = "
					+ calculateDays(loanDate, endDate) + " days rounded to minimum " 
					+ TmbMsConstant.MIN_INTEREST_DAYS + " days #Int=" + Math.round(interestAccrued); 
		} else {
			double interestHolder = 0;
			for (Activity activity : activityList) {
				if (activity.getCategory().equalsIgnoreCase(TmbMsConstant.ACT_CAT_PPAYMENT)) {
					interestHolder = runningPrincipal * TmbMsConstant.INTEREST_RATE_DAY * calculateDays(dateCursor, activity.getDate());
					interestAccrued += interestHolder;
					runningString += " | " + sdf.format(dateCursor) + " - " + sdf.format(activity.getDate()) + " = "
							+ calculateDays(dateCursor, activity.getDate()) + " days #Int=" + Math.round(interestHolder);
					dateCursor = activity.getDate();
					runningPrincipal -= activity.getAmount();
				}
				endDate = activity.getDate().compareTo(endDate)>0?activity.getDate():endDate;
			}
			interestHolder = runningPrincipal*TmbMsConstant.INTEREST_RATE_DAY * calculateDays(dateCursor, endDate);
			interestAccrued += interestHolder;
			runningString += " | " + sdf.format(dateCursor) + " - " + sdf.format(endDate) + " = "
					+ calculateDays(dateCursor, endDate) + " days #Int=" + Math.round(interestHolder);
		}
		interestAccrued = Math.round(interestAccrued/5)*5;
		outstandingResponse.setTotalDays(calculateDays(loanDate, endDate));
		outstandingResponse.setPrincipal(principal);
		outstandingResponse.setPrincipalPaid(principalPaid);
		outstandingResponse.setPrincipalOutstanding(principal-principalPaid);
		outstandingResponse.setInterest(interestAccrued);
		outstandingResponse.setInterestPaid(interestPaid);
		outstandingResponse.setInterestOutstanding(interestAccrued-interestPaid);
		outstandingResponse.setCalcComment(runningString.trim());
		return outstandingResponse;
	}
	private int calculateDays(Date startDate, Date endDate) {
		int days = (int)((endDate.getTime() - startDate.getTime())/ TmbMsConstant.DAY_IN_MILLIS );
		return days;
	}
}