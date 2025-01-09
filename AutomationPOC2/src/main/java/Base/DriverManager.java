package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	//On running tests in parallel, it ensures that each thread (test) has its own WebDriver instance.
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static void setDriver() throws IOException {
		System.out.println("Thread ID:"+Thread.currentThread().getId());
		Properties prop=new Properties();
		FileInputStream file=new FileInputStream("src\\main\\resources\\config.properties");
				prop.load(file);
				String browser=System.getProperty("browser",prop.getProperty("browser"));
				System.out.println("Property File Loaded"+browser);
		if (driver.get() == null) {
			 synchronized (DriverManager.class) {
				 if (driver.get() == null) {
			switch (browser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().driverVersion("131.0.6778.86").setup();
				driver.set(new ChromeDriver());
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver.set(new org.openqa.selenium.firefox.FirefoxDriver());
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver.set(new EdgeDriver());
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}}}
		}
	}

	public static WebDriver getDriver() throws IOException {
		if (driver.get() == null) {
            setDriver();  // Initialize WebDriver instance if it's null
        }
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
