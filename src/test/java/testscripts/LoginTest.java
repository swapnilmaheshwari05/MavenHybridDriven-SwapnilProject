package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.PredefinedActions;
import constant.ConstantValue;
import pages.LoginPage;
import utility.ExcelOperations;

public class LoginTest {

	@Test(dataProvider = "LoginDataProvider")
	public void tc1(String url, String username, String password, boolean isLoginSucessful) {
		System.out.println("STEP - Launch chrome browser and hit url");
		PredefinedActions.start(url);

		System.out.println("STEP- Enter valid credential");
		LoginPage loginPage = LoginPage.getObject();
		loginPage.login(username, password);

		if (isLoginSucessful) {
			System.out.println("VERIFY - Home page is displayed");
			String actualTitle = loginPage.getPageTitle();
			String expectedTitle = "Employee Management";

			Assert.assertEquals(actualTitle, expectedTitle,
					"Login failed" + expectedTitle + "but actual title is" + actualTitle);
		} else {
			System.out.println("VERIFY - Home page is displayed");
			String actualTitle = loginPage.getPageTitle();
			String expectedTitle = "OrangeHRM";

			Assert.assertEquals(actualTitle, expectedTitle,
					"Login failed" + expectedTitle + "but actual title is" + actualTitle);

			System.out.println("VERIFY - Retry login page loaded");
			String expectedURLContent = "retryLogin";
			String actualCurrentURL = loginPage.getPageURL();
			Assert.assertTrue(actualCurrentURL.endsWith(expectedURLContent));

		}

		//System.out.println("Clean up activity - Close the browser");
		//PredefinedActions.closeBrowser();

	}

	@DataProvider(name = "LoginDataProvider")
	public Object[][] getLoginData() throws IOException {
		Object[][] data;
		
		try {
			data = ExcelOperations.readExcelData(ConstantValue.LOGINDATA, "Data");
		} catch (IOException e) {
			data = ExcelOperations.readExcelData(".//testdata1//LoginData.xlsx", "Data");
			e.printStackTrace();
			// throw new FileNotFoundExcecption();
		}
		return data;
	}
	
	@AfterMethod
	public void tearDown() {
		PredefinedActions.closeBrowser();
	}

	@DataProvider(name = "LoginDataProvider1")
	public Object[][] getLoginData1() {
		Object[][] data = new Object[2][4];
		data[0][0] = "https://swapnilm-trials77.orangehrmlive.com/auth/login";
		data[0][1] = "admin";
		data[0][2] = "PUyF6Va6@k";
		data[0][3] = true;

		data[1][0] = "https://swapnilm-trials77.orangehrmlive.com/auth/login";
		data[1][1] = "admin";
		data[1][2] = "PUyF6Va6@k785";
		data[1][3] = false;

		return data;
	}

	//

	@Test
	public void tc1_Negative() {
		System.out.println("STEP - Launch chrome browser and hit url");
		PredefinedActions.start("https://swapnilm-trials77.orangehrmlive.com/auth/login");

		System.out.println("STEP- Enter Invalid credential");
		LoginPage loginPage = LoginPage.getObject();
		loginPage.login("admin", "PUyF6Va6@k786");

		System.out.println("VERIFY - Home page is displayed");
		String actualTitle = loginPage.getPageTitle();
		String expectedTitle = "OrangeHRM";

		Assert.assertEquals(actualTitle, expectedTitle,
				"Login failed" + expectedTitle + "but actual title is" + actualTitle);

		System.out.println("VERIFY - Retry login page loaded");
		String expectedURLContent = "retryLogin";
		String actualCurrentURL = loginPage.getPageURL();
		Assert.assertTrue(actualCurrentURL.endsWith(expectedURLContent));

		System.out.println("Clean up activity - Close the browser");
		//PredefinedActions.closeBrowser();

	}

}
