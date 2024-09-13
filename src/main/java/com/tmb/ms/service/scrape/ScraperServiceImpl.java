package com.tmb.ms.service.scrape;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmb.ms.dto.response.GoldRateResponse;
import com.tmb.ms.entity.Misc;
import com.tmb.ms.repo.MiscRepo;
import com.tmb.ms.util.TmbMsErrorCode;

@Service
public class ScraperServiceImpl implements ScraperService {
	@Autowired
	private MiscRepo miscRepo;
	private ModelMapper mapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(ScraperServiceImpl.class);

	public GoldRateResponse getGoldRate() {
		Misc misc = new Misc();
		GoldRateResponse goldRateResponse = new GoldRateResponse();
		Document doc;
		try {
			misc = miscRepo.findById(1L).get();
			if (misc.compareTo(new Date()) != 0) {
				doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp")
						.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)").get();
				Elements els = doc.select("tbody,tr,td");
				Element el = els.get(8);
				els = el.select("tr,td");
				el = els.get(4);
				String resp = el.toString().split(">")[3].split("<")[0].replace(" ", "").replace(",", "");
				misc.setGoldRate(Long.parseLong(NumberFormat.getNumberInstance(Locale.UK).parse(resp).toString()));
			}
			misc.setDate(new Date());
				misc = miscRepo.save(misc);
			logger.info("fetched rate from external source");
			goldRateResponse = mapper.map(misc, GoldRateResponse.class);
			goldRateResponse.setStatusCode(TmbMsErrorCode.SUCCESS.getErrCode());
			goldRateResponse.setStatusMessage(TmbMsErrorCode.SUCCESS.getErrMessage());
		} catch (UnknownHostException uhe) {
			goldRateResponse.setStatusCode(TmbMsErrorCode.INTERNET_ERR.getErrCode());
			goldRateResponse.setStatusMessage(TmbMsErrorCode.INTERNET_ERR.getErrMessage() + ":" + uhe.getMessage());
			logger.error(goldRateResponse.toString() + uhe.getMessage(), uhe);
		} catch (IOException ioe) {
			goldRateResponse.setStatusCode(TmbMsErrorCode.URL_CONN_ERR.getErrCode());
			goldRateResponse.setStatusMessage(TmbMsErrorCode.URL_CONN_ERR.getErrMessage() + ":" + ioe.getMessage());
			logger.error(goldRateResponse.toString() + ioe.getMessage(), ioe);
		} catch (NoSuchElementException nse) {
			goldRateResponse.setStatusCode(TmbMsErrorCode.DB_NO_RECORD.getErrCode());
			goldRateResponse.setStatusMessage(TmbMsErrorCode.DB_NO_RECORD.getErrMessage() + ":" + nse.getMessage());
			logger.error(goldRateResponse.toString() + nse.getMessage(), nse);
		} catch (ConfigurationException | MappingException ceme) {
			goldRateResponse.setStatusCode(TmbMsErrorCode.MAPPER_ERR.getErrCode());
			goldRateResponse.setStatusMessage(TmbMsErrorCode.MAPPER_ERR.getErrMessage() + ":" + ceme.getMessage());
			logger.error(goldRateResponse.toString() + ceme.getMessage(), ceme);
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
