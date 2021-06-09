package com.tmb.ms.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.tmb.ms.entity.Loan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValuationResponse extends CommonResponse {
	private List<Loan> loanVal75 = new ArrayList<Loan>();
	private List<Loan> loanVal50 = new ArrayList<Loan>();
	private List<Loan> loanVal30 = new ArrayList<Loan>();
	private List<Loan> loanVal15 = new ArrayList<Loan>();
	private List<Loan> loanVal0 = new ArrayList<Loan>();
	private List<Loan> loanValX = new ArrayList<Loan>();

	@Override
	public String toString() {
		return "Total:"
				+ (this.loanVal75.size() + this.loanVal50.size() + this.loanVal30.size() + this.loanVal15.size()
						+ this.loanVal0.size() + this.loanValX.size())
				+ "loanVal75:" + this.loanVal75.size() + ", loanVal50:" + this.loanVal50.size() + ", loanVal30:"
				+ this.loanVal30.size() + ", loanVal15:" + this.loanVal15.size() + ", loanVal0:"
				+ this.loanVal0.size() + ", loanValX:" + this.loanValX.size();
	}
}