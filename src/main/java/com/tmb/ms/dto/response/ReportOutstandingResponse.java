package com.tmb.ms.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportOutstandingResponse extends CommonResponse{
	private double principalOpen;
	private double principalPaidOpen;
	private double interestPaidOpen;
	private double principalClosed;
	private double principalPaidClosed;
	private double interestPaidClosed;
}