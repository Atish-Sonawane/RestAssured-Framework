package com.employeeapi.testCase;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.Testbase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_PostEmployeeRecord extends Testbase{
	
	public static RequestSpecification httpRequest;
	public static Response response;
	
	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@BeforeClass
	void createEmployees()throws InterruptedException{
		
		logger.info("************************Started TC003_PostEmployeeRecord************************");
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		JSONObject request = new JSONObject();
		request.put("name", empName);
		request.put("salary", empSalary);
		request.put("age", empAge);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(request.toJSONString());
		
		response = httpRequest.request(Method.POST,"/create");
		
		Thread.sleep(3);
	
	}
	@Test
	void checkResponseBody() {
		logger.info("************Check response body***************");
		
		String responseBody = response.getBody().asString();
		logger.info("responsebody is:"+responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);

	}

	@Test
	void checkResponseCode() {
		logger.info("************Check status code***************");
		int statuscode = response.getStatusCode();
		logger.info("status code is " + statuscode);
		Assert.assertEquals(statuscode, 200);

	}

	@Test
	void checkReStatusLine() {
		logger.info("************Check status line***************");
		String statusline = response.getStatusLine();
		logger.info("status line is " + statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");

	}

	@Test
	void checkContentType() {
		logger.info("************checkContentType***************");
		String contenttype = response.getContentType();
		logger.info("content type is " + contenttype);
		Assert.assertEquals(contenttype, "application/json");

	}

	@Test
	void checkserverType() {
		logger.info("************Check server type***************");
		String servertype = response.header("Server");
		logger.info("content encoding is " + servertype);
		Assert.assertEquals(servertype, "Apache");

	}

	@Test
	void checkContentEncoding() {
		logger.info("************Check content Encoding***************");
		String contentencoding = response.header("Content-Encoding");
		logger.info("content encoding is " + contentencoding);
		Assert.assertEquals(contentencoding, "gzip");

	}

	@AfterClass
	void tearDown() {
		logger.info("************finished TC003_PostEmployeeRecord***************");

	}

	
}
