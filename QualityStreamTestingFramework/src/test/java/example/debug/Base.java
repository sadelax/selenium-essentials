package example.debug;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

	private WebDriver driver;

	/**
	 * método para conectarme a google chrome
	 * 
	 * @return WebDriver driver
	 */
	public WebDriver chromeDriverConnection() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}

	/**
	 * método para obtener el driver (y así poder utilizar sus métodos)
	 * 
	 * @return el driver de Chrome Driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Encuentra y devuelve el elemento web correspondiente al localizador
	 * especificado. Este método actúa como un envoltorio (wrapper) para el método
	 * `findElement` de la clase WebDriver. Si en el futuro se realiza algún cambio
	 * en el nombre del método `findElement`, solo es necesario actualizarlo en este
	 * método para mantener la consistencia en todo el código.
	 * 
	 * @param locator El localizador utilizado para encontrar el elemento web.
	 * @return El elemento web correspondiente al localizador especificado.
	 */
	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * encuentra y devuelve el texto del elemento q paso como parámetro
	 * 
	 * @param element - elemento que paso como parámetro
	 * @return el elemento en forma de texto
	 */
	public String getText(WebElement element) {
		return element.getText();
	}

	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	/**
	 * uso del comando sendkeys
	 * 
	 * @param inputText - es el texto q queremos escribir
	 * @param locator   - localiza el elemento donde queremos escribir
	 */
	public void type(String inputText, By locator) {
		driver.findElement(locator).sendKeys(inputText);
	}

	/**
	 * uso del método click
	 * 
	 * @param locator
	 */
	public void click(By locator) {
		driver.findElement(locator).click();
		;
	}

	public void click(WebElement element) {
		element.click();
	}

	/**
	 * nos dice que el elemento se encuentra listo para ser utilizado
	 * 
	 * @param locator
	 * @return
	 */
	public Boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * método get, encargado de recibir la url y abrir la página
	 * 
	 * @param url
	 */
	public void visit(String url) {
		driver.get(url);
	}

	/**
	 * Proporciona una espera explícita antes de realizar una acción durante un
	 * tiempo especificado en milisegundos.
	 * 
	 * @param milisegundos el número de milisegundos que se desea esperar antes de
	 *                     realizar el clic.
	 * @throws InterruptedException si se produce una interrupción mientras se
	 *                              espera.
	 */
	public void espera(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Espera hasta que un elemento esté presente en la página dentro de un tiempo
	 * límite.
	 * 
	 * @param timeout el tiempo máximo de espera en milisegundos.
	 * @param locator el localizador del elemento que se espera encontrar.
	 */
	public void esperaHasta(int timeout, By locator) {
		WebDriverWait ewait = new WebDriverWait(driver, Duration.ofMillis(timeout));
		ewait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void googleCookies() {
		driver.manage().deleteCookieNamed("CONSENT");
		driver.manage().addCookie(new Cookie("CONSENT", "YES+"));
		driver.navigate().refresh();
	}
}
