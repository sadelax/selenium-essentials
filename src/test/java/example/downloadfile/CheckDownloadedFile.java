package example.downloadfile;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class CheckDownloadedFile {

	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/download");
	}

	@Test
	public void checkDownloadedFile() throws MalformedURLException, IOException {
		String link = driver.findElement(By.cssSelector("div.example>a")).getAttribute("href");

		HttpURLConnection httpCon = (HttpURLConnection) (new URL(link).openConnection());
		httpCon.setRequestMethod("HEAD");
		httpCon.connect();

		String contentType = httpCon.getContentType();
		int contentLength = httpCon.getContentLength();

		System.out.println(contentType);
		System.out.println(contentLength);

		assertEquals(contentType, "application/octet-stream");
		assertNotEquals(contentLength, 0);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
