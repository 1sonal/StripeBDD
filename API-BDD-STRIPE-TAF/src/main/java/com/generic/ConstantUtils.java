package com.generic;

public class ConstantUtils {
	
	
	private static String TOKEN="";
	private static String baseURI="";
	

	// Text File Data
	public static String TEXT_FILE_PATH=System.getProperty("user.dir")+"/src/test/resources/textFiles/%s";
	public static String TEST_DATA_TEXT_FILE_PATH=System.getProperty("user.dir")+"/src/test/resources/testdata/excel/%s";
	public static String TEXT_FILE_CLEAN_DIRECTORY=System.getProperty("user.dir") + "/src/test/resources/textFiles/";
	public static String HTML_TABLE_RESPONSE_DIRECTORY=System.getProperty("user.dir") + "/src/test/resources/html-response/";
	
	
	// End Points
	public static String LOGIN_BASE_PATH="/restapi";
	public static String LOGIN_ENDPOINT="/login";
	public static String LOGIN_INVALID_404_ENDPOINT="/login404";
	public static String BASE_PATH="/dbconsole";
	public static String CSVREPORT_ENDPOINT ="/getValidationCSVReport";
	public static String CSVREPORT_404_ENDPOINT ="/getValidationCSVReport404";
	public static String PAGINATED_DATA_TEMPLATE_LIST_ENDPOINT="/getPaginatedDataTemplateList";
	public static String PAGINATED_DATA_TEMPLATE_LIST_404_ENDPOINT="/getPaginatedDataTemplateList404";
	public static String SINGLE_RUN_TASK_ENDPOINT ="/runTaskResult";
	public static String SINGLE_RUN_TASK_404_ENDPOINT ="/runTaskResult404";
	public static String MULTI_RUN_TASK_ENDPOINT="/runTaskResult";
	public static String MULTI_RUN_TASK_404_ENDPOINT="/runTaskResult404";
	public static String VALIDATION_LIST_ENDPOINT="/validationslists";
	public static String VALIDATION_LIST_404_ENDPOINT="/validationslists404";
	public static String DQ_NULL_CHECK_RESULT_ENDPOINT="/getDQNullCheckResults";
	public static String DQ_NULL_CHECK_RESULT_404_ENDPOINT="/getDQNullCheckResults404";
	public static String DQ_NULL_CHECK_HISTORIC_DATA_ENDPOINT="/getDQNullCheckResults";
	public static String DQ_NULL_CHECK_HISTORIC_DATA_404_ENDPOINT="/getDQNullCheckResults404";
	
	//E2E
	public static String CREATE_CONNECTION_ENDPOINT="/createConnection";
	public static String CREATE_DATA_TEMPLATE_ENDPOINT="/createDataTemplate";
	public static String CREATE_VALIDATION_CHECK_ENDPOINT="/createValidationCheck";
	public static String GET_DQ_RECORD_ANOMALY_RESULTS="/getDQRecordAnomalyResults";
	public static String ACTIVATE_COLUMN_CHECK_TEMPLATE_ENDPOINT ="/activateColumnCheckInTemplate";
	
	

	
	
	
	
	public static String getBaseURI() {
		return baseURI;
	}

	public static void setBaseURI(String baseURI) {
		ConstantUtils.baseURI = baseURI;
	}

	public static String getTOKEN() {
		return TOKEN;
	}

	public static void setTOKEN(String tOKEN) {
		TOKEN = tOKEN;
	}
	

	//Stripe API
	
	public static String Base_Path="/v1";
	public static String Cust_Endpoint="/customers";
	public static String Valid_Secret_Key="sk_test_kzxPb2wjsBPiIh7QxZKqJU3a";
	public static String Invalid_Secret_Key="VSTL_asd";
	private static String CustId;
	private static String baseURL="";
	private static String name;
	

	

	public static String getCustId() {
		return CustId;
	}

	public static void setCustId(String custId) {
		CustId = custId;
	}

	public static String getBaseURL() {
		return baseURL;
	}

	public static void setBaseURL(String baseURL) {
		ConstantUtils.baseURL = baseURL;
	}
	
	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		ConstantUtils.name = name;
	}
}
