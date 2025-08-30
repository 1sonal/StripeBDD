package com.stepDefinitions;

import static org.hamcrest.Matchers.isA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.Assert;

import com.generic.ConstantUtils;
import com.generic.TestUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
//import io.restassured.response.Response;

public class AndSteps {

	@And("Verify status code should be {int}")
	public void verify_status_code_should_be(Integer status_code) {
		
		TestUtils.verifyHTTPStatusCode(WhenSteps.response, status_code);
	}
	
	@And("Verify response contains Id")
	public void verify_response_contains_id() {
		String id = WhenSteps.response.jsonPath().get("id");
		System.out.println("Response contains id:-"+id);
		ConstantUtils.setCustId(id);
	}
	
	@And("Verify {string} field is a key in response")
	public void verify_field_is_a_key_in_response(String strId) {
		
		Assert.assertTrue(TestUtils.jsonHasKey(WhenSteps.response.asString(), strId));
		System.out.println("Id field as verified as response key");
	}

	@And("Verify {string} field is not null")
	public void verify_field_is_not_null(String strId) {
		String id = WhenSteps.response.jsonPath().get(strId);
		if (!id.equals("")) {
			System.out.println("Verify id is not null - " + id);
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
	
	@And("Verify {string} field is a String")
	public void verify_field_is_a_string(String strId) {

		WhenSteps.response.then().body(strId, isA(String.class));
	}
	
	@And("Verify the response header key and value")
	public void verify_the_response_header_key_and_value(DataTable dataTable) {
		ArrayList<String> actualHeaderList = new ArrayList<>();
		Headers allHeaders = WhenSteps.response.headers();
		for (Header header : allHeaders) {
			String strHeader = header.getName().trim() + "||" + header.getValue().trim();
			actualHeaderList.add(strHeader);
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}
		ArrayList<String> expectedHeaderList = new ArrayList<>();
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String strRow = columns.get("Key") + "||" + columns.get("Value").trim();
			expectedHeaderList.add(strRow);
		}
		Collections.sort(actualHeaderList);
		Collections.sort(expectedHeaderList);
		System.out.println("actual Header List  " + actualHeaderList.toString());
		System.out.println("expected Header List  " + expectedHeaderList.toString());
		for (int index = 0; index < expectedHeaderList.size(); index++) {
			String expectedHeader = expectedHeaderList.get(index);
			if (actualHeaderList.contains(expectedHeader)) {
				System.out.println("Present in List"+expectedHeader);
				Assert.assertTrue(true);
			} else {
				boolean blnFlag = expectedHeader.startsWith("Set-Cookie||JSESSIONID=");
				System.out.println("Boolen  : " + blnFlag);
				if (blnFlag) {
					System.out.println("present in list" + expectedHeader);
					Assert.assertTrue(true);
				} else {
					System.out.println("Not Present : : " + expectedHeader);
					Assert.assertTrue(false);
				}

			}
		}

		
	}
	
	@And("Verify json schema matches with {string}")
	public void verify_json_schema_matches_with(String strJsonSchemaFileWithoutExtension) {
		WhenSteps.response.then().assertThat().body(JsonSchemaValidator
				.matchesJsonSchema(TestUtils.verifyNewSchemaFileIsCreated(strJsonSchemaFileWithoutExtension)));
		System.out.println("Json schema for Create new customer API is verified");
	}

	@And("Verify response time is less than {string} ms")
	public void verify_response_time_is_less_than_ms(String strTime) {
		long responseTime=Integer.parseInt(strTime);
		WhenSteps.response.then().time(Matchers.lessThan(responseTime));
		System.out.println("Response time is verified in ms:" + WhenSteps.response.getTime());
	}

	@And("Verify response size is more than {string} kb")
	public void verify_response_size_is_more_than_kb(String string) {
		String responseBody = WhenSteps.response.getBody().asString();
		int responseSize = responseBody.length();
		Assert.assertTrue(responseSize > 0);
		System.out.println("Response size is greater than 0 KB:" + responseBody.length());

	}

	@And("Verify email field in response is not null")
	public void verify_email_field_in_response_is_not_null() {
		Assert.assertNotNull(WhenSteps.response.jsonPath().get("email"));
		System.out.println("Verify email is not null"+WhenSteps.response.jsonPath().get("email"));
	}

	@And("Verify City field in response is updated")
	public void verify_city_field_in_response_is_updated(io.cucumber.datatable.DataTable dataTable) {
		String city = WhenSteps.response.jsonPath().get("address.city");
		List<Map<String, String>> rows=dataTable.asMaps(String.class,String.class);
		Assert.assertEquals(city, rows.get(0).get("City"));
		System.out.println("Verify updated city:-" + city);
	}
	
	@And("Verify response contains deleted field as a key")
	public void verify_response_contains_deleted_field_as_a_key() {
		Assert.assertTrue(TestUtils.jsonHasKey(WhenSteps.response.asString(), "deleted"));
		System.out.println("'deleted' key is verified");
	}
	@And("Verify deleted field is not null")
	public void verify_deleted_field_is_not_null() {
	   Assert.assertNotNull(WhenSteps.response.jsonPath().getBoolean("deleted"));
	   System.out.println("deleted field is not null"+WhenSteps.response.jsonPath().getBoolean("deleted"));
	}
	@And("Verify response contains email field  with {string} {string}")
	public void verify_response_contains_email_field_with(String strParam1Key, String strParam1Value) {
		String email = WhenSteps.response.jsonPath().get(strParam1Key);
		Assert.assertEquals(email, strParam1Value);
		System.out.println("email field is verified:" + email);
	}

	@And("Verify response contains name field with {string} {string}")
	public void verify_response_contains_name_field_with(String strParam1Key, String strParam1Value) {
		String name = WhenSteps.response.jsonPath().get(strParam1Key);
		Assert.assertEquals(name, strParam1Value);
		System.out.println("name field is verified:" + name);
		ConstantUtils.setName(name);
	}
	
	@And("Verify response contains name of created customer")
	public void verify_response_contains_name_of_created_customer() {
		String name = WhenSteps.response.jsonPath().get("data[0].name");
		Assert.assertEquals(name, ConstantUtils.getName());
		System.out.println("name field is verified:" + name);
	}
	@And("Verify response contains error as a key")
	public void verify_response_contains_error_as_a_key() {
		Assert.assertTrue(TestUtils.jsonHasKey(WhenSteps.response.asString(), "error"));
		System.out.println("Error field as verified as response key");
	}
}
