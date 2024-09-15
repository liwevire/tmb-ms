package com.tmb.ms.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Getter
@Setter
@ToString
public class YmlConfig {
	private String goldRateUrl;
	private String loanStatusOpen;
	private String loanStatusClosed;
	private String loanStatusOther;
}
