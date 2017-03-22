package testcases;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import pages.Register;
import pages.WebTable;
import base.BaseTest;

public class UserRegistrationForm extends BaseTest
{
	@Test(priority=1)
	public void verifyUserRegistrationForm()
	{
		Register register = PageFactory.initElements(driver, Register.class);
		logger=report.startTest("Verify user Registration Test Case");
		register.fillRegistrationform();
		logger.log(LogStatus.INFO, "User Registration Test Case Has been Verified");
		logger.log(LogStatus.PASS, "User Regisrtation Test Case Has been Passed");
	}
	
	@Test(priority=2)
	public void verifyRegisteredUserInWebTable()
	{
		WebTable webtable = PageFactory.initElements(driver, WebTable.class);
		logger=report.startTest("Verify Registered user is displayed in Web Table Test Case");
		webtable.registeredUserDisplauyedInWebTable();
		logger.log(LogStatus.INFO, "Verify Registered user is displayed in Web Table Test Case Has been Verified");
		logger.log(LogStatus.PASS, "Verify Registered user is displayed in Web Table Test Case Has been Passed");
	}
}
