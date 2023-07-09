package example.debug;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class GoogleSearchTest {

	static GoogleSearchPage page;
	By foundLocator = By.cssSelector("a[href=\"https://www.youtube.com/?gl=ES&hl=es\"]");

	@Before
	public void setUp() throws Exception {
		page = new GoogleSearchPage();
		page.chromeDriverConnection();
	}

	@Test
	public void test() {
		page.visit("https://www.google.com/");
		page.googleCookies();
		page.search("youtube");
		
		// expected fail on purpose
		String expected = "youtube";
		
		String realTitle = page.getDriver().getTitle();
				
		assertEquals(expected, realTitle);
	}
}
