package com.qualitystream.checkingLinks;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class CheckingLinksTest {

	WebDriver driver;
	CheckingLinksPage checker;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		checker = new CheckingLinksPage(driver);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/test/newtours/");
	}

	@Test
	public void checkLinks() throws InterruptedException {

		System.out.println(driver.manage().getCookies());
		Thread.sleep(5000);
		assertTrue(checker.OkLinks(), "There are broken links!!!");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
