package com.Kennect.gui.web.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Kennect.gui.web.pageUtils.Utilities;

public class HomePage extends Utilities {
	public static String AddedPatientName;
	WebDriver driver;

	public HomePage(WebDriver rdriver) {
		super(rdriver);

		this.driver = rdriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Patients']")
	private WebElement patients;

	@FindBy(xpath = "//div[text()='Remaining work']/../following-sibling::ul/li")
	private WebElement todolist;

	@FindBy(xpath = "//div[text()='Remaining work']/../following-sibling::ul/li//span")
	private List<WebElement> paitentslist;

	@FindBy(xpath = "(//div[contains(@class,'jss')])[19]")
	private WebElement PName;

	@FindBy(xpath = "//div[@id='patient-test-popup']/ul/li/div/div")
	private List<WebElement> PTestList;

	@FindBy(xpath = "//div[@class='MuiAutocomplete-endAdornment']/button/following-sibling::button//span[@class='MuiIconButton-label']/*[@class='MuiSvgIcon-root']")
	private WebElement addtestP;

	@FindBy(xpath = "//label[text()='Discount']/..//div/div")
	private WebElement disclkElement;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	private List<WebElement> listDiscount;

	@FindBy(xpath = "//span[contains(text(),'UR PROTEIN:CREAT RATIO, URINE')]")
	private WebElement selectedTest;

	public void clkonPatientsOpt() {
		patients.click();
	}

	public void view_oflist_todos_access_Cost_Calc(String TestName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		if (todolist.isDisplayed()) {
			System.out.println("Element is visible on the web page.");
		} else {
			System.out.println("Element is not visible on the web page.");
		}
		waitFor(2000);
		js.executeScript("arguments[0].scrollIntoView();", addtestP);
		waitFor(2000);
		addtestP.click();
		for (int i = 0; i < PTestList.size(); i++) {
//			System.out.println(testList.get(i).getText());
			if (PTestList.get(i).getText().contains(TestName)) {
				js.executeScript("arguments[0].click();", PTestList.get(i));

				break;

			}
		}
		if (selectedTest.isSelected()) {
			System.out.println("User is able to choose Desired Test");
		}
	}

	public String VerifyingAddedPatients(String Patient_Name) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < paitentslist.size(); i++) {
			if (paitentslist.get(i).getText().contains(Patient_Name)) {
				js.executeScript("arguments[0].scrollIntoView();", paitentslist.get(i));
				js.executeScript("arguments[0].click();", paitentslist.get(i));
				waitFor(3000);
				WebElement app = driver.findElement(By.xpath("//span[contains(text(),'" + Patient_Name
						+ "')]/../../following-sibling::div//span[text()='view']"));
				waitFor(3000);
				js.executeScript("arguments[0].click();", app);

				AddedPatientName = PName.getText();

			}

		}
		return AddedPatientName;
	}

	public void CostCalulatormethod(String TestName, String discount) {
		
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		waitFor(2000);
		js.executeScript("arguments[0].scrollIntoView();", addtestP);

		waitFor(2000);

		// Add tests for patient:
		addtestP.click();

		waitFor(2000);

		for (int i = 0; i < PTestList.size(); i++) {
//			System.out.println(testList.get(i).getText());
			if (PTestList.get(i).getText().contains(TestName)) {
				js.executeScript("arguments[0].click();", PTestList.get(i));
				break;

			}
		}

		// DISCOUNT:
		waitFor(2000);
		if (discount.isBlank()) {
			System.out.println("Discount is not Applicable");

		} else {
			action.moveToElement(disclkElement).click().perform();

			for (WebElement discountElement : listDiscount) {

				if (discountElement.getText().contains(discount)) {

					discountElement.click();

				}
			}
		}
	}

}