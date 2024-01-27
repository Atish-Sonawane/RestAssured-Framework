package com.employeeapi.testCase;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GetAllEmployees extends Testbase {
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException {

		logger.info("************Started TC001_GetAllEmployees***************");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		Thread.sleep(3);

	}

	@Test
	void checkResponseBody() {
		logger.info("************Check response body***************");
		String responsebody = response.getBody().asString();
		logger.info("respose body is " + responsebody);
		Assert.assertTrue(responsebody != null);

	}

	@Test
	void checkResponseCode() {
		logger.info("************Check status code***************");
		int statuscode = response.getStatusCode();
		logger.info("status code is " + statuscode);
		Assert.assertEquals(statuscode, 200);

	}

	@Test
	void checkResponseTime() {
		logger.info("************Check response time***************");
		long responsetime = response.getTime();
		logger.info("response time is " + responsetime);
		Assert.assertTrue(responsetime < 5000);

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
		Assert.assertEquals(servertype, "nginx/1.21.6");

	}

	@Test
	void checkContentEncoding() {
		logger.info("************Check content Encoding***************");
		String contentencoding = response.header("Content-Encoding");
		logger.info("content encoding is " + contentencoding);
		Assert.assertEquals(contentencoding, "gzip");

	}

	@Test
	void checkContentLength() {
		logger.info("************Check Content Length***************");
		String contentlength = response.header("Content-Length");
		logger.info("content lenght is " + contentlength);
		if (Integer.parseInt(contentlength) < 100)
			logger.warn("content length is less than 100");
		Assert.assertTrue(Integer.parseInt(contentlength) > 100);

	}

	@Test
	void checkCookies() {
		logger.info("************Check cookies***************");
		String cookies = response.getCookie("PHPSESSID");
		logger.info("cookies is" + cookies);

	}

	@AfterClass
	void tearDown() {
		logger.info("************finished TC001_GetAllEmployees***************");

	}

}
