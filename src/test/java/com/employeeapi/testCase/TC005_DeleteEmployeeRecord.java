package com.employeeapi.testCase;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_DeleteEmployeeRecord extends Testbase {
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void deleteEmployees()throws InterruptedException{
		
		logger.info("************************Started TC005_deleteEmployeeRecord************************");
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		
		//get the json object Instance from the response time
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//capture id
		String empID= jsonPathEvaluator.getString("[0].id");
		response = httpRequest.request(Method.DELETE,"/delete/"+empID);
		
		
		Thread.sleep(3);
	
	}
	@Test
	void checkResponseBody() {
		logger.info("************Check response body***************");
		String responsebody = response.getBody().asString();
		logger.info("respose body is " + responsebody);
		Assert.assertEquals(responsebody.contains("Successfully! Record has been deleted"), true);

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
