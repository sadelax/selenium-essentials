package example.checkinglinks;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckingLinksTest {

	WebDriver driver;
	CheckingLinksPage checker;
	HandleCookiesUtil cookieHandler;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");

		driver = new ChromeDriver();
		checker = new CheckingLinksPage(driver);
		cookieHandler = new HandleCookiesUtil(driver);

		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/test/newtours/");
	}

	@Test
	public void checkLinks() {

		cookieHandler.mercuryToursCookies();

		assertTrue(checker.OkLinks(), "There are broken links!!!");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
