package com.Kennect.gui.web.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Kennect.gui.web.pageUtils.Utilities;

public class LandingPage extends Utilities {
	WebDriver driver;

	public LandingPage(WebDriver rdriver) {
		super(rdriver);
		this.driver = rdriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='email']")
	private WebElement userName;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	@FindBy(xpath = "//span[text()='Login']")
	private WebElement Loginbtn;

	@FindBy(xpath = "//div[text()='The password is invalid or the user does not have a password.']")
	private WebElement ErrorMessage;

	public void LandingpageAction(String UserName, String Pswd) {
		userName.sendKeys(UserName);
		password.sendKeys(Pswd);
		Loginbtn.click();
		waitFor(2000);

		
	}
	public String LandingpageAction2(String UserName, String Pswd) {
		userName.sendKeys(UserName);
		password.sendKeys(Pswd);
		Loginbtn.click();
		String errorMSG=ErrorMessage.getText();
		waitFor(2000);
		
		return errorMSG;
	}

}
//1. Login Page:
//		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@kennect.io");
//
//		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Qwerty@1234");
//
//		driver.findElement(By.xpath("//span[text()='Login']")).click();