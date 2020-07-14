package com.tmb.ms.util;

import lombok.Getter;

@Getter
public class TmbMsException extends Exception {
	private static final long serialVersionUID = 2660242343877025361L;
	private TmbMsErrorCode errCode = TmbMsErrorCode.UNKNOWN_ERR;
	private String errMessage;

	public TmbMsException() {
		super();
	}

	public TmbMsException(TmbMsErrorCode errCode) {
		super();
		this.errCode = errCode;
		this.errMessage = errCode.getErrMessage();
	}

	public TmbMsException(TmbMsErrorCode errCode, String message) {
		super(errCode.getErrMessage() + " | " + message);
		this.errCode = errCode;
		this.errMessage = errCode.getErrMessage() + " | " + message;
	}

}
