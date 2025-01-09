package PageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utils.DBManager;
import Utils.ElementActions;

public class LoginPage {
	WebDriver driver;
	Map<String, String> sTestData;
	public String menu = "//*[@id='menuUser']";
	public String username = "//input[@name='username']";
	public String password = "//input[@name='password']";
	public String signIn = "//button[@id='sign_in_btn']";
	public String title = "(//span[contains(@class,'hi-user')])[last()]";
	
	public String createAcc="//*[contains(@class,'create-new-account')]";

	public LoginPage(WebDriver driver,Map<String, String> sTestData) {
		this.driver = driver;
		this.sTestData=sTestData;
		
	}

	public void setLogin() throws InterruptedException {
		ElementActions.dynamicClick(driver, By.xpath(menu));
		ElementActions.sendData(driver, By.xpath(username), sTestData.get("username"));
		ElementActions.sendData(driver, By.xpath(password), sTestData.get("password"));
		ElementActions.dynamicClick(driver, By.xpath(signIn));
		
	}

	public String verifyTitle(String username) {
		ElementActions.performVisibleWait(driver, By.xpath(title));
		Assert.assertEquals(driver.findElement(By.xpath(title)).getText(), username);
		return username;

	}
	
	public void createNewAccount() throws InterruptedException {
		ElementActions.dynamicClick(driver, By.xpath(menu));
		ElementActions.dynamicClick(driver, By.xpath(createAcc));
	}

}
