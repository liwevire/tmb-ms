package com.tmb.ms.dto.request;

import java.util.Calendar;
import java.util.Date;

import com.tmb.ms.util.TmbMsErrorCode;
import com.tmb.ms.util.TmbMsException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DatedReportRequest extends CommonRequest {
	private Date startDate;
	private Date endDate;

	public void validate() throws TmbMsException {
		boolean isValid = true;
		isValid = this.startDate == null ? false : this.endDate == null ? false : true;
		if (isValid) {
			Calendar startCal = Calendar.getInstance();
			startCal.setTime(this.startDate);
			startCal.set(Calendar.HOUR_OF_DAY, 0);
			startCal.set(Calendar.MINUTE, 0);
			startCal.set(Calendar.SECOND, 0);
			startCal.set(Calendar.MILLISECOND, 0);
			this.startDate=startCal.getTime();
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(this.endDate);
			endCal.set(Calendar.HOUR_OF_DAY, 23);
			endCal.set(Calendar.MINUTE, 59);
			endCal.set(Calendar.SECOND, 59);
			endCal.set(Calendar.MILLISECOND, 999);
			this.endDate=endCal.getTime();
		} else
			throw new TmbMsException(TmbMsErrorCode.VALIDATION_ERR,
					"date either empty or start date is greater than endDate");
	}
}
