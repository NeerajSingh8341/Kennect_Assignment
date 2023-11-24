package KennectTestFlows;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Kennect.gui.web.pageUtils.framework;
import com.Kennect.gui.web.pageobjects.HomePage;
import com.Kennect.gui.web.pageobjects.LandingPage;
import com.Kennect.gui.web.pageobjects.Logoutpage;
import com.Kennect.gui.web.pageobjects.PatientsPage;
import com.aventstack.extentreports.Status;
@Listeners(KennectTestFlows.Listners.class)

public class TestRunner extends BaseClass {
	String filePath = "C:\\Users\\91993\\Desktop\\BS\\Kennect_Assignment\\src\\test\\resources\\TestDATA\\KnnectTestData1.xlsx";
	String sheetName = "Sheet1";
	int rowIndex = 1; // Assuming data starts from the second row (index 1)
	// calling getdataby header
	Map<String, String> rowData = getDataFromexcel(filePath, sheetName, rowIndex);
	String PatientName = rowData.get("Patient Name");
	String PatientEmail = rowData.get("Patient Email");
	String PatientPhone = rowData.get("Patient Phone");
	String Height = rowData.get("Height");
	String Weight = rowData.get("Weight");
	String Gender = rowData.get("Gender");
	String Age = rowData.get("Age");
	String systolicBP = rowData.get("systolicBP");
	String diastolicBP = rowData.get("diastolicBP");
	String TestName = rowData.get("Test for Patient");
	String Discount = rowData.get("Discount");
	String Labs = rowData.get("Labs");
	String Doctor = rowData.get("Doctor");
	String DoctorCommision = rowData.get("Doctor Commision");
	String testequipment = rowData.get("test equipment");
	String testequipmentQuantity = rowData.get("test equipment Quantity");

	@Test(priority = 3)
	public void endtoend() {
		framework.extentTest = framework.extent.createTest("Scenario 4th");
		framework.test =framework.extentTest.createNode("Login Test");
		LandingPage p1 = new LandingPage(driver);
		p1.LandingpageAction(UserId, Password);
		framework.test.log(Status.PASS, " User Logged in successfully ");

		framework.test =framework.extentTest.createNode("User click on patient menu");
		HomePage p2 = new HomePage(driver);
		p2.clkonPatientsOpt();
		framework.test.log(Status.PASS, " User able to select patient from menu bar");

		framework.test =framework.extentTest.createNode("Add patient details and fill up a form and verify added paitent deatils in list of todos");
		PatientsPage p3 = new PatientsPage(driver);
		
		p3.addPatients();
		framework.test.log(Status.PASS, " User able to click on add patient");

		p3.PatientContactdeatils(PatientName, PatientEmail, PatientPhone);
		framework.test.log(Status.PASS, " User has been filled the PatientContactdeatils");

		p3.Secondary_details(Height, Weight, Age, systolicBP, diastolicBP, Gender);
		framework.test.log(Status.PASS, " User has been filled the Secondary_details");

		p3.Test_Cost_Calculator(TestName, Discount, Labs, Doctor, DoctorCommision, testequipment,
				testequipmentQuantity);
		framework.test.log(Status.PASS, " User has been filled the Testdetils which required");


		String addedPaitentName = p2.VerifyingAddedPatients(PatientName);
		System.out.println(addedPaitentName);
		assertEquals(addedPaitentName, PatientName);
		framework.test.log(Status.PASS, " Added paitent details are refelecting in a list of todos");
		

		framework.extent.flush();


	}

	@Test(priority = 1)
	public void Home_PageValidation() {
		framework.extentTest = framework.extent.createTest("Scenario 2nd");
		framework.test =framework.extentTest.createNode("Login Test");
		LandingPage p1 = new LandingPage(driver);
		p1.LandingpageAction(UserId, Password);
		framework.test.log(Status.PASS, " User Logged in successfully ");
//		
		framework.test =framework.extentTest.createNode("User able to see ToDoList and accessble the Cost Calculator for blood tests");
		HomePage p2 = new HomePage(driver);
		p2.view_oflist_todos_access_Cost_Calc(TestName);
		framework.test.log(Status.PASS, "Users are able to see ToDoList and able to access the Cost Calculator for blood tests");

		framework.extent.flush();

	}

	@Test(priority = 2)
	public void Cost_Calculator_for_Blood_TestValidation() {
		framework.extentTest = framework.extent.createTest("Scenario 3rd");
		framework.test =framework.extentTest.createNode("Login Test");
		LandingPage p1 = new LandingPage(driver);
		p1.LandingpageAction(UserId, Password);
		framework.test.log(Status.PASS, " User Logged in successfully ");
		
		framework.test =framework.extentTest.createNode("The user can select the desired tests and apply discounts is applicable.");
		HomePage p2 = new HomePage(driver);
		p2.CostCalulatormethod(TestName, Discount);
		framework.test.log(Status.PASS, " The user able to select the desired tests and apply discounts if applicable.");
		
		framework.test =framework.extentTest.createNode("To check user can select the desired tests and apply discounts if not applicable.");
		driver.navigate().refresh();
		String NADiscount="";
		p2.CostCalulatormethod(TestName, NADiscount);
		framework.test.log(Status.PASS, " The user able to select the desired tests and apply discounts is not applicable.");
		
		
		framework.extent.flush();
		

	}

	@Test(priority = 0)
	public void loginpageValidation() {
		framework.extentTest = framework.extent.createTest("Scenario 1st");
		framework.test =framework.extentTest.createNode("Login Test");
		LandingPage p1 = new LandingPage(driver);
		p1.LandingpageAction(UserId, Password);
		framework.test.log(Status.PASS, " User Logged in successfully ");
		
		

	}
	@Test(priority = 4)
	public void loginpageValidationNegtiveTest() {
		framework.extentTest = framework.extent.createTest("Scenario 1st");
		framework.test =framework.extentTest.createNode("Login Negative Test Case");
		LandingPage p1 = new LandingPage(driver);
		String message=p1.LandingpageAction2(UserId, Password2);
		assertEquals(message, errorMSG);
		
		framework.test.log(Status.FAIL, "The password is invalid or the user does not have a password.");
		framework.extent.flush();
		
//		assertTrue(false);

	}
	

}
