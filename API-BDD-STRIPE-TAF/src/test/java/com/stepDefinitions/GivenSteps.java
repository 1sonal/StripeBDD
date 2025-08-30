package com.stepDefinitions;

import java.util.List;
import java.util.Map;

import org.aeonbits.owner.ConfigFactory;

import com.generic.ConfigProperties;
import com.generic.ConstantUtils;

import io.cucumber.java.en.Given;

public class GivenSteps {

	public static ConfigProperties configProperties;
	public static String validSecretKey;
	public static String baseURL;
	public static String basePath;
	public static String endPoint;
	public static String TCIDName;
	
	
	public GivenSteps() {
		configProperties = ConfigFactory.create(ConfigProperties.class);
		ConstantUtils.setBaseURL(configProperties.getBaseURI());
	}
	
	
	@Given("User is ready to execute TCID {string} with valid secret key,baseUrl and endPoint")
	public void user_is_ready_to_execute_tcid_with_valid_secret_key_base_url_and_end_point(String strTCIDName) {
		baseURL= ConstantUtils.getBaseURL();
		validSecretKey=ConstantUtils.Valid_Secret_Key;
		basePath= ConstantUtils.Base_Path;
		endPoint=ConstantUtils.Cust_Endpoint;
		TCIDName=strTCIDName;
		
		System.out.println("API ready to execute:"+baseURL+basePath+endPoint);
	}
	
	@Given("User is ready to execute TCID with valid secret key,baseUrl and endPoint")
	public void user_is_ready_to_execute_tcid_with_valid_secret_key_base_url_and_end_point(io.cucumber.datatable.DataTable dataTable) {
	    List<Map<String, String>> rows=dataTable.asMaps(String.class,String.class);
	    baseURL= ConstantUtils.getBaseURL();
		validSecretKey=ConstantUtils.Valid_Secret_Key;
		basePath= ConstantUtils.Base_Path;
		endPoint=ConstantUtils.Cust_Endpoint;
	    TCIDName=rows.get(0).get("TCID");
	    System.out.println("API ready to execute:"+baseURL+basePath+endPoint);
	    
	}
	
	@Given("User is ready to execute TCID {string} with invalid secret key,baseUrl and endPoint")
	public void user_is_ready_to_execute_tcid_with_invalid_secret_key_base_url_and_end_point(String strTCIDName) {
		baseURL= ConstantUtils.getBaseURL();
		validSecretKey=ConstantUtils.Invalid_Secret_Key;
		basePath= ConstantUtils.Base_Path;
		endPoint=ConstantUtils.Cust_Endpoint;
		TCIDName=strTCIDName;
		
		System.out.println("API ready to execute:"+baseURL+basePath+endPoint);
	}
}
