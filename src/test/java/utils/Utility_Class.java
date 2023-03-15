package utils;

import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utility_Class {
	
	protected static RequestSpecification req;
	
	static PrintStream responseLog;
	static RequestSpecification reqSpec;
	static ResponseSpecification resSpec;
	
	public RequestSpecification requestSpecBuilder() throws Exception {
		
		if(reqSpec == null) {
		
			responseLog = new PrintStream(new FileOutputStream("Response-Logs.txt"));
			
			reqSpec = new RequestSpecBuilder().
					setBaseUri("https://restful-booker.herokuapp.com").
					addHeader("Content-Type", "application/json").
					addFilter(RequestLoggingFilter.logRequestTo(responseLog)).
					addFilter(ResponseLoggingFilter.logResponseTo(responseLog)).
					build();
			
			return reqSpec;
			
		}
		
		return reqSpec;
		
	}
	
	public ResponseSpecification responseSpecBuilder() {
		
		resSpec = new ResponseSpecBuilder().
		expectHeader("Server", "Cowboy").
		expectHeader("Content-Type","application/json; charset=utf-8").
		expectStatusCode(200).
		build();
		
		return resSpec;
		
	}

}
