package com.runners;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.generic.ConfigProperties;
import com.generic.ConstantUtils;
import com.generic.ExcelReaderUtilities;
import com.generic.ExtentReportManager;
import com.generic.TestUtils;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.restassured.RestAssured;

@CucumberOptions(plugin= {"json:target/BDD-Report/cucumber.json","pretty","html:target/BDD-Report/cucumber.html"},
features="src/test/resources/stripeFeatures",
glue="com/stepDefinitions",
monochrome=true,
dryRun = false,
tags="@TestDemo")
public class Stripe_BDD_Runner extends AbstractTestNGCucumberTests{
	public static ConfigProperties configProperties;
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();
	public static ExcelReaderUtilities excel = new ExcelReaderUtilities(
			"./src/test/resources/testdata/excel/Input Test Data.xlsx");

	@BeforeSuite
	public void setUp() {
		System.out.println("I'm in before suite");
		configProperties = ConfigFactory.create(ConfigProperties.class);
		RestAssured.baseURI = configProperties.getBaseURI();
		// RestAssured.basePath = configProperties.getBasePath();
		extent = ExtentReportManager.GetExtent("./target/AutomationReport.html");
		TestUtils.cleanDirectory(ConstantUtils.HTML_TABLE_RESPONSE_DIRECTORY);
	}	
	
}
