package com.qualitystream.testng;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTesting {

	WebDriver driver;

	By searchboxLocator = By.name("q");
	By foundLocator = By.cssSelector("a[href=\"https://www.youtube.com/?gl=ES&hl=es\"]");

	@BeforeClass
	@Parameters({ "URL", "BrowserType" })
	public void beforeClass(String Url, String browserType) {

		System.out.println("Abriendo con: " + browserType);

		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("chrome ok");
		} else if (browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("firefox ok");
		} else if (browserType.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "./src/test/resources/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
			System.out.println("edge ok");
		}

		driver.manage().window().maximize();
		driver.get(Url);

	}

	@Test
	public void googleSearch() {

//		System.out.println("google cookies: " + driver.manage().getCookies());

		driver.manage().deleteCookieNamed("CONSENT");
		driver.manage().addCookie(new Cookie("CONSENT", "YES+"));
		driver.navigate().refresh();

		WebElement searchbox = driver.findElement(searchboxLocator);
		searchbox.clear();
		searchbox.sendKeys("youtube");
		searchbox.submit();

		WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(7));
		wdw.until(ExpectedConditions.presenceOfElementLocated(foundLocator));

		assertTrue(driver.findElement(foundLocator).isDisplayed(), "no se encuentra");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
