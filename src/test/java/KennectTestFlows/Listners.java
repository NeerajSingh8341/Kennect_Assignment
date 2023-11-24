package KennectTestFlows;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Kennect.gui.web.pageUtils.framework;
import com.Kennect.gui.web.pageobjects.Logoutpage;
import com.aventstack.extentreports.Status;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class Listners extends BaseClass implements ITestListener {
	
//	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("------------------------------------Test Started---------------------------");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		framework.test =framework.extentTest.createNode("To check user User Logged out successfully  ");
		Logoutpage signout=new Logoutpage(driver);
		signout.logoutmethod();
		framework.test.log(Status.PASS, " User Logged out  successfully ");
		framework.extent.flush();
		System.out.println("------------------------------------Test Passed---------------------------");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		framework.extentTest.log(Status.FAIL, result.getTestName());
		
		String Fname=getScreenshotname();

		String methodname = result.getName(); 

//			this statement take ss
	        Screenshot entirePageScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
//		copy to store the ss
	        File fullPageScreenshotFile = new File(System.getProperty("user.dir") + "\\Reports\\FailScreenShots\\"+methodname+Fname);
	        try {
				ImageIO.write(entirePageScreenshot.getImage(), "png", fullPageScreenshotFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//	      
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        framework.extent.flush();
	        driver.close();

		System.out.println("------------------------------------Test Failed---------------------------");

	}

	@Override
	public void onFinish(ITestContext context) {
		framework.extent.flush();
	}

}
