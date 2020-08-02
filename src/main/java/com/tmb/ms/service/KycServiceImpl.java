package com.tmb.ms.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import com.tmb.ms.dto.request.CommonRequest;
import com.tmb.ms.dto.response.CommonResponse;
import com.tmb.ms.util.TmbMsErrorCode;

@Service
public class KycServiceImpl implements KycService {

	private Logger logger = LoggerFactory.getLogger(KycServiceImpl.class);
	private final String uploadFolder = "C:\\Users\\premk\\git";

	public CommonResponse getbyId(CommonRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		try {

		}  catch (InvalidPathException ipe) {
			commonResponse.setStatusCode(TmbMsErrorCode.FILE_PATH_ERR.getErrCode());
			commonResponse.setStatusMessage(TmbMsErrorCode.FILE_PATH_ERR.getErrMessage() + ":" + ipe.getMessage());
			logger.error(commonResponse.toString() + ipe.getMessage(), ipe);
//		} catch (IOException ioe) {
//			commonResponse.setStatusCode(TmbMsErrorCode.READ_WRITE_ERR.getErrCode());
//			commonResponse.setStatusMessage(TmbMsErrorCode.READ_WRITE_ERR.getErrMessage() + ":" + ioe.getMessage());
//			logger.error(commonResponse.toString() + ioe.getMessage(), ioe);
		} catch (Exception e) {
			commonResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			commonResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(commonResponse.toString() + e.getMessage(), e);
		}
		return commonResponse;
	}

	@Override
	public CommonResponse update(CommonRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			byte[] fileByte=Base64Utils.decodeFromString(request.getData());
			Path copyLocation = Paths.get(uploadFolder, File.separator, StringUtils.cleanPath(request.getId()+".face.jpg"));
			FileOutputStream fos= new FileOutputStream(copyLocation.toString());
			fos.write(fileByte);
			fos.close();
			commonResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			commonResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
		} catch (InvalidPathException ipe) {
			commonResponse.setStatusCode(TmbMsErrorCode.FILE_PATH_ERR.getErrCode());
			commonResponse.setStatusMessage(TmbMsErrorCode.FILE_PATH_ERR.getErrMessage() + ":" + ipe.getMessage());
			logger.error(commonResponse.toString() + ipe.getMessage(), ipe);
		} catch (IOException ioe) {
			commonResponse.setStatusCode(TmbMsErrorCode.READ_WRITE_ERR.getErrCode());
			commonResponse.setStatusMessage(TmbMsErrorCode.READ_WRITE_ERR.getErrMessage() + ":" + ioe.getMessage());
			logger.error(commonResponse.toString() + ioe.getMessage(), ioe);
		} catch (Exception e) {
			commonResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			commonResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(commonResponse.toString() + e.getMessage(), e);
		}
		return commonResponse;
	}

	@Override
	public CommonResponse delete(long id) {
		CommonResponse commonResponse = new CommonResponse();
		try {

		}  catch (InvalidPathException ipe) {
			commonResponse.setStatusCode(TmbMsErrorCode.FILE_PATH_ERR.getErrCode());
			commonResponse.setStatusMessage(TmbMsErrorCode.FILE_PATH_ERR.getErrMessage() + ":" + ipe.getMessage());
			logger.error(commonResponse.toString() + ipe.getMessage(), ipe);
//		} catch (IOException ioe) {
//			commonResponse.setStatusCode(TmbMsErrorCode.READ_WRITE_ERR.getErrCode());
//			commonResponse.setStatusMessage(TmbMsErrorCode.READ_WRITE_ERR.getErrMessage() + ":" + ioe.getMessage());
//			logger.error(commonResponse.toString() + ioe.getMessage(), ioe);
		} catch (Exception e) {
			commonResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			commonResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(commonResponse.toString() + e.getMessage(), e);
		}
		return commonResponse;
	}

}