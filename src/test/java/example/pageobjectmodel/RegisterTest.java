package example.pageobjectmodel;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class RegisterTest {
	
	private WebDriver driver;
	RegisterPage registerPage;

	@Before
	public void setUp() throws Exception {
		registerPage = new RegisterPage(driver);
		driver = registerPage.chromeDriverConnection();
		driver.manage().window().maximize();
		registerPage.visit("https://demo.guru99.com/test/newtours/");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		registerPage.registerUser();
		assertEquals("Note: Your user name is sandra.", registerPage.registeredMessage());
	}

}
