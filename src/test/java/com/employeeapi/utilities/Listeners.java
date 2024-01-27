package com.employeeapi.utilities;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportname = "Test-Api-Teport"+timestamp+".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/"+reportname);

		htmlReporter.config().setDocumentTitle("Automation RFeport");
		htmlReporter.config().setReportName("Functional Report");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("hostname", "LocalHost");
		extent.setSystemInfo("Envirinment", "QA");
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Tester Name", "Atish");
		extent.setSystemInfo("Browser", "Chrome");
	}
	
	public void onTestSuccess(ITestResult result) {
		test= extent.createTest(result.getName());
		test.log(Status.PASS, "Test case PASSED is"+result.getName());
	}
	public void onTestFailure(ITestResult result) {
		test= extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is"+result.getName());
		test.log(Status.FAIL, "Test case FAILED is"+result.getThrowable());
	}

	public void onTestSkip(ITestResult result) {
		test= extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPPED is"+result.getName());
	}
	public void onFinish(ITestResult testContext) {
		extent.flush();
	}	

}
