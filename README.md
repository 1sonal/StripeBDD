# **API-BDD-STRIPE-TAF**




•	API-BDD-STRIPE-TAF  makes it easier to write high quality API automation tests, with powerful reporting.<br>
•	Tests are written in BDD Gherkin format and it is represented as a living documentation in the test report.<br>
•	Application under test: https://44.197.194.76:8080<br>
-----------
#### Table of contents<br>
- [Prerequisite](#1-prerequisite) 
- [Framework Modules](#2-framework-modules)
- [How to execute the project?](#3-how-to-execute-the-project)
- [Where to see the execution reports?](#4-where-to-see-the-execution-reports)
- [Where to see the failed testcase execution report?](#5-where-to-see-the-failed-testcase-execution-report)

----------


#1. Prerequisite


* Eclipse IDE 
* Java 11 or higher
* Maven
* Cucumber Eclipse plugin
* TestNG
* Rest Assured



# 2. Framework Modules


The framework consists of following modules.
## a.  generic

 This folder contains all reusable methods. It consists of following classes.
+ BaseTest:-This class is used to initialize properties file,Extent reports and DataProvider
+ ConfigProperties:-This interface has methods to read configurations from properties file
+ ConstantUtils:-This class contains the reusable components like baseURI, basePath, endPoint, filePaths, token and their getter and setters.
+ DataProviderManager:-This class is used to get data from excel in the form of key and value.
+ ExcelReaderUtilities and ExcelUtility:- In order to manipulate excel files and do excel operations, ExcelReaderUtilities and ExcelUtility classes are used.
+ ExtentReportManager:- This class is responsible to get the extent report.
+ FileFunctions:- The utility functions related to file handling are written here.
+ HtmlTableUtils:-The utility functions for creating HTML table from json response are written here.
+ Pojo:- POJOs are used for increasing the readability and re-usability of a program. Generally, a POJO class contains variables and their Getters and Setters. The POJO class is an object class that encapsulates the Business logic.
+ RequestTypeUtils:-This class contains utility functions for API requests.
+ TestUtils:-This class contains the reusable functions.

## b.  Runners

 It consists of Stripe_BDD_Runner class. We can run tests using this test runner file for Cucumber. The test runner file should contain the path of the feature file and step definition file that we want to execute.

## c.  stepDefs 

Step definitions map the Gherkin steps to corresponding automation code. Here Given,When ,Then and And steps are stored in separate java files.The folder also consists of Hooks class which is responsible for storing the code which is to be executed before and after every scenario. 

## d.	Resources

It consists of following folders.
+ configXMLFiles:-The report template design file is stored here.
+ featureFiles:-Cucumber tests are written here using Gherkin syntax. The feature file is the entry point for the cucumber tests.
+ html-response:-Response in the form of HTML table is stored here.
+ jsonFiles:-The json payloads for all the tests are stored here.
+ jsonSchemaFiles:- The json schema files for the response of all the tests are stored here.
+ propertyFiles:-It has config.properties file.BaseURI is stored in config.properties file.
+ Testdata:-The input test data in the form of excel and text file is stored here.
+ testSuites:-The test suite xml file is stored here.
+ textFiles:-The output CSV file and text file are stored here.


# 3. How to execute the project?

Use maven command: clean test verify    

# 4. Where to see the execution reports?

target->cucumber-report->cucumber-html-reports-overview-features.html

# 5. Where to see the failed testcase execution report?

target->cucumber-report->cucumber-html-reports-overview-failures.html







