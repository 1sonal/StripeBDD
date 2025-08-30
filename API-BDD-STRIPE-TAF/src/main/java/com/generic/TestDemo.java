package com.generic;

public class TestDemo {

	public static void main(String[] args) {
		System.out.println("Previous Day :: "+TestUtils.getFutureOrBackDateInSpecifiedFormat("YYYY-MM-dd", -1));
		System.out.println("Date Format :: "+TestUtils.getDateInSpecifiedFormat("YYYY-MM-dd"));
	}

}
