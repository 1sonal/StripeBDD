package com.generic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;


public class FileFunctions {
	
	public static final String className = FileFunctions.class.getName();
	
	
	// static utility methods , Implemented by internal singleton for testability
	private static FileFunctionsSingleton singleton;
	
	private static void initSingleton(){
		singleton = FileFunctionsSingleton.getInstance();
	}
	
	public static void restoreSingleton(){
		FileFunctionsSingleton.instance = null;
		initSingleton();
	}
	
	static{
		initSingleton();
	}
	// Implementation of static methods, for testing purpose
	
	public static class FileFunctionsSingleton{
		
		private static FileFunctionsSingleton instance;
		
		private FileFunctionsSingleton(){
			
		}
		
		public static FileFunctionsSingleton getInstance(){
			if(instance==null){
				synchronized (FileFunctionsSingleton.class) {
					if(instance==null){
						instance = new FileFunctionsSingleton();
					}
				}
			}
			return instance;
			
		}
			
		public List<String> readFileIntoList(InputStream fileStream) throws IOException{
			
			List<String> stringList = new ArrayList<>();
			BufferedReader br = new BufferedReader(new InputStreamReader(fileStream,StandardCharsets.UTF_8));
			String lineIn = br.readLine();
			while (lineIn != null) {
				stringList.add(lineIn);
				lineIn = br.readLine();
			}
			br.close();
			return stringList;
		}
		
		public List<String> readFileIntoList(String fileName) throws Exception{
			// If Blank just return the empty list	
			if(StringUtils.isBlank(fileName)){
				return new ArrayList<>();
			}
			try(FileInputStream fis = new FileInputStream(fileName)){
				return readFileIntoList(fis);
			
			} catch (IOException ioException) {
				System.out.println("Problem reading file < " + fileName + ">"+className + " readFileIntoList");
				ioException.printStackTrace();
				throw ioException;
			}
		}
		
	}
	
	public static List<String> readFileIntoList(String fileName) throws Exception{
		if(StringUtils.isBlank(fileName)){
			//Blank file just return empty list
			return new ArrayList<>();
		}
		return singleton.readFileIntoList(fileName);
	}
	
	public static List<String> readFileIntoList(InputStream fileStream) throws IOException{
		
		return singleton.readFileIntoList(fileStream);
	}
	
	
	
}
