package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Base {
	
	By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By signBtnLocator = By.name("submit");

	By loggedInLocator = By.xpath("//table//h3[text()='Login Successfully']");

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void login() {
		if(isDisplayed(userLocator)) {
			type("sandra", userLocator);
			type("pass1", passLocator);
			click(signBtnLocator);
		} else {
			System.out.println("el textbox del username no está");
		}
	}
	
	public boolean isHomePageDisplayed() {
		return isDisplayed(loggedInLocator);
	}
}
