package framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import framework.TestCaseFramework.DriverManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFramework {
	/** 
	 * it auto calls by all sub-page
	 * @return 
	 */
	public void Page() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	public static void waitForPageLoad(int timeInSeconds){
		DriverManager.getDriver().manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
	}

	public void waitForLazyLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
					}
				};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public void scrollInToView(WebDriver driver,int x, int y) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String exeScript = String.format("window.scrollBy(%d,%d)",x,y);
		jse.executeScript(exeScript, "");
	}

	public boolean hover(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		Action action = actions.moveToElement(element).build();
		action.perform();
		return true;
	}

	public boolean hover(WebDriver driver, WebElement element, int x, int y) {
		Actions actions = new Actions(driver);
		Action action = actions.moveToElement(element, x, y).build();
		action.perform();
		return true;
	}

	public boolean elementClickable(WebDriver driver, By Locator){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(Locator));
		return true;
	}

}