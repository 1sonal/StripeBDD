# **API-BDD-STRIPE-TAF**




•	API-BDD-STRIPE-TAF  makes it easier to write high quality automated tests, with powerful reporting.<br>
•	Tests are written in BDD Gherkin format and it is represented as a living documentation in the test report.<br>
•	Application under test: https://44.197.194.76:8080<br>
-----------
#### Table of contents<br>
- [Prerequisite](#1-prerequisite) 
- [Cucumber feature file](#2-cucumber-feature-file)
- [Cucumber Step Definition File](#3-cucumber-step-definition-file)
- [Cucumber Test Runner File](#4-cucumber-test-runner-file) 
- [Framework Modules](#5-framework-modules)
- [How to execute the project?](#6-how-to-execute-the-project)
- [Where to see the execution reports?](#7-where-to-see-the-execution-reports)
- [Where to see the failed testcase execution report?](#8-where-to-see-the-failed-testcase-execution-report)

----------


#1. Prerequisite


* Java 11 or higher
* Maven
* Cucumber Eclipse plugin
* TestNG


#2. Cucumber feature file


A feature file offers a high-level description of an Application Under Test (AUT).It is an entry point to the Cucumber tests. This is a file where you will describe your tests in Descriptive language (Like English). It is an essential part of Cucumber, as it serves as an automation test script as well as live documents. 
The extension of the feature file needs to be “.feature”.A simple feature file consists of the following keywords/parts.
+ Feature:The purpose of the Feature keyword is to provide a high-level description of a software feature and to group related scenarios.
+ Scenario: A scenario represents a specific test case or a behavior to be tested. It describes a particular situation or use case. Scenarios are defined using the Scenario keyword, followed by a description. 
+ Scenario Outline: A scenario outline allows for the description of multiple scenarios using a template with placeholders. It is useful when testing similar behaviors with different inputs. The Scenario Outline keyword is used, followed by a description and an Examples section.
+ Examples - A Scenario Outline must contain one or more Examples (or Scenarios) section(s). Its steps are interpreted as a template which is never directly run. Instead, the Scenario Outline is run once for each row in the Examples section beneath it (not counting the first header row).
The steps can use <> delimited parameters that reference headers in the examples table. Cucumber will replace these parameters with values from the table before it tries to match the step against a step definition
+ Description (optional) − Describe about feature under test.
+ Given − Prerequisite before the test steps get executed.
+ When − Specific condition which should match in order to execute the next step.
+ Then − What should happen if the condition mentioned in WHEN is satisfied.
+ AND - AND keyword is used to show conjunction between two conditions. AND can be used with any other keywords like GIVEN, WHEN and THEN.
+ * - Gherkin also supports using an asterisk (*) in place of any of the normal step keywords. This can be helpful when you have some steps that are effectively a list of things, so you can express it more like bullet points where otherwise the natural language of And etc might not read so elegantly.
+ BUT - BUT keyword is used to add negative type comments. It is good to use when your step describes a condition that is not expected. For example, when you expect some text or element to not be present on the page.
+ Rule - The purpose of the Rule keyword is to represent one business rule that should be implemented. It provides additional information for a feature. A Rule is used to group together several scenarios that belong to this business rule. A Rule should contain one or more scenarios that illustrate the particular rule.
+ Background - A Background allows you to add some context to the scenarios that follow it. It can contain one or more Given steps, which are run before each scenario, but after any Before hooks.A Background is placed before the first Scenario/Example, at the same level of indentation.<br><br>
There are a few secondary keywords as well:
+ """ (Doc Strings) - In some cases you might want to pass more data to a step than fits on a single line. For this purpose Gherkin has Doc Strings and Data Tables.
Doc Strings are handy for passing a larger piece of text to a step definition.They support three double-quote marks and three backticks as the delimiter.
+ | (Data Tables) - Data Tables are handy for passing a list of values to a step definition.
+ @ (Tags) - Tags are optional and can be added to scenarios or feature sections using the @ symbol. They provide metadata and can be used for categorization, filtering, or reporting purposes.
+ \# (Comments) - Comments can be added to the feature file using the # symbol. They are useful for providing additional context, explanations, or annotations

# 3. Cucumber Step Definition File


A Step Definition is a small piece of code with a pattern attached to it or in other words a Step Definition is a java method in a class with an annotation above it. An annotation followed by the pattern is used to link the Step Definition to all the matching Steps, and the code is what Cucumber will execute when it sees a Gherkin Step. Cucumber finds the Step Definition file with the help of the Glue code in Cucumber Options. 

# 4. Cucumber Test Runner File


We can run tests using a test runner file for Cucumber. It uses the @CucumberOptions Annotation to define the location of feature files, step definitions, reporting integrations, etc. It contains following options.
+ dryRun - dryRun option can either set as true or false. If it is set as true, it means that Cucumber will only check that every Step mentioned in the Feature File has corresponding code written in Step Definition file or not. So in case any of the functions are missed in the Step Definition for any Step in Feature File, it will give us the message
+ Monochrome - This option can either set as true or false. If it is set as true, it means that the console output for the Cucumber test are much more readable. And if it is set as false, then the console output is not as readable as it should be. 
+ features - Features Options helps Cucumber to locate the Feature file in the project folder structure. 
+ glue - it helps Cucumber to locate the Step Definition file.
+ Format - We use Cucumber format option to generate output or test results in different types of formats. 
Eg: HTML Report, JUnit or TestNG Report, Cucumber Report and So. 
+ strict -  We use Cucumber strict option to check if any step is not defined in step definition file. 
If any step is not defined in step definition file then it will stop an execution of program.
+ tags - We use Cucumber tags option when we have more number of scenarios in a single feature file which represents different purpose [Smoke, Sanity, Regression etc] in such cases we can make use tags option.
Eg: tags={ ” @ Smoke ” } >> It will pick only and only those scenarios which are tagged with Smoke in feature files.

# 5. Framework Modules


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

 It consists of DataBuck_BDD_Runner class. We can run tests using this test runner file for Cucumber. The test runner file should contain the path of the feature file and step definition file that we want to execute.

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


# 6. How to execute the project?

Use maven command: clean test verify    

# 7. Where to see the execution reports?

target->cucumber-report->cucumber-html-reports-overview-features.html

# 8. Where to see the failed testcase execution report?

target->cucumber-report->cucumber-html-reports-overview-failures.html







