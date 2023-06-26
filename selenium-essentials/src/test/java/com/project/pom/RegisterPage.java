package com.project.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.bytebuddy.asm.Advice.Enter;

/**
 * @author sandr
 * en esta página incluiré los localizadores y las acciones q pueden ejecutarse sobre la página de registro
 */
public class RegisterPage extends Base {
	
	By registerLinkLocator = By.linkText("REGISTER");
	By registerPageLocator = By.xpath("//img[@src='images/mast_register.gif']");

	By usernameLocator = By.id("email");
	By passwordLocator = By.name("password");
	By confirmPasswordLocator = By.cssSelector("input[name='confirmPassword']");

	By registerBtnLocator = By.name("submit");
	
	By registeredMessage = By.tagName("font");

	public RegisterPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void registerUser() throws InterruptedException {
		// ventana de las cookies - click manual en aceptar
		Thread.sleep(2000);
		click(registerLinkLocator);
		Thread.sleep(2000);
		if(isDisplayed(registerPageLocator)) {
			type("sandra", usernameLocator);
			type("pass1", passwordLocator);
			type("pass1", confirmPasswordLocator);
			click(registerBtnLocator);
		} else {
			System.out.println("la página de registro no se encontró");
		}
	}
	
	public String registeredMessage() {
		List<WebElement> fonts = findElements(registeredMessage);
		return getText(fonts.get(5));
	}

}
