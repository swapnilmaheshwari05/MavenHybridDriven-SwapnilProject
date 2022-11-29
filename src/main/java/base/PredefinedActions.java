package base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constant.ConstantValue;
import customexceptions.ElementNotEnabeldException;

public class  PredefinedActions {

	protected static WebDriver driver;
	static WebDriverWait wait;
	private static Actions actions;
	
	protected PredefinedActions(){
		
	}
	
	public static void start(String url) {
		
		String browser = System.getProperty("browserName");
		String env = System.getProperty("env");

		System.out.println("Browser Name : "+browser);
		System.out.println("Environment Name :"+env);

		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--incognito");
			System.setProperty(ConstantValue.CHROMEDRIVERKEY, ConstantValue.CHROMEDRIVER);
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty(ConstantValue.CHROMEDRIVERKEY, ConstantValue.CHROMEDRIVER);
			driver = new FirefoxDriver();

		case "ie":
			System.setProperty(ConstantValue.CHROMEDRIVERKEY, ConstantValue.CHROMEDRIVER);
			driver = new InternetExplorerDriver();

		default:
			break;
		}

		
		driver.get(url);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,ConstantValue.EXPLICITWAITTIME);
		actions = new Actions(driver);
	}
	
	public static WebDriver startTemp(String url) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_106.exe");
		System.out.println("STEp - Launch chrome browser");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,60);
		actions = new Actions(driver);
		return driver;
	}
	
	protected WebElement getElement (String locatorType, String locatorValue,boolean isWaitRequired) {
		WebElement element=null;
		
		switch (locatorType.toLowerCase()) {
		
			case "id":
				if(isWaitRequired) {
					element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				}
				else
					element=driver.findElement(By.id(locatorValue));
				break;
				
			case "xpath":
				if(isWaitRequired) {
					element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				}
				else
					element=driver.findElement(By.xpath(locatorValue));
				break;
				
			case "cssselector":
				if(isWaitRequired) {
					element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				}
				else
					element=driver.findElement(By.cssSelector(locatorValue));
				break;
				
			case "name":
				if(isWaitRequired) {
					element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				}
				else
					element=driver.findElement(By.name(locatorValue));
				break;
				
			case "linktext":
				if(isWaitRequired) {
					element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
				}
				else
					element=driver.findElement(By.linkText(locatorValue));
				break;
				
			case "partiallinktext":
				if(isWaitRequired) {
					element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
				}
				else
					element=driver.findElement(By.partialLinkText(locatorValue));
				break;
				
			case "classname":
				if(isWaitRequired) {
					element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				}
				else
					element=driver.findElement(By.className(locatorValue));
				break;
			
			case "tagname":
				if(isWaitRequired) {
					element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
				}
				else
					element=driver.findElement(By.tagName(locatorValue));
				break;
		}
		
		return element;
	}
	
	protected void setText(WebElement e,String text) {
		scrollToElement(e);
		if(e.isEnabled())
			e.sendKeys(text);
		else
			throw new ElementNotEnabeldException(text +"can't be entered as element is not enabled");
		}
	
	protected void setText(String locatorType,String locatorValue,boolean isWaitRequired,String text) {
		WebElement e=getElement(locatorType, locatorValue, isWaitRequired);
		if(e.isEnabled()) 
			e.sendKeys(text);
		}
	
	protected void clickOnElement(WebElement e, boolean isWaitRequiredBeforeClick) {
		scrollToElement(e);
		if(isWaitRequiredBeforeClick) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
			e.click();
		}
		else
			e.click();
		
	}
	
	protected void scrollToElement(WebElement e) {
		if(!e.isDisplayed()) {
			JavascriptExecutor je = (JavascriptExecutor)driver;
			je.executeScript("arugments[0].scrollIntoView(true)",e);
			
		}
	}
	protected boolean isElementDisplayed(WebElement e) {
		scrollToElement(e);
		return e.isDisplayed();
	}
	
	protected void mouseHoverOnElement(WebElement e) {
		actions.moveToElement(e).build().perform();
	}
	
	protected List<String> getListOfWebElementText(List<WebElement> list) {
		List <String> listOfElementText=new ArrayList<String>();
		for (WebElement e : list) {
			listOfElementText.add(e.getText());
		}
		return listOfElementText;
	}
	
	protected boolean waitForVisibilityOfElement(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
		}
		catch(Exception exception) {
			return false;
		}
		return true;
	}
	
	protected String getElementText(WebElement e , boolean isWaitRequired) {
		if(isWaitRequired)
			waitForVisibilityOfElement(e);
		String value=e.getText();
		if(value.equals(value)) {
			value=e.getAttribute("value");
		}
		return value;
	}
	
	protected void clickUsingJS(WebElement ele) {
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click", ele);
	}
	
	protected void sendKeysUsingJS(WebElement ele, String text) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+ text +"'", ele);
	}
	
	protected void markCheckboxUsingJS(WebElement ele, boolean checkedOrUnchecked) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].checked=" +checkedOrUnchecked+"",ele);
	}
	
	
	public  static void takeScreenShot(String testCaseName) {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(ConstantValue.SCREENSHOTLOCATION+testCaseName+ConstantValue.SCREENSHOTEXT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getPageURL() {
		return driver.getCurrentUrl();
	}
	
	public static void closeBrowser() {
		driver.close();
	}

	public static void closeAllBrowser() {
		driver.quit();
	}
	
	

}
