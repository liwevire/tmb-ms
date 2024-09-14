package com.tmb.ms.service.scrape;

import java.io.IOException;
import java.text.ParseException;

import com.tmb.ms.dto.response.GoldRateResponse;
import com.tmb.ms.entity.Misc;

public interface ScraperService {
	public GoldRateResponse getGoldRate();
	public Misc fetchCurrentRate() throws IOException, NumberFormatException, ParseException;
}