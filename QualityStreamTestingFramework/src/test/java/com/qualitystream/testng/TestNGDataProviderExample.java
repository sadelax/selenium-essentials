package com.qualitystream.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDataProviderExample {
	
	WebDriver driver;
	
	By signInLocator = By.linkText("Sign in");
	By emailLocator = By.id("email");
	By pwdLocator = By.id("passwd");
	By submitLoginLocator = By.id("SubmitLogin");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.automationpractice.pl/index.php?");
	}

	@Test(dataProvider = "authData")
	public void login(Integer n, String s) {
		if(driver.findElement(signInLocator).isDisplayed()) {
			driver.findElement(signInLocator).click();
			
		}
	}

	@DataProvider(name = "authData")
	public Object[][] getData() {
		Object[][] data = new Object[2][2];
		data[0][0] = "email1@gmail.com";
		data[0][1] = "pass123";
		data[1][0] = "email2@gmail.com";
		data[1][1] = "pass123";
		return data;
	}

	@AfterClass
	public void afterClass() {
	}

}
