package SeleniumTestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class ajio {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://www.ajio.com/shop/women";
		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(op);
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Browser Launched");
		System.out.println("Url Loaded");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		JavascriptExecutor js = ((JavascriptExecutor) driver);

//		Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElement(By.xpath("//input[@placeholder='Search AJIO']")).sendKeys("Bags");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Women Handbags']/parent::a")).click();
		String pageHeader = driver.findElement(By.xpath("//h1/div[2]")).getText();
		System.out.println(pageHeader);

//		Click on five grid and Select SORT BY as "What's New"
		WebElement fivegrid = driver.findElement(By.xpath("//div[@class='five-grid']"));
		fivegrid.click();
		System.out.println("Five grid is selected");
		WebElement whatsnew = driver.findElement(By.xpath("//div[@class='filter-dropdown']/child::select"));
		Select s = new Select(whatsnew);
		s.selectByVisibleText("What's New");
		System.out.println("Whats new option is selected");
		Thread.sleep(5000);

//		Enter Price Range Min as 2000 and Max as 5000
		WebElement price = driver.findElement(By.xpath("//span[text()='price']"));
		price.click();
		System.out.println("Price is Clicked");
		driver.findElement(By.id("minPrice")).sendKeys("2000");
		driver.findElement(By.id("maxPrice")).sendKeys("5000");
		driver.findElement(By.xpath("//div[@class='facet-min-price-filter']/button")).click();
		System.out.println("Price range set between 2000 and 5000");

//		Click on the product "BAGGIT Red Totes & Hobos Hobo Handbag with Detachable Strap"
		js.executeScript("window.scrollBy(0,600)", "");
		WebElement puma = driver.findElement(By.xpath("(//div[@class='item rilrtl-products-list__item item'])[27]"));
		puma.click();
		System.out.println("BAGGIT Red Totes & Hobos Hobo Handbag with Detachable Strap is Clicked");
		Set<String> allwin = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(allwin);
		driver.switchTo().window(ls.get(1));

//		Verify the Coupon code for the price above 2690 is applicable for your product, if applicable the get the Coupon Code and Calculate the discount price for the coupon
		String brand = driver.findElement(By.xpath("//div[@class='prod-content']/h2")).getText();
		String prod = driver.findElement(By.xpath("//div[@class='prod-content']/h1")).getText();
		System.out.println("Brand: " + brand);
		System.out.println("Product: " + prod);
		String rawPrice = driver.findElement(By.xpath("//div[@class='prod-price-section']/div[@class='prod-sp']"))
				.getText();
		int prodPrice = Integer.parseInt(rawPrice.replaceAll("\\D", ""));
		System.out.println("Product price: " + prodPrice);
		WebElement promodetails = driver.findElement(By.xpath("//div[@class='promo-desc']"));
		if (prodPrice > 2690) {
			Thread.sleep(500);
			if (promodetails.getText().contains("Extra Upto 33% Off on 2790 and Above ")) {
				String coupon = driver.findElement(By.xpath("//div[@class='promo-title']")).getText();
				System.out.println("Coupon code applicable for product price above 2690 is: " + coupon);
			} else
				System.err.println("Coupon code not displayed for product price below 2690");
		}

//		Check the availability of the product for pincode 560043, print the expected delivery date if it is available
		WebElement pincode = driver.findElement(By.xpath("//div[@class='edd-pincode-msg-container']"));
		pincode.click();
		System.out.println("Pincode is Clicked");
		WebElement pinenter = driver.findElement(By.xpath("//input[@class='edd-pincode-modal-text']"));
		pinenter.sendKeys("560043");
		System.out.println("Pincode 560043 is entered");
		WebElement confirm = driver.findElement(By.xpath("//button[text()='CONFIRM PINCODE']"));
		confirm.click();
		System.out.println("Confirm pincode is clicked");

		Thread.sleep(2000);
		String delivery = driver.findElement(By.xpath("//div[@class='edd-message-success']")).getText();
		System.out.println("The Delivery message are:" + delivery);
		if (delivery.contains("Expected Delivery")) {
			System.out.println("Product available for pincode specified");
		} else {
			System.out.println("Product not available for pincode specified");
		}

//		Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		js.executeScript("window.scrollBy(0,200)", "");
		WebElement otherinfo = driver.findElement(By.xpath("//div[text()='Other information']"));
		otherinfo.click();
		System.out.println("Other Information is Clicked");
		List<WebElement> addrprint = driver.findElements(By.xpath("(//span[@class='other-info'])[6]"));
		for (WebElement print : addrprint) {
			String text = print.getText();
			System.out.println(text);

		}

//         Click on ADD TO BAG and then GO TO BAG
		WebElement addbag = driver.findElement(By.xpath("//div[@class='btn-gold']"));
		addbag.click();
		System.out.println("Add Bag is Clicked");
		WebElement gobag = driver.findElement(By.xpath("//div[@class='btn-cart']"));
		gobag.click();
		System.out.println("Goto Bag is Clicked");

//	       Check the Order Total before apply coupon
		Thread.sleep(2000);
		String strOrderTotal = driver.findElement(By.xpath("//span[text()='Order Total']/following-sibling::span")).getText();
		String strOrderTot = strOrderTotal.replaceAll("\\D", "");
		int total = Integer.parseInt(strOrderTot.substring(0, strOrderTot.length() - 2));
		System.out.println("Order total before applying coupon code: " + total);
		
		
//		Enter Coupon Code and Click Apply
		WebElement couponapply = driver.findElement(By.xpath("(//span[@class='ic-Radio-Buton-Of default-radio-button '])[1]"));
		couponapply.click();
		System.out.println("Coupon code is clicked");
		WebElement apply = driver.findElement(By.linkText("Apply"));
		apply.click();
		System.out.println("Coupon code is applied");
		
//		Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details
	    String couponprice = driver.findElement(By.xpath("(//span[@class='price-value discount-price'])[2]")).getText();
	    String Coupamt = couponprice.replaceAll("\\D", "");
	    int couptot = Integer.parseInt(Coupamt);
	    System.out.println("The Coupon savings amount are:" +couptot);
	    
	
//	    Click on Delete and Delete the item from Bag
		driver.findElement(By.xpath("//div[@class='product-delete']/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='card-delete-button']//div[text()='DELETE']")).click();
		String emptyCartMsg = driver.findElement(By.xpath("//div[@class='empty-cart']/p")).getText();
		if(emptyCartMsg.contains("Your Shopping Bag is Empty"))
		{
			System.out.println("Item deleted from bag");
		
	}
		
//		Close all the browsers
		driver.quit();
	}

}
