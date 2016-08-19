package com.adcampaigns.rest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "partner")
public class Partner implements Serializable {

	private static final long serialVersionUID = 1L;
	private String partner_id;
	private int duration;
	private String ad_content;

	public Partner() {
	}

	public Partner(String partner_id, int duration, String ad_content) {
		this.partner_id = partner_id;
		this.duration = duration;
		this.ad_content = ad_content;
	}

	public String getPartner_id() {
		return partner_id;
	}

	@XmlElement
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public int getDuration() {
		return duration;
	}

	@XmlElement
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getAd_content() {
		return ad_content;
	}

	@XmlElement
	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}

}