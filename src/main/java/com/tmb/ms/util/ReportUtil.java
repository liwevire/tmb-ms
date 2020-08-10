package com.tmb.ms.util;

import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.tmb.ms.dto.response.LoanOutstandingResponse;
import com.tmb.ms.entity.Activity;

@Service
public class ReportUtil {

	public LoanOutstandingResponse calulateOutstanding(Set<Activity> activities) {
		LoanOutstandingResponse outstandingResponse = new LoanOutstandingResponse();
		Date loanDate = null;
		for (Activity activity : activities) {
			if(activity.getCategory().equalsIgnoreCase(TmbMsConstant.PRINCIPAL)) {
				loanDate = activity.getDate();
				System.out.println(loanDate);
			}
		}
		return outstandingResponse;
	}
}