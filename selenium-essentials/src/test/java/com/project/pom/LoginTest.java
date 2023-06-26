package com.project.pom;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class LoginTest {

	private WebDriver driver;
	LoginPage loginPage;
	
	@Before
	public void setUp() throws Exception {
		loginPage = new LoginPage(driver);
		driver = loginPage.chromeDriverConnection();
		driver.manage().window().maximize();
		loginPage.visit("https://demo.guru99.com/test/newtours/");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		// hacer click manualmente a la ventana de las cookies
		Thread.sleep(2000);
		
		loginPage.login();
		Thread.sleep(2000);
		assertTrue(loginPage.isHomePageDisplayed());
	}
}
