package PageObjects;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Utils.DBManager;
import Utils.ElementActions;

public class AD_ProductPage {
	WebDriver driver;
	Map<String, String> sTestData;
	Map<String, String> map = new LinkedHashMap<>();
	String search = "//*[@title='SEARCH']";
	String searchInp = "//*[@id='autoComplete']";
	String item = "(//*[text()='dynText'])[last()]";
	String addToCart = "//button[@name='save_to_cart']";
	String checkout = "//button[@id='checkOutPopUp']";
	String payment = "//button[@id='next_btn'][@data-ng-click='shippingDetails_next()']";
	String payUser = "//input[@name='safepay_username']";
	String payPass = "//input[@name='safepay_password']";
	String payNext = "pay_now_btn_SAFEPAY";
	String successPage = "//*[@translate='Thank_you_for_buying_with_Advantage']";
	String track = "//label[@id='trackingNumberLabel']";
	String orderId = "//label[@id='orderNumberLabel']";
	String contact = "(//*[text()='CONTACT US'])[2]";
	String category = "//*[@name='categoryListboxContactUs']";
	String product = "//*[@name='productListboxContactUs']";
	String email = "//input[@name='emailContactUs']";
	String sub = "//textarea[@name='subjectTextareaContactUs']";
	String send = "send_btn";
	String successMsg = "//*[contains(@class,'successMessage')]";
	
	String pop="(//*[text()='POPULAR ITEMS'])[2]";
	String fItem="((//*[text()='POPULAR ITEMS'])[2]/..//p[contains(@name,'popular_item')])[3]";
	String itemClick="((//*[text()='POPULAR ITEMS'])[2]/..//label[contains(@id,'details')])[3]";
	String fprice="//div[@id='Description']/h2";
	String SItem="((//*[text()='POPULAR ITEMS'])[2]/..//p[contains(@name,'popular_item')])[2]";
	String sitemClick="((//*[text()='POPULAR ITEMS'])[2]/..//label[contains(@id,'details')])[2]";
	Double totalPrice=0.0;
	String checkoutPrice="//button[@id='checkOutPopUp']";
	String cartHover="//a[@id='shoppingCartLink']";
	String xIcon="(//div[@class='removeProduct iconCss iconX'])";
	String emptyCart="(//label[@translate='Your_shopping_cart_is_empty'])[1]";
	String cartCount="(//span[contains(@ng-show,'productsCartCount')])[last()]";
	String menuCart="//*[@id='menuCart']";
	String itemPrice="(//*[contains(@class,'price')])[1]";
	
	String id="speakersTxt";
	String shopNow="(//label[contains(text(),'Shop Now')])[1]";
	String compatDrpDwn="h4#accordionAttrib0";
	String blueName="compatibility_0";
	String itemText="//a[contains(@class,'productName')][contains(text(),'HP')]";
	
	public AD_ProductPage(WebDriver driver,Map<String, String> sTestData) {
		this.driver = driver;
		this.sTestData=sTestData;
		
	}

	public void searchProduct() throws InterruptedException {
		ElementActions.performClickableWait(driver, By.xpath(search));
		ElementActions.dynamicClick(driver, By.xpath(search));
		ElementActions.sendData(driver, By.xpath(searchInp), sTestData.get("SearchItem").trim());
		ElementActions.pause(6000);

		ElementActions.performVisibleWait(driver,
				By.xpath("(//*[text()='" + sTestData.get("SearchItem") + "'])[last()]"));
		// item = item.replace("dynText", sTestData.get("SearchItem"));
		// ElementActions.pause(3000);
		ElementActions.dynamicClick(driver,
				By.xpath("(//*[text()='" + sTestData.get("SearchItem") + "'])[last()]"));
		ElementActions.pause(3000);

	}

	public void addToCart() throws InterruptedException {
		ElementActions.performClickableWait(driver, By.xpath(addToCart));
		ElementActions.dynamicClick(driver, By.xpath(addToCart));
		ElementActions.dynamicClick(driver, By.xpath(checkout));
	}

	public void completePayment() throws InterruptedException {
		ElementActions.dynamicClick(driver, By.xpath(payment));

		driver.findElement(By.xpath(payUser)).clear();
		ElementActions.sendData(driver, By.xpath(payUser), "TestUser");

		driver.findElement(By.xpath(payPass)).clear();
		ElementActions.sendData(driver, By.xpath(payPass), "Test1");

		ElementActions.performVisibleWait(driver, By.id(payNext));
		ElementActions.dynamicClick(driver, By.id(payNext));

	}

	public Map<String, String> placeOrder() throws InterruptedException {
		ElementActions.pause(3000);
		map.put("message", driver.findElement(By.xpath(successPage)).getText());
		map.put("trackId", driver.findElement(By.xpath(track)).getText());
		map.put("orderId", driver.findElement(By.xpath(orderId)).getText());
		return map;

	}

	public void scrollElement() {
		ElementActions.scrollToView(driver, By.xpath(contact));
	}

	public void provideContactDetails() throws InterruptedException {
		ElementActions.selectItemsByText(driver, By.xpath(category), sTestData.get("Category"));
		ElementActions.pause(1000);
		ElementActions.selectItemsByText(driver, By.xpath(product), sTestData.get("Product"));
		ElementActions.sendData(driver, By.xpath(email), sTestData.get("EmailID"));
		ElementActions.sendData(driver, By.xpath(sub), "Test");
	}

	public void sendEmail() throws InterruptedException {
		ElementActions.dynamicClick(driver, By.id("send_btn"));
	}

	public void verifySendMailTitle() {
		ElementActions.performVisibleWait(driver, By.xpath(successMsg));
		Assert.assertEquals(driver.findElement(By.xpath(successMsg)).getText(),
				"Thank you for contacting Advantage support.");
	}
	
	public void scrollToPop() {
		ElementActions.scrollToView(driver, By.xpath(pop));
		
	}
	
	public String fItemCapture() throws InterruptedException {
		String fItemTxt=driver.findElement(By.xpath(fItem)).getText();
		ElementActions.dynamicClick(driver, By.xpath(itemClick));
		return fItemTxt;
	}
	
	public String fItemCart() throws InterruptedException {
		//String fPriceTxt=driver.findElement(By.xpath(fprice)).getText();
		//ElementActions.pause(2000);
		
		ElementActions.performClickableWait(driver, By.xpath(addToCart));
		//ElementActions.dynamicClick(driver, By.xpath(addToCart));
			//ElementActions.pause(2000);
			String str=ElementActions.getTextContent(driver, By.xpath("(//span[contains(@ng-show,'productsCartCount')])[last()]"));
			System.out.println("Cart Count:"+str);
			int count=Integer.valueOf(str);
			ElementActions.dynamicClick(driver, By.xpath(addToCart));
			//ElementActions.pause(3000);
			int incCount=Integer.valueOf(ElementActions.getTextContent(driver, By.xpath("(//span[contains(@ng-show,'productsCartCount')])[last()]")));
			System.out.println("Incremented Count"+incCount);
			while(!(incCount>=count+1 && incCount<=count+2))
			{
				ElementActions.dynamicClick(driver, By.xpath(addToCart));
				ElementActions.pause(2000);
				incCount=Integer.valueOf(ElementActions.getTextContent(driver, By.xpath("(//span[contains(@ng-show,'productsCartCount')])[last()]")));
				System.out.println("Incremented Count"+incCount);
				
			}
			
		ElementActions.pause(2000);
		String fPriceTxt=driver.findElement(By.xpath(itemPrice)).getText();
		String price=fPriceTxt.replace("$","").replace(",", "");
		totalPrice+=Double.valueOf(price);
		return fPriceTxt;
	}
	
	public String SItemCapture() throws InterruptedException {
		String sItemTxt=driver.findElement(By.xpath(SItem)).getText();
		ElementActions.dynamicClick(driver, By.xpath(sitemClick));
		ElementActions.pause(2000);
		
		return sItemTxt;
	}
	
	public Double TotalPriceCheck() {
		ElementActions.moveToElemAction(driver,By.xpath(cartHover) );
		String temp=driver.findElement(By.xpath(checkoutPrice)).getText();
		System.out.println("checkoutprice"+temp);
		temp=temp.substring(temp.indexOf('$'), temp.indexOf(')')).replace(",", "");
		Double cPrice=Double.valueOf(temp.replace("$", ""));
		Assert.assertEquals(cPrice,totalPrice);
		return cPrice;
	}
	
	public void cartClear() throws InterruptedException {
		List<WebElement> list=driver.findElements(By.xpath(xIcon));
		System.out.println("Cart Size:" +list.size());
		for(int i=0;i<list.size();i++) {
			System.out.println(driver.findElement(By.xpath(xIcon+"["+(1)+"]")));
			ElementActions.dynamicClick(driver, By.xpath(xIcon+"["+(1)+"]"));
		}
	}
	
	public void EmptyCart() throws InterruptedException {
		ElementActions.dynamicClick(driver, By.xpath(menuCart));
		Assert.assertEquals(driver.findElement(By.xpath(emptyCart)).getText(), "Your shopping cart is empty");
	}
	
	public void categoryItem() throws InterruptedException {
		ElementActions.scrollToView(driver, By.id(id));
		ElementActions.dynamicClick(driver, By.xpath(shopNow));
	}
	public void compatibileItem() throws InterruptedException {
		ElementActions.dynamicClick(driver, By.cssSelector(compatDrpDwn));
		ElementActions.dynamicClick(driver, By.name(blueName));
	}
	public void selectItem() throws InterruptedException {
		ElementActions.dynamicClick(driver, By.xpath(itemText));
	}

	}
