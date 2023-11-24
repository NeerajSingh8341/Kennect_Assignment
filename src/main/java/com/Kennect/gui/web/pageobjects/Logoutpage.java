package com.Kennect.gui.web.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Kennect.gui.web.pageUtils.Utilities;

public class Logoutpage  extends Utilities{
	
	WebDriver driver;

	public Logoutpage(WebDriver rdriver)  {
		super(rdriver);
		this.driver = rdriver;
		PageFactory.initElements(driver, this);
	}

	
	
	@FindBy(xpath = "//div[text()='T']")
	private WebElement usertitle;
	
	@FindBy(xpath = "//span[text()='Sign out of Lab']")
	private WebElement Signout;
	
	
	public void logoutmethod() {
		waitFor(1000);
		usertitle.click();
		waitFor(1000);
		Signout.click();
	}
}
