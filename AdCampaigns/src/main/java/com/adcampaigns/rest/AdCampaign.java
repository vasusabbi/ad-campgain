package com.adcampaigns.rest;

import java.util.Date;

public class AdCampaign {

	private Date adPostTime;
	private Partner partner;

	public AdCampaign() {
	}

	public AdCampaign(Date adPostTime, boolean isStillValid, Partner partner) {
		this.adPostTime = adPostTime;
		this.partner = partner;
	}

	public Date getAdPostTime() {
		return adPostTime;
	}

	public void setAdPostTime(Date adPostTime) {
		this.adPostTime = adPostTime;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}
