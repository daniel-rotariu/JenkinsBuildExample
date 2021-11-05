package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;

public class BaseTest {
   
	public static WebDriver driver;
	
	//@Parameters({"url"})
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");	
		driver =  new ChromeDriver();
//		Log.info("Started the suite with Chrome browser");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		driver.get("https://keybooks.ro/");	
//		Log.info("Open the https://keybooks.ro/ ");

		//driver.get(url);
	}
		
	@AfterClass
	public void tearDown() throws InterruptedException {	
		
		driver.quit();
	}
	
	@AfterMethod
	public void recordFailure(ITestResult result) throws IOException {
		
		if(ITestResult.FAILURE == result.getStatus()) {
			
			TakesScreenshot poza = (TakesScreenshot)driver;
			File screenshot = poza.getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			Files.copy(screenshot, new File("screenshots/"+ result.getName()+ timeStamp +".png"));
			
		}
	}
	
	
}
