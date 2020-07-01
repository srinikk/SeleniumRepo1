package utils;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUntil {

	
	public static void waitUnitlElementIsVisible(WebDriver driver, By locator, long timeout) {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
		
	}
	
	public static void waitUnitlElementIsNotVisible(WebDriver driver, By locator, long timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator)));
		
	}
	
}
