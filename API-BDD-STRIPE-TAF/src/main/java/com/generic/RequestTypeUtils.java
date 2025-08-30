package com.generic;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestTypeUtils {

	private static String accessToken;
	private static String baseURI = "https://44.197.194.76:8080";
//	private static String basePath = "/api";
//	private static String randomUserId;
	private static String randomId;
	public static ConfigProperties configProperties;

	public RequestTypeUtils() {
	}

	public static RequestSpecification getRequestSpecification(String baseURL) {
		try {
			RequestSpecification req = new RequestSpecBuilder().setBaseUri(baseURL)
					.addHeader("Content-Type", "application/json").build();
			return req;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public static Response doPostRequest(RequestSpecification req, String jsonFileName, String endPoint) {
		Response response = given().relaxedHTTPSValidation().spec(req)
				.body(TestUtils.generateStringFromResource(jsonFileName)).post(endPoint);
		return response;
	}
	
	public static Response doPostRequestWithHeaders(String strToken, String jsonFileName,String basePathAndEndpoint) {
		Response response = null;
		try {
			response = given().relaxedHTTPSValidation()
					.header("Content-Type", "application/json")
					.header("token", strToken)
					.body(jsonFileName)
					.post(basePathAndEndpoint);
			response.prettyPrint();
			return response;
		} catch (Exception exception) {
			System.out.println("I got exception : "+exception.getMessage());
			return response;
		}
	}
	
	
	

	public static String getAccessToken() {
		return accessToken;
	}

	public static void setAccessToken(String accessToken) {
		RequestTypeUtils.accessToken = accessToken;
	}

	public static Response doGETRequest(String strAccessToken, String endPoint) {
		Response response = given().header("Authorization", "Bearer " + strAccessToken)
				.header("Content-Type", "application/json").get(baseURI + endPoint);
		return response;
	}

	public static String createRandomIdFromResponse(Response response) {
		int arraySize = response.jsonPath().getInt("data.size()");
		int index = (int) (arraySize * Math.random());
		randomId = response.jsonPath().get("data[" + index + "]._id");
		return randomId;
	}

	public static String getRandomId() {
		return randomId;
	}

	public static void setRandomId(String randomId) {
		RequestTypeUtils.randomId = randomId;
	}

	public static void verifyResponseIdSameAsRandomIdProvided(Response response) {
		int arraySize = response.jsonPath().getInt("data.size()");
		for (int i = 0; i < arraySize; i++) {
			String userId = response.jsonPath().get("data[" + i + "]._id");
			Assert.assertEquals(userId, getRandomId());
		}
	}

	public static String createAndGetToken() {
		try {
			Response response = given().relaxedHTTPSValidation().baseUri("https://44.197.194.76:8080")
					.header("Content-Type", "application/json")
					.body(TestUtils.generateStringFromResource("LoginDetails.json")).post("/restapi/login");
			String token = response.jsonPath().get("token");
			System.out.println("Create and Get Token");
			response.prettyPrint();
			return token;
		} catch (Exception exception) {
			System.out.println("I got exception while generating access token - " + exception.getMessage());
			return "";
		}
	}

	public static Response doPostLoginRequest(RequestSpecification req, String strBody, String endPoint) {
		Response response = given().relaxedHTTPSValidation().spec(req).body(strBody).post(endPoint);
		return response;
	}
	

	//Stripe API
	public static Response doPostCreateCustomerRequest(RequestSpecification req,String strParam1Key,String strParam1Value,String strParam2Key,String strParam2Value,String endPoint) {
		Response response = given().spec(req)
				.auth().basic(ConstantUtils.Valid_Secret_Key, "")
				.formParam(strParam1Key, strParam1Value)
				.formParam(strParam2Key, strParam2Value)
				.post(endPoint);
		
		return response;

	}
	
	public static RequestSpecification addRequestSpecification(String baseURL) {
		try {
			RequestSpecification req = new RequestSpecBuilder().setBaseUri(baseURL)
					.build();
			return req;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}


	public static Response doGetCustomerRequest(RequestSpecification req,String endPoint,String id) {
		Response response = given().spec(req)
				.auth().basic(ConstantUtils.Valid_Secret_Key, "")
				.get(endPoint + "/" +id);
		
		return response;

	}
	
	public static Response doDeleteCustomerRequest(RequestSpecification req,String endPoint,String id) {
		Response response = given().spec(req)
				.auth().basic(ConstantUtils.Valid_Secret_Key, "")
				.delete(endPoint + "/" +id);
		
		return response;

	}
	
	public static Response doPostCustomerRequestWithInvalidKey(RequestSpecification req,String endPoint) {
		Response response = given().spec(req)
				.auth().basic(ConstantUtils.Invalid_Secret_Key, "")
				.post(endPoint);
		
		return response;

	}
	
	public static Response doGetCustomerListRequest(RequestSpecification req,String endPoint) {
		Response response = given().spec(req)
				.auth().basic(ConstantUtils.Valid_Secret_Key, "")
				.get(endPoint);
		
		return response;

	}
}
