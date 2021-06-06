package com.tmb.ms.service.scrape;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tmb.ms.dto.response.GoldRateResponse;
import com.tmb.ms.util.TmbMsErrorCode;

@Service
public class ScraperServiceImpl implements ScraperService {
	private Logger logger = LoggerFactory.getLogger(ScraperServiceImpl.class);

	public GoldRateResponse getGoldRate() {
		GoldRateResponse goldRateResponse = new GoldRateResponse();
		Document doc;
		try {
			doc = Jsoup.connect("https://www.goodreturns.in/gold-rates/chennai.html").get();
			Elements els = doc.select("div#current-price strong");
			for (Element el : els) {
				goldRateResponse.setGoldRate(Long.parseLong(NumberFormat.getNumberInstance(Locale.UK).parse(el.ownText()).toString()));
			}
		} catch (IOException ioe) {
			goldRateResponse.setStatusCode(TmbMsErrorCode.URL_CONN_ERR.getErrCode());
			goldRateResponse.setStatusMessage(TmbMsErrorCode.URL_CONN_ERR.getErrMessage() + ":" + ioe.getMessage());
			logger.error(goldRateResponse.toString() + ioe.getMessage(), ioe);
		} catch (ParseException pe) {
			goldRateResponse.setStatusCode(TmbMsErrorCode.NUM_PARSE_ERR.getErrCode());
			goldRateResponse.setStatusMessage(TmbMsErrorCode.NUM_PARSE_ERR.getErrMessage() + ":" + pe.getMessage());
			logger.error(goldRateResponse.toString() + pe.getMessage(), pe);
		} catch (Exception e) {
			goldRateResponse.setStatusCode(TmbMsErrorCode.UNKNOWN_ERR.getErrCode());
			goldRateResponse.setStatusMessage(TmbMsErrorCode.UNKNOWN_ERR.getErrMessage() + ":" + e.getMessage());
			logger.error(goldRateResponse.toString() + e.getMessage(), e);
		}
		return goldRateResponse;
	}
}





