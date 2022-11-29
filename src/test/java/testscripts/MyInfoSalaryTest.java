package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.MyInfoPage;
import pages.MyInfo_SalaryPage;
import pages.DashboardPage.Menu;
import pages.MyInfoPage.MyInfoMenu;

public class MyInfoSalaryTest extends TestBase{
	@Test
	public void verifyCtc() {
		DashboardPage dashboardPage = DashboardPage.getObject();
		dashboardPage.gotoMenu(Menu.MYINFO);
		MyInfoPage myInfoPage = MyInfoPage.getObject();
		myInfoPage.gotoMenu(MyInfoMenu.SALARY);
		MyInfo_SalaryPage salaryPage = MyInfo_SalaryPage.getObject();
		String ctc = salaryPage.getCostToCompany();
		Assert.assertTrue(ctc.startsWith("$"),"Actual CTC displayed as"+ctc);
		ctc=ctc.replace("$", "").replace(",", "");
		double d=Double.parseDouble(ctc);
		Assert.assertTrue(d>0,"CTC value was"+ctc);
		System.out.println(ctc);
	}

}
