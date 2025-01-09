package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import Base.DriverManager;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import CucumberExecutor.TestRunner;
import PageObjects.AD_ProductPage;
import PageObjects.AD_UserRegistration;
import PageObjects.LoginPage;
import Utils.DBManager;

public class StepDefs {
	public Scenario scenario;
	public static WebDriver driver ;
	public Logger log;
	LoginPage lp;
	AD_ProductPage pg;
	AD_UserRegistration ad;
	Map<String, String> sTestData;

	@Before
	public void initailSetUp(Scenario scenario) throws Exception {
		driver = DriverManager.getDriver();
		
		log=LogManager.getLogger();
		
		this.scenario = scenario;
		
		DBManager dbManager = new DBManager();
		sTestData=dbManager.getExcelData("src\\test\\resources\\testData\\testdata.xlsx", "testdata", scenario.getName());
		System.out.println("TestData:"+sTestData);

		lp = new LoginPage(driver,sTestData);
		pg=new AD_ProductPage(driver,sTestData);
		ad=new AD_UserRegistration(driver,sTestData);
		System.out.println("Before Hook Executed");
	}
	
	@After
	public void tearDown() throws Exception {
		if(scenario.isFailed()) {
			scenario.attach(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png", "Error Screenshot");
			driver.quit();
			driver=null;
		}
	}

	@Given("user opens the browser")
	public synchronized void userOpensTheBrowser() {
		driver.manage().window().maximize();
		log.info("Maximised");
	}

	@When("user navigates to url")
	public synchronized void userNavigatesToURL() {
		String str=sTestData.get("url");
		System.out.println(sTestData.get("url"));
		driver.navigate().to(str);
		log.info("Launched URL");
	}

	@When("click menu to provide login credentials")
	public void click_menu_to_provide_login_credentials() throws InterruptedException {
		lp.setLogin();
		log.info("Logged In");
		
	}

	@Then("verify the page title")
	public void thePageTitleShouldContain(){
		scenario.log("Page Title : "+lp.verifyTitle(sTestData.get("username")));
	}
	//place order
	
	@Given("user is on application welcome page")
	public void user_is_on_application_welcome_page() throws InterruptedException {
		if(!driver.findElement(By.xpath("//*[text()='OUR PRODUCTS']")).isDisplayed()){
			driver.findElement(By.xpath("//*[text()='dvantage']")).click();
		}
		scenario.log("Page Title : "+lp.verifyTitle(sTestData.get("username")));
	}

	@When("user search an item")
	public void user_search_an_item() throws InterruptedException {
		pg.searchProduct();
	}

	@When("add item to cart")
	public void add_item_to_cart() throws InterruptedException {
	   pg.addToCart();
	}

	@When("complete the payment")
	public void complete_the_payment() throws InterruptedException {
	   pg.completePayment();
	}

	@Then("order is placed successfully")
	public void order_is_placed_successfully() throws InterruptedException {
		
	   Map<String, String> mp=pg.placeOrder();
	   for(Map.Entry<String,String> m:mp.entrySet()){
		   scenario.log(m.getKey()+":"+m.getValue());
	   }
	}
	//Contact us functionality
	@When("user is on Contact Us section")
	public void user_is_on_contact_us_section() {
		pg.scrollElement();
	}

	@When("user provide all the details")
	public void user_provide_al_the_details() throws InterruptedException {
	   pg.provideContactDetails();
	}

	@When("click on Send")
	public void click_on_send() throws InterruptedException {
	    pg.sendEmail();
	}

	@Then("user should be able to see Thankyou message")
	public void user_should_be_able_to_see_thankyou_message() {
	    pg.verifySendMailTitle();
	}
	
	//Multiple product to cart and clear cart
	@When("user scroll to Popular Items section")
	public void user_scroll_to_popular_items_section() {
	   pg.scrollToPop();
	}

	@When("user capture and click the first available product")
	public void user_capture_and_click_the_first_available_product() throws InterruptedException {
	   String Item1=pg.fItemCapture();
		scenario.log("Item 1:"+Item1);
	}

	@When("capture price and Add to Cart")
	public void capture_price_and_add_to_cart() throws InterruptedException {
		String Price1=pg.fItemCart();
		scenario.log("Price :"+Price1);
	}

	@When("user navigate back to Popular Items section")
	public void user_navigate_back_to_popular_items_section() {
	    driver.navigate().back();
	    System.out.println("Title: "+ driver.getTitle());
	}

	@When("user capture and click the second available product")
	public void user_capture_and_click_the_second_available_product() throws InterruptedException {
		 String Item2= pg.SItemCapture();
			scenario.log("Item 2:"+Item2);
	  
	}
	
	@When("Hover on the cart icon to view the total price")
	public void hover_on_the_cart_icon_to_view_the_total_price() {
		 Double TotalPrice=pg.TotalPriceCheck();
		 scenario.log("Total Price :"+TotalPrice);
	}

	@When("user click X icon for the products added")
	public void user_click_x_icon_for_the_products_added() throws InterruptedException {
	  pg.cartClear();
	}

	@Then("cart should be cleared")
	public void cart_should_be_cleared() throws InterruptedException {
	    pg.EmptyCart();
	}

	/*******User Registration***************/
	
	@When("click Create New Account")
	public void click_create_new_account_to_register() throws InterruptedException {
	    lp.createNewAccount();
	}

	@When("Register by providing details")
	public void provide_details() throws InterruptedException {
	    ad.setRegisterDetails();
	}

	@Then("user registered successfully")
	public void user_registered_successfully() {
		scenario.log("Page Title : "+ad.verifyTitle(sTestData.get("Reg_UN")));
	}
	
	@When("user click menu and sign out")
	public void user_click_menu_and_sign_out() throws InterruptedException {
	   ad.signOut();
	}

	@Then("user signed out successfully")
	public void user_signed_out_successfully() throws InterruptedException {
	    ad.verifySignOut();
	}
	//Shop from the category list
	@When("user click on Speakers Shop Now")
	public void user_click_on_speakers_shop_now() throws InterruptedException {
	    pg.categoryItem();
	}
	@When("select the compataibility to bluetooth enabled")
	public void select_the_compataibility_to_bluetooth_enabled() throws InterruptedException {
		pg.categoryItem();
	}
	@When("select HP product")
	public void select_hp_product() throws InterruptedException {
		pg.selectItem();
	}
}
