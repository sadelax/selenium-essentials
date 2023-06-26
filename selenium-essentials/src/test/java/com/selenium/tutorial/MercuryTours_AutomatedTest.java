package com.selenium.tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours_AutomatedTest {

	private WebDriver driver;

	By registerLinkLocator = By.linkText("REGISTER");
	By registerPageLocator = By.xpath("//img[@src='images/mast_register.gif']");

	By usernameLocator = By.id("email");
	By passwordLocator = By.name("password");
	By confirmPasswordLocator = By.cssSelector("input[name='confirmPassword']");

	By registerBtnLocator = By.name("submit");

	By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By signBtnLocator = By.name("submit");

	By loggedInLocator = By.xpath("//table//h3[text()='Login Successfully']");

	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");

		driver = new ChromeDriver();

		// maximizar la ventana
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/test/newtours/");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void registerUser() {

		// ESCENARIOS:
		// 1. clic en REGISTER
		// 1(a) ventana de cookies
		// 2. completar campos para registrar usuario
		// 3. confirmar mensaje de usuario registrado

		driver.findElement(registerLinkLocator).click();

		// para la ventana de las cookies
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (driver.findElement(registerPageLocator).isDisplayed()) {

			driver.findElement(usernameLocator).sendKeys("sandra");
			driver.findElement(passwordLocator).sendKeys("pass1");
			driver.findElement(confirmPasswordLocator).sendKeys("pass1");

			driver.findElement(registerBtnLocator).click();
		} else {
			System.out.println("la página de registro no se encuentra");
		}

		// al inspeccionar un elemento por etiquetas, pueden haber muchas. Hacemos una
		// lista de ellas...
		List<WebElement> fonts = driver.findElements(By.tagName("font"));

		// ...y checkeamos la coincidencia
		assertEquals("Note: Your user name is sandra.", fonts.get(5).getText());
	}

	@Test
	public void LoginUser() {
		
		// para la ventana de las cookies
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (driver.findElement(userLocator).isDisplayed()) {
			driver.findElement(userLocator).sendKeys("sandra");
			driver.findElement(passLocator).sendKeys("pass1");
			driver.findElement(signBtnLocator).click();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			assertTrue(driver.findElement(loggedInLocator).isDisplayed());
		} else {
			System.out.println("no has escrito nada");
		}
	}

}
