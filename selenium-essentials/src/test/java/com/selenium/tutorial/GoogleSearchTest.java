package com.selenium.tutorial;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchTest {

	private WebDriver driver;
	private WebDriverWait wait;
	
	By busquedaLinkLocator = By.cssSelector("a[href=\"https://www.reddit.com/\"]");
		
	@Before
	public void setUp() {
		
		// para que el objeto driver pueda ser reconocido, hay que señalar el ejecutable chromedriver.exe
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");

		driver = new ChromeDriver();
		
		// maximizar la ventana
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
	}
	
	@Test
	public void testGooglePage() {
		
		// Ventana de cookies :(
	    // Esperar hasta que aparezca el botón "Aceptar todo"
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".QS5gu.sy4vM")));
	    // Hacer clic en el botón "Aceptar todo"
	    acceptButton.click();
		
	    // q es la caja de google xD
		WebElement searchbox = driver.findElement(By.name("q"));
		
		// limpiar cualquier texto q esté en la caja de búsqueda
		searchbox.clear();
		
		// enviar el texto que queremos q esté en la caja de búsqueda
		searchbox.sendKeys("reddit");
		
		// es como dar enter
		searchbox.submit();
		
		// explicit wait
//		WebDriverWait ewait = new WebDriverWait(driver, Duration.ofMillis(1000));
//		ewait.until(ExpectedConditions.titleContains("reddit"));
		
		// fluent wait
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		
		// le decimos que el elemento busqueda es el motivo de fin de espera.
		// fluent wait esperará un máximo de 10 segundos, haciendo consultas a la página cada 2 para ver si el elemento ya está presente
		fwait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(busquedaLinkLocator);
			}
		});
		
		assertTrue(driver.findElement(busquedaLinkLocator).isDisplayed());
	}
	
	@After
	public void tearDown() {
		// cerrar el navegador una vez completa la prueba
		driver.quit();
	}
}
