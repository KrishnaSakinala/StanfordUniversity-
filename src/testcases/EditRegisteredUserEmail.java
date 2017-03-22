package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.WebTable;
import base.BaseTest;

import com.relevantcodes.extentreports.LogStatus;

public class EditRegisteredUserEmail extends BaseTest
{
	@Test
	public void verifyEditRegisteredUserEmail() throws Exception
	{
		WebTable webtable = PageFactory.initElements(driver, WebTable.class);
		logger=report.startTest("Verify Edit Registered user email in Web Table Test Case");
		webtable.editFeildValueInWebTable();
		logger.log(LogStatus.INFO, "Verify Edit Registered user email in Web Table Test Case Has been Verified");
		logger.log(LogStatus.PASS, "Verify Edit Registered user email in Web Table Test Case Has been Passed");
	}
}
