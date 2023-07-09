package example.dropdownlist;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class DropdownListTest {
	
	private WebDriver driver;
	DropdrownListPage ddLPage;

	@Before
	public void setUp() throws Exception {
		ddLPage = new DropdrownListPage(driver);
		driver = ddLPage.chromeDriverConnection();
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void testDdLSelect(){
		ddLPage.visit("https://demo.guru99.com/test/newtours/index.php");
		ddLPage.login();
		ddLPage.clickIntoFlights();
		assertEquals(ddLPage.selectDropdownListDepartingFrom(), "Paris");
	}
	
	@Test
	public void testDdLReact() {
		ddLPage.visit("https://modus-react-bootstrap.trimble.com/components/dropdowns/");
		ddLPage.selectReactDropdownList();
	}
}
