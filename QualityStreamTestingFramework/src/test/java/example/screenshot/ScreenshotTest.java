package example.screenshot;

import static org.junit.Assert.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import example.checkinglinks.HandleCookiesUtil;

public class ScreenshotTest {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	@Rule
	public TestRule watcher = new TestWatcher() {
		// se ejecutará cuando el test falle
		@Override
		protected void failed(Throwable throwable, Description description) {
			// hacer el screenshot
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshotFile,
						new File("error_" + description.getMethodName() + getDate() + ".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// se ejecutará después del método failed
		// usar este método en lugar del TearDown porque, si un test falla, se lanza una
		// excepción y el método TearDown nunca se ejecutaría
		@Override
		protected void finished(Description description) {
			driver.quit();
		}
	};

	@Test
	public void googleSearchTest() {
		driver.get("https://www.google.com/");
		
		HandleCookiesUtil handleCookies = new HandleCookiesUtil(driver);
		handleCookies.googleSearchCookies();

		WebElement searchbox = driver.findElement(By.name("q"));
		searchbox.clear();
		searchbox.sendKeys("youtube");
		searchbox.submit();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		assertEquals("esto ocasionará un error", driver.getTitle());
	}

}
