package testscripts;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.PredefinedActions;
import constant.ConstantValue;
import pages.LoginPage;
import utility.PropertyFileOperations;

public class TestBase {

	@BeforeMethod
	public void setUp() throws IOException {
		PropertyFileOperations fileOperations = new PropertyFileOperations(ConstantValue.CONFIGFILEPATH);
		String url = fileOperations.getValue("url");
		PredefinedActions.start(url);

		LoginPage loginPage = LoginPage.getObject();
		loginPage.login(fileOperations.getValue("username"), fileOperations.getValue("password"));
	}

	@AfterMethod
	public void tearDown(ITestResult restult) {
		int status=restult.getStatus();
		if(ITestResult.FAILURE==status) {
			PredefinedActions.takeScreenShot(restult.getMethod().getMethodName());
		}
		PredefinedActions.closeBrowser();
	}

}
