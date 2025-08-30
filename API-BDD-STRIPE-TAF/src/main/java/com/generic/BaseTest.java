package com.generic;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;

public class BaseTest {

	public static ConfigProperties configProperties;
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();
	public static ExcelReaderUtilities excel = new ExcelReaderUtilities("./src/test/resources/testdata/excel/Input Test Data.xlsx");
	private static ExcelUtility excelResponse = new ExcelUtility("./src/test/resources/testdata/excel/APIResponseOutput.xlsx");

	@BeforeSuite
	public void setUp() {
		configProperties = ConfigFactory.create(ConfigProperties.class);
		RestAssured.baseURI = configProperties.getBaseURI();
	//	RestAssured.basePath = configProperties.getBasePath();
		extent=ExtentReportManager.GetExtent("./target/AutomationReport.html");
	}

	@BeforeTest
	public void beforeTest() {

	}

	@BeforeClass
	public void beforeClass() {
		ExtentTest classLevelTest = extent.createTest(getClass().getSimpleName());
		classLevelLog.set(classLevelTest);
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		ExtentTest test = classLevelLog.get().createNode(method.getName());
		testLevelLog.set(test);
	}

	@AfterMethod
	public void afterMethod(ITestResult testCaseResult) {
		if(testCaseResult.isSuccess())
		{
		  testLevelLog.get().pass("This test case got passed");	
		}
		else
		{
			testLevelLog.get().fail("This test case got failed");
		}
		extent.flush();
	}

	@AfterClass
	public void AfterClass() {

	}

	@AfterTest
	public void afterTest() {

	}

	@AfterSuite
	public void tearDown() {

	}
	
	/**
	 * Description : Return 2D Array object
	 * 
	 * @author : Sonal
	 */
	@DataProvider(name = "dp")
	public Object[][] getDataForReadAndWrite(Method method) {
		String sheetName = method.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][1];
		Hashtable<String, String> table = null;
		for (int rowNum = 6; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 5), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 6][0] = table;
			}
		}
		return data;
	}
	
	public static void setDataInExcel(String strSheetName, String strColmnName, int intRow, String strSetData,boolean blnFlag) {

		excelResponse.setCellData(strSheetName, strColmnName, intRow, strSetData,blnFlag);

	}

	
	
	
	

}
