package com.Kennect.gui.web.pageUtils;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class framework extends Utilities {

	WebDriver driver;

	public framework(WebDriver rdriver) {
		super(rdriver);
		this.driver = rdriver;
	}

	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static ExtentTest test;


	static String FolderName = getFolderName();
	static String FileName = getReportname();

	public static void reports() {
		ExtentSparkReporter m1 = new ExtentSparkReporter(
				System.getProperty("user.dir") + "\\Reports\\" + FolderName + "\\" + FileName + ".html");

		m1.config().setDocumentTitle("Pathology Lab Management web application UI Testing");
		m1.config().setReportName("kennect Pathology Lab Management web application Testing Report");

		extent = new ExtentReports();
		extent.attachReporter(m1);
		extent.setSystemInfo("platform Name  		", "Web Application");
		extent.setSystemInfo("OS Name        		", "Microsoft Windows 11");
		extent.setSystemInfo("OS Version     		", "10.0.22621");
		extent.setSystemInfo("Automation Tool		", "Selenium");
		extent.setSystemInfo("java version          ", "17.0.6");
		extent.setSystemInfo("QA Automation Engineer", "Neeraj Singh");
		extent.setSystemInfo("Company Name      	", "Kennect Inc. Pvt Ltd");
		extent.setSystemInfo("Project Name			", "Pathology Lab Management");

	}

}
