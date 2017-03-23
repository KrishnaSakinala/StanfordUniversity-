package base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import config.ConstantsClass;


public  class BaseTest 
{
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;
	
	@BeforeSuite
	@Parameters("browser")
	public void setUp(String browserName)
	{
		report=new ExtentReports(System.getProperty("user.dir") +"/test-output/HTMLResultReport.html");
		report.addSystemInfo("Host Name", "Automation User");
		report.addSystemInfo("Environment", "QA");
		report.addSystemInfo("User Name", "Selenium User");
		report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
    
		if(driver==null)
		{
			if(browserName.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
				driver=new FirefoxDriver();				
			}
			else if(browserName.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				driver= new ChromeDriver();
			}
			driver.manage().window().maximize();	
			driver.get(ConstantsClass.APP_URL);
		}
	}
		
	@AfterMethod
	public void generateReport(ITestResult result) throws Exception
	{
		if(ITestResult.FAILURE==result.getStatus())
		{
			String screenPath=captureFailedScreenshot(result.getName());
			String image=logger.addScreenCapture(screenPath);
			logger.log(LogStatus.FAIL, result.getName(), image);			
			logger.log(LogStatus.FAIL, result.getThrowable());
		}		
		report.endTest(logger);		
	} 
	
	@AfterSuite
	public void reportClose()
	{		
		report.flush();
		report.close();	
		driver.close();
	}
	
	public String captureFailedScreenshot(String screenName) throws IOException
	{
		String currentDateTime= getDateTime();
		/*Random rand= new Random();
		int number=rand.nextInt(10000);*/
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE) ;
		String destination=System.getProperty("user.dir")+"\\Screenshots\\"+screenName+" "+currentDateTime+".png";
		File dest=new File(destination);
		FileUtils.copyFile(srcFile, dest);
		return destination;
	}	
	
	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String currentDateTime = dateFormat.format(date);
		return currentDateTime;
	}
}
