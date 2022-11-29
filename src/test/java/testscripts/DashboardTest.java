package testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.PredefinedActions;
import pages.DashboardPage;
import utility.PropertyFileOperations;

public class DashboardTest extends TestBase {

	@Test
	public void verifyWidgetsCountAndText() throws IOException {
		DashboardPage dashboardPage = DashboardPage.getObject();
		System.out.println("VERIFY - Number of widgets on dashboard page");
		int totalWidgets = dashboardPage.getNumberOfWidgets();
		Assert.assertEquals(totalWidgets, 9, "totalWidegets was not displayed as expected, expected was 9, actual widgets displayed "+ totalWidgets);
		
		List<String> listOfExpectedWidgetsText = new ArrayList<String>(
				Arrays.asList("Quick Access","Buzz Latest Posts", "My Actions",
						"Latest Documents", "Latest News", "Employees on Leave Today",
						"Time At Work", "Headcount by Location", "COVID-19 Report"));

		System.out.println("STEP - Get list of all widgets text");
		List<String> listOfActualWidgetsText = dashboardPage.getAllWidgetsText();
		System.out.println("VERIFY - text of all widgets");
		Assert.assertEquals(listOfActualWidgetsText, listOfExpectedWidgetsText);
	}

	@Test
	public void verifyProfileAboutContentTest() {
		DashboardPage dashboardPage = DashboardPage.getObject();

		System.out.println("STEP- Mouse hover on profile and click on Settings");
		List<String> expectedProfileSettingOptions = new ArrayList<String>(Arrays.asList("Change Password", "About"));
		List<String> profileSettingOptions = dashboardPage.getSettingProfileOptions();

		System.out.println("VERIFY - Available setting options");
		Assert.assertEquals(profileSettingOptions, expectedProfileSettingOptions);

		System.out.println("STEP - Click on About link");
		dashboardPage.clickOnProfielAbout();

		SoftAssert softAsert = new SoftAssert();

		System.out.println("VERIFY- Company name");
		String companyName = "OrangeHRM (Pvt) Ltd(Parallel Demo)";
		softAsert.assertEquals(dashboardPage.getCompanyName(), companyName);

		System.out.println("VERIFY- Version");
		String version = "OrangeHRM 7.7.178472";
		softAsert.assertEquals(dashboardPage.getVersion(), version);

		System.out.println("VERIFY- Employees");
		String employees = "97 (103 more allowed)";
		softAsert.assertEquals(dashboardPage.getEmployees(), employees);

		System.out.println("VERIFY- Users");
		String users = "83 (417 more allowed)";
		softAsert.assertEquals(dashboardPage.getUsers(), users);

		System.out.println("VERIFY- Renewal On");
		String renewalOn = "Fri, 30 Dec 2022";
		softAsert.assertEquals(dashboardPage.getRenewalOn(), renewalOn);

		System.out.println("STEP - Click on Ok button");
		dashboardPage.clickOnAboutPopupBtn("Ok");

		softAsert.assertAll();
	}

}
