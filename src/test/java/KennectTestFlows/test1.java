package KennectTestFlows;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test1 {

	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://gor-pathology.web.app/dashboard");
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@kennect.io");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Qwerty@1234");
		driver.findElement(By.xpath("//span[.='Login']")).click();
		
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement Element = driver.findElement(By.xpath("//div[.='Subtotal']"));
		js.executeScript("arguments[0].scrollIntoView();", Element);

		Thread.sleep(2000);

		// Add tests for patient:

		driver.findElement(By.cssSelector("[id='patient-test']")).click();

		Thread.sleep(2000);

		String testName = "Beans";

		Thread.sleep(2000);

		List<WebElement> listPatientTest = driver
				.findElements(By.xpath("//div[@id='patient-test-popup']/ul/li/div/div"));

		for (WebElement test : listPatientTest) {

		

			if (test.getText().contains(testName)) {

				test.click();
				break;
			}


		// DISCOUNT:
		Thread.sleep(2000);

		WebElement disclkElement = driver.findElement(By.xpath("//label[text()='Discount']/..//div/div"));

		action.moveToElement(disclkElement).click().perform();

		String discount = "20%";

		List<WebElement> listDiscount = driver.findElements(By.xpath("//ul[@role='listbox']/li"));

		for (WebElement discountElement : listDiscount) {

			if (discountElement.getText().contains(discount)) {

				discountElement.click();

			}
		}
	}
/*
//1. Login Page:
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@kennect.io");

		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Qwerty@1234");

		driver.findElement(By.xpath("//span[text()='Login']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;

//		/ 		 * //2. Home Page: // vaidation of todo list WebElement element =
		WebElement element = driver.findElement(By.xpath("//div[text()='Remaining work']/../following-sibling::ul/li"));

		if (element.isDisplayed()) {
			System.out.println("Element is visible on the web page.");
		} else {
			System.out.println("Element is not visible on the web page.");
		}
		Thread.sleep(3000);
//		  WebElement ele = driver.findElement(By.xpath(
//		  "//div[@class='MuiAutocomplete-endAdornment']/button/following-sibling::button//span[@class='MuiIconButton-label']"));
//		  js.executeScript("arguments[0].scrollIntoView();", ele); Thread.sleep(1000);
//		  js.executeScript("arguments[0].click();", ele);

		List<WebElement> paitentslist = driver
				.findElements(By.xpath("//div[text()='Remaining work']/../following-sibling::ul/li//span"));
		for (int i = 0; i < paitentslist.size(); i++) {
			if (paitentslist.get(i).getText().contains("Mr. Ratan Singh")) {
				js.executeScript("arguments[0].scrollIntoView();", paitentslist.get(i));
				js.executeScript("arguments[0].click();", paitentslist.get(i));
				Thread.sleep(3000);
				WebElement app = driver.findElement(By.xpath("//span[contains(text(),'Mr. Ratan Singh')]/../../following-sibling::div//span[text()='view']"));
				Thread.sleep(3000);
				js.executeScript("arguments[0].click();", app);

//			  System.out.println(paitentslist.get(i).getText());

				break;
			}
		}
	*/
	}

}
