package com.adcampaigns.rest;

import java.util.Date;

public class AdCampaignUtils {

	/*
	 * checks for if the partner has a active campaign based on the duration of the ad.
	 * if the duration of the ad exceeds the time difference in seconds from the time of posting to the current time
	 * this api returns "false" and the ad is no more active.
	 * if this api returns "true" then the ad is still active and a new ad cannot be created for the same partner
	 */
	public static boolean isActiveCampaign(Date adDate, int duration) {

		/**
		 * Added system.out.printlns just for testing purpose and for this poc as i have to finish quickly. 
		 * We can add loggers during the real time.
		 * We should not use system.outs in real time.
		 */
		if (null != adDate && duration > 0) {
			long seconds = (new Date().getTime() - adDate.getTime()) / 1000;
			System.out.println("new Date().getTime() :" + new Date());
			System.out.println("adDate.getTime() :" + adDate);
			System.out.println("seconds :" + seconds);
			System.out.println("duration :" + duration);
			if (duration - seconds > 0) {
				return true;
			}
		}
		return false;
	}

}
