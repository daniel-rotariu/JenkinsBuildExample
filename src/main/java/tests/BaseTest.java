package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;

public class BaseTest {
   
	WebDriver driver;
	String browser = System.getProperty("browser");


	@Parameters({"url"})
	@BeforeClass
	public void setup(String url) throws IOException {
		//System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("--headless"); // applicable to windows os only
		//driver = new ChromeDriver(options);
		//driver.manage().window().maximize();
	    FirefoxBinary firefoxBinary = new FirefoxBinary();
	    firefoxBinary.addCommandLineOptions("--headless");
	    FirefoxOptions firefoxOptions = new FirefoxOptions();
	    firefoxOptions.setBinary(firefoxBinary);
		
		if(browser != "" & browser != null ) {
			if(browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
			}
			else if(browser.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver(firefoxOptions);
				driver.manage().window().maximize();

			}
			
		}
		else {

			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		}
		
		
		
		driver.manage().window().setSize(new Dimension(1440, 900));

		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);	
		driver.get(url);
		
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
