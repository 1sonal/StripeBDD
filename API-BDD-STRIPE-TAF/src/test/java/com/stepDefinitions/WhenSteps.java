package com.stepDefinitions;

import com.generic.ConstantUtils;
import com.generic.RequestTypeUtils;

import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WhenSteps {

	public static Response response;
	RequestSpecification requestSpecification;
	String endPoint;
	
	@When("User sends post request with {string} {string} and {string} {string}")
	public void user_sends_post_request_with_and(String strFormParam1, String strFormParm1Value, String strFormParam2, String strFormParam2Value) {
		requestSpecification=RequestTypeUtils.addRequestSpecification(GivenSteps.baseURL);
		response=RequestTypeUtils.doPostCreateCustomerRequest(requestSpecification, strFormParam1, strFormParm1Value, strFormParam2, strFormParam2Value,ConstantUtils.Base_Path+ConstantUtils.Cust_Endpoint);
		response.prettyPrint();
		
	}
	
	@When("User sends post request with customer Id")
	public void user_sends_post_request_with_customer_id() {
		requestSpecification=RequestTypeUtils.addRequestSpecification(GivenSteps.baseURL);
		String id=ConstantUtils.getCustId();
	    response=RequestTypeUtils.doGetCustomerRequest(requestSpecification,ConstantUtils.Base_Path+ConstantUtils.Cust_Endpoint, id);
	    response.prettyPrint();
	}
	
	@When("User sends post request with {string} {string} and {string} {string} and customer Id")
	public void user_sends_post_request_with_and_and_customer_id(String strFormParam1, String strFormParm1Value, String strFormParam2, String strFormParam2Value) {
		requestSpecification=RequestTypeUtils.addRequestSpecification(GivenSteps.baseURL);
		endPoint=ConstantUtils.Base_Path+ConstantUtils.Cust_Endpoint+ "/" +ConstantUtils.getCustId();
		response=RequestTypeUtils.doPostCreateCustomerRequest(requestSpecification, strFormParam1, strFormParm1Value, strFormParam2, strFormParam2Value,endPoint);
		response.prettyPrint();
	}
	
	@When("User sends delete request with customer Id")
	public void user_sends_delete_request_with_customer_id() {
		requestSpecification=RequestTypeUtils.addRequestSpecification(GivenSteps.baseURL);
		endPoint=ConstantUtils.Base_Path+ConstantUtils.Cust_Endpoint;
		response=RequestTypeUtils.doDeleteCustomerRequest(requestSpecification, endPoint, ConstantUtils.getCustId());
		response.prettyPrint();
	}
	
	@When("User sends post request with invalid secret key")
	public void user_sends_post_request_with_invalid_secret_key() {
		requestSpecification=RequestTypeUtils.addRequestSpecification(GivenSteps.baseURL);
		response=RequestTypeUtils.doPostCustomerRequestWithInvalidKey(requestSpecification, ConstantUtils.Base_Path+ConstantUtils.Cust_Endpoint);
		response.prettyPrint();
	}
	
	@When("User sends get request with customer Id")
	public void user_sends_get_request_with_customer_id() {
		requestSpecification=RequestTypeUtils.addRequestSpecification(GivenSteps.baseURL);
		String id=ConstantUtils.getCustId();
	    response=RequestTypeUtils.doGetCustomerRequest(requestSpecification,ConstantUtils.Base_Path+ConstantUtils.Cust_Endpoint, id);
	    response.prettyPrint();
	}
	
	@When("User sends get request")
	public void user_sends_get_request() {
		requestSpecification=RequestTypeUtils.addRequestSpecification(GivenSteps.baseURL);
		response=RequestTypeUtils.doGetCustomerListRequest(requestSpecification,ConstantUtils.Base_Path+ConstantUtils.Cust_Endpoint);
	    response.prettyPrint();
	}
}
