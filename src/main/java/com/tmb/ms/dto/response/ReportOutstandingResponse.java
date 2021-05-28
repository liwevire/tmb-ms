package com.tmb.ms.dto.response;

import java.util.List;

import com.tmb.ms.entity.Loan;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportOutstandingResponse extends CommonResponse {
	private double principalOpen;
	private double principalPaidOpen;
	private double interestPaidOpen;
	private double principalClosed;
	private double principalPaidClosed;
	private double interestPaidClosed;
	private double loanCountOpen;
	private double loanCountClosed;
	private double interestOutstandingOpen;
	private List<Loan> loans;
}