package com.qualitystream.testng;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DownloadFile {

	private WebDriver driver;
	private String downloadFilePath = "C:\\TestDownloads";

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilePath);

		// configurar las opciones del navegador con un objeto ChromeOptions
		ChromeOptions options = new ChromeOptions();
		
		// insertar las preferencias experimentales del hashmap
		options.setExperimentalOption("prefs", chromePrefs);

		// crear instancia del controlador de Chrome
		driver = new ChromeDriver(options);

		driver.get("https://the-internet.herokuapp.com/download");
	}

	@Test
	public void downloadFile() throws InterruptedException {

		driver.findElement(By.cssSelector("div.example>a")).click();

		Thread.sleep(2000);

		// representar la carpeta de descargas para verificar su interior
		File folder = new File(downloadFilePath);
		File[] listOfFiles = folder.listFiles();

		assertTrue(listOfFiles.length > 0, "file not downloaded correctly");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
