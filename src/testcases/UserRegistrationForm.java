package testcases;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.Register;
import pages.WebTable;
import base.BaseTest;

public class UserRegistrationForm extends BaseTest
{
	@Test(priority=2)
	public void verifyUserRegistrationForm() throws InterruptedException
	{
		Register register = PageFactory.initElements(driver, Register.class);
		logger=report.startTest("Verify user Registration Test Case");
		register.fillRegistrationform();
		/*WebTable webtable = PageFactory.initElements(driver, WebTable.class);
		logger=report.startTest("Verify Registered user is displayed in Web Table Test Case");
		webtable.registeredUserDisplauyedInWebTable();*/
		logger.log(LogStatus.INFO, "User Registration Test Case Has been Verified");
		logger.log(LogStatus.PASS, "User Regisrtation Test Case Has been Passed");
	}
	
	@Test(priority=1)
	public void verifyPhotoUpload()
	{
		Register register = PageFactory.initElements(driver, Register.class);
		logger=report.startTest("Verify user Registration Test Case");
		register.photoUpload();
		logger.log(LogStatus.INFO, "verifyPhotoUpload Sucess");
		logger.log(LogStatus.PASS, "verifyPhotoUpload Sucess");
	}
}
