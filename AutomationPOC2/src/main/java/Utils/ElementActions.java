package Utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

	public static void performClickableWait(WebDriver driver,By path) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(80));
		wait.until(ExpectedConditions.elementToBeClickable(path));
	}
	
	public static void performVisibleWait(WebDriver driver,By path) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(80));
		wait.until(ExpectedConditions.visibilityOfElementLocated(path));
	}
	
	public static void pause(long milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);
	}
	
		public static void dynamicClick(WebDriver driver,By path) throws InterruptedException {
		performClickableWait(driver, path);
		boolean isClicked=false;
		
		
		for(int i=0;i<3;i++) {
		try {
				WebElement ele=driver.findElement(path);
				ele.click();
				isClicked=true;
				break;
			}
		catch(Exception e){
			Thread.sleep(2000);
			System.out.println("Counter :"+i);
		}
		}
		if(isClicked==false) {
			WebElement ele=driver.findElement(path);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		}
		
	}
	
	public static void sendData(WebDriver driver,By path,String data) throws InterruptedException {
		boolean isSend=false;
		for(int i=0;i<2;i++) {
			try {
					WebElement ele=driver.findElement(path);
					ele.sendKeys(data);
					isSend=true;
					break;
				}
			catch(Exception e){
				Thread.sleep(2000);
				System.out.println("Counter :"+i);
			}
			}
		if(isSend==false) {
			WebElement ele=driver.findElement(path);
			((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1];", ele,data);
		}
		
		
	}
	
	public static void scrollToView(WebDriver driver,By path) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(path));
	}
	
	public static String getTextContent(WebDriver driver,By path) {
		return (String)((JavascriptExecutor)driver).executeScript(" return arguments[0].textContent;", driver.findElement(path));
	}
	
	public static void selectItemsByText(WebDriver driver,By path,String text) {
		Select sc=new Select(driver.findElement((path)));
		sc.selectByVisibleText(text);
	}
	
	public static void moveToElemAction(WebDriver driver,By path) {
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(path));
	}
}
