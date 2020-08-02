package com.tmb.ms.util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TmbMsErrorCode {
	SUCCESS(200, "SUCCESS"),
	VALIDATION_ERR(801, "validation error"),
	MAPPER_ERR(802, "mapping error or mapper config error"),
	DB_REPO_FAILURE(900, "failure at db repo interaction"),
	DB_NO_RECORD(901, "no record found"),
	DB_CONSTRIANT(902, "db constraint error"),
	FILE_PATH_ERR(950, "file path error"),
	READ_WRITE_ERR(951, "read/write error"),
	UNKNOWN_ERR(700, "unknown error");

	private int errCode;
	private String errMessage;

	private TmbMsErrorCode(int errCode, String errMessage) {
		this.errCode = errCode;
		this.errMessage = errMessage;
	}
}
