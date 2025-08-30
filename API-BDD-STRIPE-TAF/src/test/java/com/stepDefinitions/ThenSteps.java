package com.stepDefinitions;

import java.util.List;
import java.util.Map;

import com.generic.TestUtils;

import io.cucumber.java.en.Then;

public class ThenSteps {

	@Then("new customer {string} be created")
	public void new_customer_be_created(String strCreated) {
	    System.out.println("New customer "+strCreated+" be created");
	}
	
	@Then("Verify status code should be {string}")
	public void verify_status_code_should_be(String string, io.cucumber.datatable.DataTable dataTable) {
		 List<Map<String, String>> rows=dataTable.asMaps(String.class,String.class);
		 int status_code=Integer.parseInt(rows.get(0).get("statusCode"));
		 TestUtils.verifyHTTPStatusCode(WhenSteps.response, status_code);
		 
	}

}
