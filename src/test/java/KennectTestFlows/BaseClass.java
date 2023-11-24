package KennectTestFlows;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Kennect.gui.web.pageUtils.framework;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	String UserId;
	String errorMSG;
	String Password;
	public static WebDriver driver;
	String Password2;

	@BeforeSuite
	public void relatedtoReports() {
		framework f1 = new framework(driver);
		f1.reports();
	}

//	@BeforeClass
	@BeforeMethod
	public void setup() throws Exception {
		String path = "./Configuration/Config.properties";
		FileInputStream fis = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fis);

		String Url = prop.getProperty("Url");
		UserId = prop.getProperty("UserId");
		Password = prop.getProperty("Password");
		Password2 = prop.getProperty("Password2");
		errorMSG = prop.getProperty("errorMSG");
		String browserName = prop.getProperty("BrowserName");

		if (browserName.contains("chrome")) {
			ChromeOptions option = new ChromeOptions();

			option.addArguments("--remote-allow-origins=*");
			option.addArguments("--disable-extensions");

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver(option);

		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
			EdgeOptions caps = new EdgeOptions();
			caps.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(caps);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(Url);
	}

	/*
	 * public String Datadriven() { // Example usage: Fetching data by headers
	 * String filePath=
	 * "C:\\Users\\91993\\Desktop\\BS\\Kennect_Assignment\\src\\test\\resources\\TestDATA\\KnnectTestData1.xlsx";
	 * String sheetName = "Sheet1"; int rowIndex = 1; // Assuming data starts from
	 * the second row (index 1) //calling getdataby header Map<String, String>
	 * rowData = getDataFromexcel(filePath, sheetName, rowIndex);
	 * System.out.println("Data extracted:"); String
	 * PatientName=rowData.get("Patient Name"); String
	 * PatientEmail=rowData.get("Patient Email"); String
	 * PatientPhone=rowData.get("Patient Phone"); String
	 * Height=rowData.get("Height"); String Weight=rowData.get("Weight"); String
	 * Gender=rowData.get("Gender"); String Age=rowData.get("Age"); String
	 * systolicBP=rowData.get("systolicBP"); String
	 * diastolicBP=rowData.get("diastolicBP"); String
	 * TestforPatient=rowData.get("Test for Patient"); String
	 * Discount=rowData.get("Discount"); String Labs=rowData.get("Labs"); String
	 * Doctor =rowData.get("Doctor "); String
	 * DoctorCommision=rowData.get("Doctor Commision"); String
	 * testequipment=rowData.get("test equipment"); String
	 * testequipmentQuantity=rowData.get("test equipment Quantity");
	 * 
	 * 
	 * 
	 * // for (Map.Entry<String, String> entry : rowData.entrySet()) { //
	 * System.out.println(entry.getKey() + ": " + entry.getValue()); // } }
	 */

	public static Map<String, String> getDataFromexcel(String filePath, String sheetName, int rowIndex) {
		Map<String, String> dataMap = new HashMap<>();

		try {
			FileInputStream inputStream = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheet(sheetName);

			Row headerRow = sheet.getRow(0);
			Row dataRow = sheet.getRow(rowIndex);

			for (int colIndex = 0; colIndex < headerRow.getLastCellNum(); colIndex++) {
				Cell headerCell = headerRow.getCell(colIndex);
				Cell dataCell = dataRow.getCell(colIndex);

				String header = headerCell.getStringCellValue().trim();
				String value = "";

				if (dataCell != null) {
					if (dataCell.getCellType() == CellType.STRING) {
						value = dataCell.getStringCellValue().trim();
					} else if (dataCell.getCellType() == CellType.NUMERIC) {
						value = String.valueOf(dataCell.getNumericCellValue()).trim();
					}
				}

				dataMap.put(header, value);
			}

			inputStream.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	public static String getScreenshotname() {
		Date d = new Date();
//        fileName= "Assertion"+"_"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		String fileName = "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
//        System.out.println(fileName);
		return fileName;

	}

}

/*
 * public static void main(String[] args) { // Example usage: Fetching data by
 * headers String filePath="C:\\Users\\91993\\Documents\\TestDataAppium.xlsx";
 * String sheetName = "Sheet1"; int rowIndex = 1; // Assuming data starts from
 * the second row (index 1) //calling getdataby header Map<String, String>
 * rowData = getDataByHeader(filePath, sheetName, rowIndex);
 * System.out.println("Data extracted:");
 * System.out.println(rowData.get("UserName"));
 * System.out.println(rowData.get("Lname"));
 * System.out.println(rowData.get("Pincode"));
 * System.out.println(rowData.get("Fname"));
 * System.out.println(rowData.get("Password"));
 * System.out.println(rowData.get("Country"));
 * System.out.println(rowData.get("State")); // for (Map.Entry<String, String>
 * entry : rowData.entrySet()) { // System.out.println(entry.getKey() + ": " +
 * entry.getValue()); // } } }
 */