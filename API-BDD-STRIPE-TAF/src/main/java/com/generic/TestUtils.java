package com.generic;

import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;
//import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
//import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.testng.Assert;


import io.restassured.response.Response;

public class TestUtils {

	public static boolean jsonHasKey(String json, String key) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			boolean flag = jsonObject.has(key);
			return flag;
		} catch (Exception exception) {
			System.out.println("I got exception - " + exception.getMessage());
			return false;
		}
	}
	
	public static JSONObject  getJsonObject(String responseAsString) {
		try {
			JSONObject jsonObject = new JSONObject(responseAsString);
			return jsonObject;
		} catch (Exception exception) {
			System.out.println("I got exception - " + exception.getMessage());
			return null;
		}
	}
	
	
	

	public static String getCurrentDateWithTimeStamp() {
		try {
			Date date = new Date();
			String strDateTimeStamp = new Timestamp(date.getTime()).toString();
			return strDateTimeStamp;
		} catch (Exception exception) {
			System.out.println("I got exception - " + exception.getMessage());
			return null;
		}
	}

	public static String generateStringFromResource(String jsonFileName) {
		try {
			return new String(Files.readAllBytes(
					Paths.get(System.getProperty("user.dir") + "/src/test/resources/jsonFiles/" + jsonFileName + "")));
		} catch (Exception exception) {
			System.out.println("I got exception - " + exception.getMessage());
			return null;
		}
	}
	
	public static String getCreateDataTemplatePayload(String jsonFileName) {
		try {
			 JSONObject jasonObject  = new JSONObject(jsonFileName);
			 String dataTemplateName ="TestTemp_"+TestUtils.getRandomStringWithNumbers(5);
			 Pojo.setTemplateName(dataTemplateName);
			 jasonObject.put("dataTemplateName", dataTemplateName);
			 jasonObject.put("idDataSchema", Pojo.getIdDataSchema());
			 String jsonString = jasonObject.toString();
			 System.out.println("New Json :: "+jsonString);
			 return jsonString;
		} catch (Exception exception) {
			System.out.println("I got exception while updating payload - "+exception.getMessage());
			return "";
		}
	}
	
	public static String getCreateValidationCheckPayload(String jsonFileName) {
		try {
			 JSONObject jasonObject  = new JSONObject(jsonFileName);
			 jasonObject.put("name", Pojo.getTemplateName());
			 jasonObject.put("selectedTables",Pojo.getTemplateId());
			 String jsonString = jasonObject.toString();
			 System.out.println("New Json :: "+jsonString);
			 return jsonString;
		} catch (Exception exception) {
			System.out.println("I got exception while updating payload - "+exception.getMessage());
			return "";
		}
	}

	
	public static String getRunTaskResultPayload(String jsonFileName) {
		try {
			 JSONObject jasonObject  = new JSONObject(jsonFileName);
			 jasonObject.put("appIds", Pojo.getIdApp());
			 String jsonString = jasonObject.toString();
			 System.out.println("New Json :: "+jsonString);
			 return jsonString;
		} catch (Exception exception) {
			System.out.println("I got exception while updating payload - "+exception.getMessage());
			return "";
		}
	}

	public static String getDQNullCheckResultPayload(String jsonFileName) {
		try {
			 JSONObject jasonObject  = new JSONObject(jsonFileName);
			// jasonObject.put("idApp", Pojo.getIdApp());
			 jasonObject.put("fromDate",TestUtils.getFutureOrBackDateInSpecifiedFormat("YYYY-MM-dd", -1));
			 jasonObject.put("toDate",TestUtils.getDateInSpecifiedFormat("YYYY-MM-dd"));
			 String jsonString = jasonObject.toString();
			 System.out.println("New Json :: "+jsonString);
			 return jsonString;
		} catch (Exception exception) {
			System.out.println("I got exception while updating payload - "+exception.getMessage());
			return "";
		}
	}
	
	public static String getDQRecordAnomalyResultsPayload(String jsonFileName) {
		try {
			 JSONObject jasonObject  = new JSONObject(jsonFileName);
			// jasonObject.put("idApp", Pojo.getIdApp());
			// jasonObject.put("fromDate",TestUtils.getFutureOrBackDateInSpecifiedFormat("YYYY-MM-dd", -1));
			// jasonObject.put("toDate",TestUtils.getDateInSpecifiedFormat("YYYY-MM-dd"));
			 String jsonString = jasonObject.toString();
			 System.out.println("New Json :: "+jsonString);
			 return jsonString;
		} catch (Exception exception) {
			System.out.println("I got exception while updating payload - "+exception.getMessage());
			return "";
		}
	}

	public static String getActivateColumnCheckInTemplatePayloadForNullCheck(String jsonFileName) {
		try {
			 JSONObject jasonObject  = new JSONObject(jsonFileName);
			 jasonObject.put("idApp", Pojo.getIdApp());
			 String jsonString = jasonObject.toString();
			 System.out.println("New Json :: "+jsonString);
			 return jsonString;
		} catch (Exception exception) {
			System.out.println("I got exception while updating payload - "+exception.getMessage());
			return "";
		}
	}
	
	public static String getActivateColumnCheckInTemplatePayloadForAnomalyCheck(String jsonFileName) {
		try {
			 JSONObject jasonObject  = new JSONObject(jsonFileName);
			 jasonObject.put("idApp", Pojo.getIdApp());
			 String jsonString = jasonObject.toString();
			 System.out.println("New Json :: "+jsonString);
			 return jsonString;
		} catch (Exception exception) {
			System.out.println("I got exception while updating payload - "+exception.getMessage());
			return "";
		}
	}
	
	
	
	

	public static String getFutureOrBackDateInSpecifiedFormat(String dateFormat, int NoOfFutureDay) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, NoOfFutureDay);
		String futureDate = new SimpleDateFormat(dateFormat).format(c.getTime()).toString();
		return futureDate;
	}
	
	
	
	public static String getResponseHeader(Response response, String headerName) {
		try {
			return response.getHeader(headerName);
		} catch (Exception exception) {
			System.out.println("I got exception : " + exception.getMessage());
			return "";
		}
	}

	// Added New Code

	public static boolean writeTextFileUsingFiles(String strInputTextToWrite, String strTextFilePath) {
		boolean blnFlag = false;
		try {
			List<String> lines = Arrays.asList(strInputTextToWrite);
			Path file = Paths
					.get(System.getProperty("user.dir") + "/src/test/resources/textFiles/" + strTextFilePath + ".txt");
			Files.write(file, lines, Charset.forName("UTF-8"));
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("I got Exception: " + ex.getMessage());
			return blnFlag;
		}
	}

	public static boolean renameOrConvertFileIntoDifferentFormat(String oldFileNameWithExtension,
			String newFileNameWithExtension) {
		try {
			File oldfile = new File(
					System.getProperty("user.dir") + "/src/test/resources/textFiles/" + oldFileNameWithExtension);
			File newfile = new File(
					System.getProperty("user.dir") + "/src/test/resources/textFiles/" + newFileNameWithExtension);
			if (oldfile.renameTo(newfile)) {
				System.out.println("File rename or conversion succesfully done");
			} else {
				System.out.println("File rename or conversion failed");
			}
			return true;
		} catch (Exception exception) {
			System.out.println("I got exception - " + exception.getMessage());
			return false;
		}
	}

	public static String getTextFileFirstLineDataUsingFiles(String strTextFilePath) {
		try {
			List<String> list = FileFunctions.readFileIntoList(strTextFilePath);
			String strReadData = list.get(0);
			return strReadData;
		} catch (Exception exception) {
			System.out.println("I got Exception: " + exception.getMessage());
			return null;
		}
	}

	public static void cleanDirectory(String directoryFolderPath) {
		try {
			File directory = new File(directoryFolderPath);
			for (File file : Objects.requireNonNull(directory.listFiles())) {
				if (!file.isDirectory()) {
					file.delete();
				}
			}
			System.out.println("clean text file directory");
		} catch (Exception exception) {
			System.out.println("I got exception while cleaning the directory - " + exception.getMessage());
		}
	}

	public static void createAndVerifyNewFileIsCreated(String strFileNameWithExtension) {
		File file = new File(
				System.getProperty("user.dir") + "/src/test/resources/textFiles/" + strFileNameWithExtension);
		boolean result;
		try {
			result = file.createNewFile();
			if (result) {
				System.out.println("file created " + file.getCanonicalPath());
			} else {
				System.out.println("File already exist at location: " + file.getCanonicalPath());
			}
		} catch (IOException ioException) {
			System.out.println("I got IO Exception while creating the new file - " + ioException.getMessage());
		}
	}

	public static List<String> getTestDataFromTextFile(String textFilePath) {
		List<String> listReturnValue;
		try {
			listReturnValue = FileFunctions.readFileIntoList(textFilePath);
			return listReturnValue;
		} catch (Exception exception) {
			System.out.println("I got exception while reading text file : " + exception.getMessage());
			return null;
		}
	}

	public static void verifyCSVFileColumnHeader(String textFileNameWithExtension, String[] strCSVHeader) {
		List<String> listReturnValue = null;
		listReturnValue = TestUtils.getTestDataFromTextFile(
				String.format(ConstantUtils.TEST_DATA_TEXT_FILE_PATH, textFileNameWithExtension));
		for (int index = 0; index < listReturnValue.size(); index++) {
			String strCSVColumnName = listReturnValue.get(index).trim();
			if (!strCSVColumnName.equals("")) {
				Assert.assertTrue(strCSVColumnName.equals(strCSVHeader[index]));
				System.out.println("CSV File Column Header Successfully Verified");
			} else {
				System.out.println("CSV File Column Header mistmatch");
			}
		}

	}

	public static boolean waitFor(int timeUnitSeconds) {
		try {
			Thread.sleep(TimeUnit.MILLISECONDS.convert(timeUnitSeconds, TimeUnit.SECONDS));
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	public static void verifyHTTPStatusCode(Response response, int statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}

	public static void verifyHeadersKeyAndValue(String textFileNameWithExtension, String strHeadersKeyAndValue) {
		List<String> listReturnValue = null;
		listReturnValue = TestUtils.getTestDataFromTextFile(
				String.format(ConstantUtils.TEST_DATA_TEXT_FILE_PATH, textFileNameWithExtension));
		System.out.println("List size ::::" + listReturnValue.size());
		for (int index = 0; index < listReturnValue.size(); index++) {
			String strHeadersKeyValue = listReturnValue.get(index).trim();
			if (!strHeadersKeyValue.equals("")) {
				System.out.println("Verify CSV column header- Expected Column Name :: " + strHeadersKeyValue
						+ " || Actual Column Name :: " + strHeadersKeyAndValue);

				if (strHeadersKeyAndValue.contains("Set-Cookie||JSESSIONID")) {
					Assert.assertTrue(strHeadersKeyValue.contains("Set-Cookie||JSESSIONID"));
				} else {
					Assert.assertTrue(strHeadersKeyValue.equals(strHeadersKeyAndValue));
				}

			} else {
				System.out.println("CSV file column header is null");
				Assert.assertTrue(false);
			}
		}

	}
	
	public static File getJsonSchemaFilePath(String jsonSChemaFileNameWithExtension) {
		File file=new File(
				System.getProperty("user.dir") + "/src/test/resources/jsonSchemaFiles/"+jsonSChemaFileNameWithExtension);
		return file;
	}
	
	public static void createNewFileForHTMLResponse(String strFileNameWithExtension) {
		File file = new File(
				System.getProperty("user.dir") + "/src/test/resources/html-response/" + strFileNameWithExtension);
		boolean result;
		try {
			result = file.createNewFile();
			if (result) {
				System.out.println("file created " + file.getCanonicalPath());
			} else {
				System.out.println("File already exist at location: " + file.getCanonicalPath());
			}
		} catch (IOException ioException) {
			System.out.println("I got IO Exception while creating the new file - " + ioException.getMessage());
		}
	}
	public static boolean writeTextFileUsingFilesForHTML(String strInputTextToWrite, String strTextFilePath) {
		boolean blnFlag = false;
		try {
			List<String> lines = Arrays.asList(strInputTextToWrite);
			Path file = Paths
					.get(System.getProperty("user.dir") + "/src/test/resources/html-response/" + strTextFilePath + ".txt");
			Files.write(file, lines, Charset.forName("UTF-8"));
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("I got Exception: " + ex.getMessage());
			return blnFlag;
		}
	}
	
	public static boolean renameOrConvertFileIntoHTML(String oldFileNameWithExtension,
			String newFileNameWithExtension) {
		try {
			File oldfile = new File(
					System.getProperty("user.dir") + "/src/test/resources/html-response/" + oldFileNameWithExtension);
			File newfile = new File(
					System.getProperty("user.dir") + "/src/test/resources/html-response/" + newFileNameWithExtension);
			if (oldfile.renameTo(newfile)) {
				System.out.println("File rename or conversion succesfully done");
			} else {
				System.out.println("File rename or conversion failed");
			}
			return true;
		} catch (Exception exception) {
			System.out.println("I got exception - " + exception.getMessage());
			return false;
		}
	}

	public static String getRandomStringWithNumbers(int lenght) 
	{
		String allowedChars = "abcdefghiklABCDEFGHIJKLMNOmnopqrstuvwxyz1234567890";
		String randomstring = "";
		for (int i=0; i < lenght; i++) 
		{
			int rnum = (int) Math.floor(Math.random() * allowedChars.length() );
			randomstring += allowedChars.substring(rnum,rnum+1);
		}
		return randomstring;
	}
	
	/*
	 *  @Method		: getDateInSpecifiedFormat
	 * @Description	: This method takes parameter of your required DateFormat Type Like: dd-mm-YYYY DD.MM.YYYY
	 * 					and in return it will give you today's date in specified date format
	 * @param		: dateFormat like : dd-MM-YYYY
	 * @author		: Framework Developer
	 * 
	 */
	public static String getDateInSpecifiedFormat(String dateFormat)
	   {
		   String current_date="";
			Date today = Calendar.getInstance().getTime();
		    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		    current_date = formatter.format(today);		
		   return current_date;
	   }
	
	
	public static File verifyNewSchemaFileIsCreated(String strSchemaFile) {
		File file=new File(
				System.getProperty("user.dir") + "/src/test/resources/jsonSchemaFiles/"+strSchemaFile+".json");
		return file;
	}
	
}
