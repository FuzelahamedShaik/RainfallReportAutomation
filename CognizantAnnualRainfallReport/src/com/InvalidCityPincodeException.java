package com;

public class InvalidCityPincodeException extends Exception {
	
	public  void InvalidCityPincodeException() {
		System.out.println("Invalid city pincode");
	}
	
	public void InvalidCityPincodeException(String err) {
		System.out.println(err);
	}
	
}
