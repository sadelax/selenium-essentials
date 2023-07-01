package com.qualitystream.checkingLinks;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleCookiesUtil {

	WebDriver driver;
	By consentFrameLocator = By.cssSelector("iframe#gdpr-consent-notice");
	By acceptCookiesBtnLocator = By.cssSelector("button#save.mat-button.mat-raised-button");

	public HandleCookiesUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public void mercuryToursCookies() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement consentFrameElement = wait.until(ExpectedConditions.presenceOfElementLocated(consentFrameLocator));
		
		driver.switchTo().frame(consentFrameElement);
		
		System.out.println("ventana gdpr localizada...");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(acceptCookiesBtnLocator));
		driver.findElement(acceptCookiesBtnLocator).click();
		System.out.println("se ha hecho click...");
		
		driver.switchTo().defaultContent();
		System.out.println("vuelta al contexto ppal");
	}
}
