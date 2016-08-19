package com.adcampaigns.rest;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Form;
import javax.ws.rs.client.Entity;

public class AdCampaignsServiceTest {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/AdCampaigns/rest/adcampaignsService/ad";
	private static final String SUCCESS_RESULT = "{'result':'success'}";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";

	private void init() {
		this.client = ClientBuilder.newClient();
	}

	public static void main(String[] args) {
		/**
		 * Added system.out.printlns just for testing purpose and for this poc as i have to finish quickly. 
		 * We can add loggers during the real time.
		 * We should not use system.outs in real time.
		 */
		
		AdCampaignsServiceTest tester = new AdCampaignsServiceTest();
		tester.init(); // initialize the tester

		// case 1 [to add a partner and get the partner details]
		tester.testAddPartners("10001", 100, "testing xfinity ad 1");
		tester.testGetPartner("10001");

		// case 2 [to get all the list of partners]
		tester.testGetAllPartners();
		
		// case 3 [add an existing partner with in the ad duration to get an error message]
		tester.testAddPartners("10001", 100, "testing xfinity ad 1");
		tester.testGetPartner("10001");
		
		//case 4 [add a new partner]
		tester.testAddPartners("10002", 200, "testing xfinity ad 2");
		tester.testGetPartner("10002");
		
		//case 5 [add an existing partner after the ad duration. which should get added again]
		tester.testAddPartners("10001", 100, "testing xfinity ad 1");
		tester.testGetPartner("10001");
		
		//case 6 [to get all the list of partners]
		tester.testGetAllPartners();

	}

	// Test: Get list of all partners
	private void testGetAllPartners() {
		GenericType<List<Partner>> list = new GenericType<List<Partner>>() {
		};
		List<Partner> partners = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_JSON).get(list);
		String result = PASS;
		if (partners.isEmpty()) {
			result = FAIL;
		} else {
			result = PASS;
		}
		System.out.println("Test case name: testGetAllPartners, Result: " + result);
	}

	// Test: Get a partner for the given partner id
	private void testGetPartner(String partner_id) {
		Partner samplePartner = new Partner();
		samplePartner.setPartner_id(partner_id);

		Partner partner = client.target(REST_SERVICE_URL).path("/{partner_id}")
				.resolveTemplate("partner_id", partner_id).request(MediaType.APPLICATION_JSON).get(Partner.class);

		String result = FAIL;
		if (null != samplePartner && samplePartner.getPartner_id().equals(partner.getPartner_id())) {
			result = PASS;
			System.out.println("Test case name: testGetPartner: Result: " + result +" Ad_content::"+partner.getAd_content());
		}else{
			System.out.println("Test case name: testGetPartner: Result: " + result + "partner_id::"+partner_id);
		}
	}

	// Test: create a partner
	private void testAddPartners(String partner_id, int duration, String ad_content) {
		Form form = new Form();
		form.param("partner_id", partner_id);
		form.param("duration", String.valueOf(duration));
		form.param("ad_content", ad_content);

		String callResult = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);

		String result = PASS;
		if (!SUCCESS_RESULT.equals(callResult)) {
			System.out.println("Test case name: testAddPartners, callResult: " + callResult +" partner_id::"+partner_id);
			result = FAIL;
		}

		System.out.println("Test case name: testAddPartners, Result: " + result+" partner_id::"+partner_id);
	}

}