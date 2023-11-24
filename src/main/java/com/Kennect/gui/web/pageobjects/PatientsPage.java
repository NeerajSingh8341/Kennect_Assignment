package com.Kennect.gui.web.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Kennect.gui.web.pageUtils.Utilities;

public class PatientsPage extends Utilities {
	WebDriver driver;

	public PatientsPage(WebDriver rdriver) {
		super(rdriver);
		this.driver = rdriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//button//span[text()='Add Patient'])[1]")
	private WebElement ADDpatients;

	@FindBy(xpath = "//input[@name='name']")
	private WebElement name;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement email;

	@FindBy(xpath = "//input[@name='phone']")
	private WebElement phone;

	@FindBy(xpath = "//button//span[text()='General Details']")
	private WebElement genralBtn;

	@FindBy(xpath = "//input[@name='height']")
	private WebElement height;
	@FindBy(xpath = "//input[@name='weight']")
	private WebElement weight;
	@FindBy(xpath = "//input[@name='age']")
	private WebElement age;
	@FindBy(xpath = "//input[@name='systolic']")
	private WebElement systolic;
	@FindBy(xpath = "//input[@name='diastolic']")
	private WebElement diastolic;

	@FindBy(id = "mui-component-select-gender")
	private WebElement selectgender;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	private List<WebElement> Genderlist;

	@FindBy(xpath = "//button//span[text()='Add Tests']")
	private WebElement addtest;

	@FindBy(xpath = "//label[text()='Add tests for patient']")
	private WebElement addtest_forPatients;

	@FindBy(xpath = "//div[@id='patient-test-popup']/ul/li//div[text()]")
	private List<WebElement> testList;

	@FindBy(xpath = "//em/..")
	private WebElement discountopt;
	@FindBy(xpath = "//ul[@role='listbox']/li")
	private List<WebElement> discountlist;

	@FindBy(xpath = "(//label[text()='Select Labs from recommendation']/..//div/button)[2]")
	private WebElement labopt;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	private List<WebElement> lablist;
	@FindBy(xpath = "//label[text()='Doctor who recommended this test']")
	private WebElement Doctoropt;
	@FindBy(xpath = "//ul[@role='listbox']/li")
	private List<WebElement> doctorlist;

	@FindBy(id = "mui-component-select-doctor_commission")
	private WebElement doctor_commissionopt;
	@FindBy(xpath = "//ul[@role='listbox']/li")
	private List<WebElement> Commisionlist;

	@FindBy(xpath = "//h6/../following-sibling::div//button")
	private WebElement addequipment;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	private List<WebElement> equipmentlist;

	@FindBy(xpath = "//input[@placeholder='Required']")
	private WebElement quantity;

//	@FindBy(xpath = "//*[@class='MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiInputBase-input MuiInput-input']/span")
//	private WebElement equipdrpbtn;

	@FindBy(css = "[mode='add'] td")
	private WebElement equipdrpbtn;

	@FindBy(xpath = "//span[text()='Add Patient']")
	private WebElement Add_Patient;

	@FindBy(xpath = "//span[text()='check']")
	private WebElement checksave;
	
	
	@FindBy(xpath = "//span[text()='Dashboard']")
	private WebElement Dashboard;
	
	@FindBy(xpath = "//div[text()='Patient added successfully.']")
	private WebElement text;

	public void addPatients() {
		ADDpatients.click();
	}

	public void PatientContactdeatils(String Pname, String Pemail, String Pphone) {

		name.sendKeys(Pname);
		email.sendKeys(Pemail);
		phone.sendKeys(Pphone);
		genralBtn.click();

	}

	// p means patients
	public void Secondary_details(String Pheight, String Pweight, String Page, String Psystolic, String Pdiastolic,
			String Pgender) {

		height.sendKeys(Pheight);
		waitFor(1000);
		weight.sendKeys(Pweight);
		waitFor(1000);
		age.sendKeys(Page);
		waitFor(1000);
		systolic.sendKeys(Psystolic);
		waitFor(1000);
		diastolic.sendKeys(Pdiastolic);
		waitFor(1000);
		selectgender.click();
		for (int i = 0; i < Genderlist.size(); i++) {
			if (Genderlist.get(i).getText().equalsIgnoreCase(Pgender)) {
				Genderlist.get(i).click();
				break;
			}
		}

	}

	public void Test_Cost_Calculator(String TestName, String discount, String lab, String Doctor, String Commision,
			String testequipment, String testequipmentQuantity) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		waitFor(1000);
		addtest.click();
//		addtest_forPatients.click();
		Actions action = new Actions(driver);
		action.moveToElement(addtest_forPatients).click().perform();
		waitFor(1000);
		for (int i = 0; i < testList.size(); i++) {
//			System.out.println(testList.get(i).getText());
			if (testList.get(i).getText().contains(TestName)) {
				js.executeScript("arguments[0].click();", testList.get(i));
				break;

			}
		}
		// discount
		waitFor(2000);
		discountopt.click();
		waitFor(1000);
		for (int i = 0; i < discountlist.size(); i++) {
			waitFor(2000);
			if (discountlist.get(i).getText().equalsIgnoreCase(discount)) {
				discountlist.get(i).click();
				break;
			}
		}
		// Labs
		// label[text()='Select Labs from
		// recommendation']/following-sibling::div//div//button//span
//		labopt.click();
		action.moveToElement(labopt).click().perform();
		js.executeScript("arguments[0].click();", labopt);

		waitFor(1000);
		for (int i = 0; i < lablist.size(); i++) {
			if (lablist.get(i).getText().contains(lab)) {
//				lablist.get(i).click();
				js.executeScript("arguments[0].click();", lablist.get(i));

				break;
			}
		}
		// doctor
//		Doctoropt.click();
		action.moveToElement(Doctoropt).click().perform();
		waitFor(1000);
		for (int i = 0; i < doctorlist.size(); i++) {
			if (doctorlist.get(i).getText().contains(Doctor)) {
				doctorlist.get(i).click();
				break;
			}
		}
		// doctor commission

//		doctor_commissionopt.click();
		action.moveToElement(doctor_commissionopt).click().perform();
		waitFor(1000);
		for (int i = 0; i < Commisionlist.size(); i++) {
			if (Commisionlist.get(i).getText().contains(Commision)) {
				Commisionlist.get(i).click();
				break;
			}
		}

//		addequipment.click();
		js.executeScript("arguments[0].scrollIntoView();", addequipment);
		waitFor(1000);
		action.moveToElement(driver.findElement(By.xpath("//div[text()='Eqipment Name']"))).build().perform();
//		action.moveToElement(equipdrpbtn).click().perform();

		js.executeScript("arguments[0].click();", addequipment);
		System.out.println("clicked");
		waitFor(2000);

		String script = "var element = arguments[0];" + "var mouseEvent = document.createEvent('MouseEvents');"
				+ "mouseEvent.initEvent('mouseover', true, true);" + "element.dispatchEvent(mouseEvent);";

		equipdrpbtn.click();
		((JavascriptExecutor) driver).executeScript(script, equipdrpbtn);
		js.executeScript("arguments[0].click();", equipdrpbtn);


		waitFor(2000);
		quantity.sendKeys(Keys.chord(Keys.CONTROL, "a"), testequipmentQuantity);
		checksave.click();
		Add_Patient.click();
		waitFor(2000);
		System.out.println(text.getText());
		System.out.println("clicked");
		
		waitFor(7000);
		Dashboard.click();
		waitFor(1000);
		

	}

}
