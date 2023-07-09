package example.debug;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage extends Base {
	
	By searchboxLocator = By.name("q");

	public GoogleSearchPage(WebDriver driver) {
		super(driver);
	}
	
	public void search(String keywords) {
		WebElement searchbox = findElement(searchboxLocator);
		type(keywords, searchboxLocator);
		searchbox.submit();
	}

}
