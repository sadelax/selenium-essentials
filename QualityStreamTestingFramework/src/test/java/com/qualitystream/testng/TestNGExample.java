package com.qualitystream.testng;

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
import org.testng.annotations.Test;

public class TestNGExample {

	WebDriver driver;
	By searchboxLocator = By.id("search_query_top");
	By resultsLocator = By.cssSelector("span.heading-counter");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.automationpractice.pl/index.php?");
	}

	@Test
	public void searchBlouses() {
		WebElement searchbox = driver.findElement(searchboxLocator);
		searchbox.clear();
		
		searchbox.sendKeys("blouse");
		searchbox.submit();
		
		WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ewait.until(ExpectedConditions.presenceOfElementLocated(resultsLocator));
		
		System.out.println("Resultado: " + driver.findElement(resultsLocator).getText());
		
		assertTrue(driver.findElement(resultsLocator).isDisplayed(), "no se ha encontrado el resultsLocator");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
