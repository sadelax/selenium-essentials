package com.qualitystream.testng;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	By signOutLocator = By.cssSelector("a.logout");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.automationpractice.pl/index.php?");
	}

	@Test(dataProvider = "authData")
	public void login(String email, String pwd) {
		if(driver.findElement(signInLocator).isDisplayed()) {
			driver.findElement(signInLocator).click();
			
			WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(7));
			wdw.until(ExpectedConditions.presenceOfElementLocated(emailLocator));
			
			driver.findElement(emailLocator).sendKeys(email);
			driver.findElement(pwdLocator).sendKeys(pwd);
			driver.findElement(submitLoginLocator).click();
			
			assertEquals(driver.findElement(signOutLocator).getText(), "Sign out");
			
			driver.findElement(signOutLocator).click();
		} else {
			System.out.println("no se encuentra el formulario de login");
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
