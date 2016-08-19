package com.adcampaigns.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/adcampaignsService")
public class AdCampaignsService {

	private static final String SUCCESS_RESULT = "{'result':'success'}";
	private static final String FAILURE_RESULT = "{'result':'failure'}";

	// i have put this as a static map, so that it acts as a memory for the data
	private static Map<String, AdCampaign> partnerMap = new HashMap<String, AdCampaign>();

	//this resource returns all the list of active ads
	@GET
	@Path("/ad")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Partner> getPartners() {

		List<Partner> partnerList = new ArrayList<Partner>();
		for (Entry<String, AdCampaign> map : partnerMap.entrySet()) {
			AdCampaign adcamp = map.getValue();
			if (AdCampaignUtils.isActiveCampaign(adcamp.getAdPostTime(), adcamp.getPartner().getDuration())) {
				partnerList.add(adcamp.getPartner());
			}
		}
		return partnerList;
	}

	//this resource returns the active ad for a specific partner id
	@GET
	@Path("/ad/{partner_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Partner getPartner(@PathParam("partner_id") int partner_id) {

		if (null != partnerMap.get(String.valueOf(partner_id))) {
			AdCampaign adcamp = partnerMap.get(String.valueOf(partner_id));

			if (AdCampaignUtils.isActiveCampaign(adcamp.getAdPostTime(), adcamp.getPartner().getDuration())) {
				return adcamp.getPartner();
			} else {
				return new Partner(String.valueOf(partner_id), 0, "Error ::::: ad campaign no more valid...");
			}
		}
		return new Partner(String.valueOf(partner_id), 0, "partner does not exits...");
	}

	/*this resource creates a ad for the given partner details.
	 * 	will not create a new one if the ad is still active
	 */
	@POST
	@Path("/ad")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createPartner(@FormParam("partner_id") String partner_id, @FormParam("duration") int duration,
			@FormParam("ad_content") String ad_content) throws IOException {
		Partner partner = new Partner(partner_id, duration, ad_content);
		AdCampaign adcamp = new AdCampaign(new Date(), true, partner);

		int result = 1;
		if (null == partnerMap.get(partner_id)) {
			partnerMap.put(partner_id, adcamp);
		} else {
			AdCampaign existingAdCamp = partnerMap.get(String.valueOf(partner_id));
			if (AdCampaignUtils.isActiveCampaign(existingAdCamp.getAdPostTime(),
					existingAdCamp.getPartner().getDuration())) {
				result = 0;
				return "{'Error':'ad campaign for the partner still active'}";
			} else {
				partnerMap.put(partner_id, adcamp);
			}
		}

		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

}
