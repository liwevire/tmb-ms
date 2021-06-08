package com.tmb.ms.dto.response;

import java.util.List;

import com.tmb.ms.entity.Loan;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ValuationResponse extends CommonResponse {
	private List<Loan> loansVal25;
	private List<Loan> loansVal50;
	private List<Loan> loansVal75;
	private List<Loan> loansVal88;
	private List<Loan> loansVal100;
	private List<Loan> loansValX;	
}