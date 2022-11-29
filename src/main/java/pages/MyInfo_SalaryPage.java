package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class MyInfo_SalaryPage extends PredefinedActions{
private static MyInfo_SalaryPage myInfo_SalaryPage;
	
	@FindBy(xpath="//div[@translate='Cost to the Company']/following-sibling::div")
	private WebElement costToCmp;
	
	private MyInfo_SalaryPage(){
		
	}
	
	
	public static MyInfo_SalaryPage getObject() {
		if(myInfo_SalaryPage == null)
			myInfo_SalaryPage = new MyInfo_SalaryPage();
		PageFactory.initElements(driver, myInfo_SalaryPage);
		return myInfo_SalaryPage;
	}
	
	public String getCostToCompany() {
		waitForVisibilityOfElement(costToCmp);
		return costToCmp.getText();
	}

}
