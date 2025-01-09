/**
 * @author Juhi_Saji
 *
 */
package CucumberExecutor;

import io.cucumber.testng.CucumberOptions;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import Base.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
   features = "src/test/resources/Features",    // Path to your feature files
    glue = "StepDefinition",                  // Path to your step definition package
    plugin = {
        "pretty",                                // Prints the Gherkin steps in the console
        "html:target/cucumber-reports/cucumber.html", // Generates an HTML report
        "json:target/cucumber-reports/cucumber.json", // Generates a JSON report
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", // ExtentReports
        "rerun:target/failedrerun.txt"        
    },
    monochrome = true,                            // Makes the console output more readable
    dryRun= false
)

public class FailedRunner extends AbstractTestNGCucumberTests {
	public static WebDriver driver;
			
	/*@BeforeClass
	public void setUp() throws Exception {
		Properties prop=new Properties();
		FileInputStream file=new FileInputStream("src\\main\\resources\\config.properties");
		prop.load(file);
		
		String browser=System.getProperty("browser",prop.getProperty("browser"));
		//DriverManager.setDriver(browser);
		driver = DriverManager.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	}*/
	@AfterSuite
	public void closeBrowser() {
		if(driver!=null)
		DriverManager.quitDriver();
	}
	
	
}