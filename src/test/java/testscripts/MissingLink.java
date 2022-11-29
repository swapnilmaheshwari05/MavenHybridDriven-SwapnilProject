package testscripts;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.PredefinedActions;
import constant.ConstantValue;

public class MissingLink {
	static WebDriver driver;
	
	public static void start(String url) {
		System.setProperty(ConstantValue.CHROMEDRIVERKEY, ConstantValue.CHROMEDRIVER);
		System.out.println("STEp - Launch chrome browser");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@Test
		public void verifyMissingLink() throws IOException {
		start("file:///D:/TechnoCredits/Maven_Hybrid_Driven_Framwork_Maulki's%20project/MavenHybridDriven-Aug2022/BrokenLink.html");
		List<WebElement> listofLinks =driver.findElements(By.xpath("//a"));
		
		for(WebElement e  : listofLinks) {
			if(e.getAttribute("href")==null || e.getAttribute("href").equals(""))
				System.out.println(e.getText()+"Is missing link");
			else {
				
				
				URL url=new URL(e.getAttribute("href"));
				
				HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
				httpConn.setConnectTimeout(3000);
				httpConn.connect();
				
				if(httpConn.getResponseCode()==200)
{
					System.out.println(e.getText()+"--"+httpConn.getResponseCode());
				}
				
				else if(httpConn.getResponseCode()>399) {
					System.out.println(e.getText()+"--"+httpConn.getResponseCode());
					
				}
			}
				
		}
	}

}
