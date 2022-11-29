package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class LoginPage extends PredefinedActions {
	private static LoginPage loginPage;
	
	@FindBy(xpath="//input[@id='txtUsername']")
	private WebElement userNameElement;
	
	@FindBy(xpath="//input[@id='txtPassword']")
	private WebElement passwordElement;

	@FindBy(xpath="//button[@type='submit']")
	private WebElement submitBtn;
	
	@FindBy(css="div.organization-logo.shadow>img")
	private WebElement logo;
	
	@FindBy(css="#txtUsername-error")
	private WebElement usernameErrorElement;
	
	@FindBy(css="#txtPassword-error")
	private WebElement passwordErrorElement;
	
	private LoginPage() {
		
	}
	
	public static LoginPage getObject() {
		if(loginPage == null)
			loginPage = new LoginPage();
		PageFactory.initElements(driver, loginPage);
		return loginPage;
	}
	
	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickOnLoginBtn();
	}
	
	public void enterUsername(String username) {
		//driver.findElement(By.id("txtUsername")).sendKeys(username);
		
		//WebElement e = getElement("id", "txtUsername", false);
		//setText(e,username);
		//setText("id", "txtUsername", false,username);
		//userNameElement.sendKeys(username);
		
		setText(userNameElement, username);
	}
	
	public void enterPassword(String password) {
		//driver.findElement(By.id("txtPassword")).sendKeys(password);
		setText(passwordElement, password);
	}
	
	public void clickOnLoginBtn() {
		//driver.findElement(By.xpath("//button[@type='submit']")).click();
		clickOnElement(submitBtn, false);
	}
	
	public boolean isUsernameErrorMessageDisplayed() {
		return isElementDisplayed(usernameErrorElement);
	}
	
	public boolean isPasswordErrorMessageDisplayed() {
		return isElementDisplayed(passwordElement);
	}
	
	public boolean isLogoDisplayed() {
		return isElementDisplayed(logo);
	}
}