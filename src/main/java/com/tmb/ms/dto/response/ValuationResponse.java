package com.tmb.ms.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.tmb.ms.entity.Loan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValuationResponse extends CommonResponse {
	private List<Loan> loanVal25 = new ArrayList<Loan>();
	private List<Loan> loanVal50 = new ArrayList<Loan>();
	private List<Loan> loanVal70 = new ArrayList<Loan>();
	private List<Loan> loanVal85 = new ArrayList<Loan>();
	private List<Loan> loanVal100 = new ArrayList<Loan>();
	private List<Loan> loanValX = new ArrayList<Loan>();

	@Override
	public String toString() {
		return "Total:"
				+ (this.loanVal25.size() + this.loanVal50.size() + this.loanVal70.size() + this.loanVal85.size()
						+ this.loanVal100.size() + this.loanValX.size())
				+ "loanVal25:" + this.loanVal25.size() + ", loanVal50:" + this.loanVal50.size() + ", loanVal70:"
				+ this.loanVal70.size() + ", loanVal85:" + this.loanVal85.size() + ", loanVal100:"
				+ this.loanVal100.size() + ", loanValX:" + this.loanValX.size();
	}
}