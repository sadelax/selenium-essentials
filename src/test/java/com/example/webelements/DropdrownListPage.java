package com.example.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.example.pom.Base;

/**
 * @author sandr En esta clase implemento la automatización de un dropdownlist
 *         básico en la página de Mercury Tours -
 *         https://demo.guru99.com/test/newtours/ También se implementará un
 *         dropdownlist hecho con bootstrap y react -
 *         https://modus-react-bootstrap.trimble.com/components/dropdowns/
 */
public class DropdrownListPage extends Base {

	By ddLPassengers = By.name("passCount");
	By ddLDepartingFrom = By.name("fromPort");

	By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By signBtnLocator = By.name("submit");
	
	By flightsMenuLocator = By.xpath("//a[@href=\"reservation.php\"]");
	
	// dropdownlist con bootstrap y react
	
	By ddLBtn = By.id("dropdown-basic");
	By option2 = By.cssSelector("div[aria-labelledby=\'dropdown-basic']>a[href='#/action-2']");
	
	public DropdrownListPage(WebDriver driver) {
		super(driver);
	}

	public void login() {
		
		espera(4000);
		if (isDisplayed(userLocator)) {
			type("sandra", userLocator);
			type("pass1", passLocator);
			click(signBtnLocator);
		} else {
			System.out.println("el textbox del username no está");
		}
	}
	
	public void clickIntoFlights() {
		click(flightsMenuLocator);
	}

	public String selectDropdownListDepartingFrom() {
		esperaHasta(9000, ddLDepartingFrom);

		Select pais = new Select(findElement(ddLDepartingFrom));
		pais.selectByVisibleText("Paris");
		return getText(pais.getFirstSelectedOption());
	}

	public void selectReactDropdownList() {
		click(ddLBtn);
		espera(3000);
		click(option2);
	}
}
