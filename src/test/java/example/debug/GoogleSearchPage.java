package example.debug;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage extends Base {

	public GoogleSearchPage() {
		super();
	}

	By searchboxLocator = By.name("q");

	public void search(String keywords) {
		WebElement searchbox = findElement(searchboxLocator);
		type(keywords, searchboxLocator);
		searchbox.submit();
	}
}
