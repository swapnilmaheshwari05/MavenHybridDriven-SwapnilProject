package pages;

import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class MyInfoPage extends PredefinedActions  {
	private static MyInfoPage myInfoPage;
	
	private String menuPagemenu="//a[contains (text(), '%s')]";
	
	public MyInfoPage() {
		
	}
	
	public static MyInfoPage getObject() {
		if(myInfoPage == null)
			myInfoPage = new MyInfoPage();
		PageFactory.initElements(driver, myInfoPage);
		return myInfoPage;
	}
	
	public enum MyInfoMenu{
		PERSONALDETAILS("Personal Details"),
		JOB("Job"),
		SALARY("Salary"),
		CONTACTDETAILS("Contact Details");
		
		public String value;
		private MyInfoMenu(String value) {
			this.value=value;
		}
	}
	public void gotoMenu(MyInfoMenu myInfoMenu) {
			String menuText=myInfoMenu.value;
			String locatorValue=String.format(menuPagemenu, menuText);
			clickOnElement(getElement("xpath", locatorValue, true),false);	
	}

}
