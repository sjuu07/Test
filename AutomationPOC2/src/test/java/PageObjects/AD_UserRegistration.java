/**
 * 
 */
package PageObjects;

import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utils.DBManager;
import Utils.ElementActions;

/**
 * @author Juhi_Saji
 *
 */
public class AD_UserRegistration {
	WebDriver driver;
	Map<String, String> sTestData;;
	
	String rUsername="usernameRegisterPage";
	String rEmail="emailRegisterPage";
	String rPass="passwordRegisterPage";
	String rPassCnfirm="confirm_passwordRegisterPage";
	String agreeChcbox="i_agree";
	String regstrBtn="register_btn";
	public String title = "(//span[contains(@class,'hi-user')])[last()]";
	String signOut="(//*[text()='Sign out'])[last()]";
	String user;
	
	public AD_UserRegistration(WebDriver driver,Map<String, String> sTestData) {
		this.driver = driver;
		this.sTestData=sTestData;
		user=sTestData.get("Reg_UN")+new Random().nextInt(100);
		
	}
	
	public void setRegisterDetails() throws InterruptedException {
		
		ElementActions.sendData(driver, By.name(rUsername), user);
		ElementActions.sendData(driver, By.name(rEmail), sTestData.get("Reg_Email"));
		ElementActions.sendData(driver, By.name(rPass), sTestData.get("Reg_Pass"));
		ElementActions.sendData(driver, By.name(rPassCnfirm), sTestData.get("PasswordConfirm"));
		ElementActions.dynamicClick(driver, By.name(agreeChcbox));
		ElementActions.dynamicClick(driver, By.id(regstrBtn));
	}
	
	public String verifyTitle(String username) {
		ElementActions.performVisibleWait(driver, By.xpath(title));
		Assert.assertEquals(driver.findElement(By.xpath(title)).getText(), user);
		return username;

	}
	
	public void signOut() throws InterruptedException {
		ElementActions.dynamicClick(driver, By.xpath(title));
		ElementActions.dynamicClick(driver, By.xpath(signOut));
	}
	
	public void verifySignOut() throws InterruptedException {
		ElementActions.pause(2000);
		Assert.assertEquals(driver.findElement(By.xpath(title)).getText(), "");
	}
}
