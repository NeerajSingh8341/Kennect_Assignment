package com.Kennect.gui.web.pageUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

public class Utilities {
	
	WebDriver driver;

	public Utilities(WebDriver rdriver) {
		this.driver = rdriver;
		}
	
	
	public void waitFor(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception er) {
			er.printStackTrace();
		}
	}
	public static String getReportname() {
		Date date = new Date();
		SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");
		String time = formatTime.format(date);
//		System.out.println(time);
		return time;

	}

	public static String getFolderName() {
		Date currentDate = new Date();
//		System.out.println(currentDate);
		String mString = String.valueOf(currentDate);
		String[] arr = mString.split(" ");
//		System.out.println(arr[2]);
		String FNAME = arr[0] + arr[1] + arr[2];
//		System.out.println(FNAME);
		return FNAME;

	}
	
	
	
}
