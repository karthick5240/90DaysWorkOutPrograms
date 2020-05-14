package SeleniumTestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException {

		
		String url = "https://www.snapdeal.com/";
		
		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Url Launched");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		Actions act = new Actions(driver);
		
//		Mouse over on Toys, Kids' Fashion & more and click on Toys
		WebElement toy = driver.findElement(By.xpath("(//span[@class='catText'])[8]"));
		act.moveToElement(toy).perform();
		System.out.println("Toys, Kids' Fashion & more is Clicked");
		WebElement toys = driver.findElement(By.xpath("//span[text()='Toys']"));
		act.moveToElement(toys).click().build().perform();
		System.out.println("Toys is Clicked");
		Thread.sleep(3000);
		
		
//		Click Educational Toys in Toys & Games
		WebElement edutoys = driver.findElement(By.xpath("//div[text()='Educational Toys']"));
		edutoys.click();
		System.out.println("Educational toys is Clicked");
		Thread.sleep(2000);
		
//		Click the Customer Rating star and Up 
		js.executeScript("window.scrollBy(0,800)", "");
		WebElement rating = driver.findElement(By.xpath("//label[@for='avgRating-4.0']"));
		rating.click();
		System.out.println("Average rating menu is Clicked");
		Thread.sleep(2000);

//		Click the offer as 40-50
		js.executeScript("window.scrollBy(0,600)", "");
		WebElement offer = driver.findElement(By.xpath("(//div[@class='sdCheckbox filters-list '])[10]"));
		offer.click();
		System.out.println("40-50 offer menu is Clicked");
		Thread.sleep(2000);
		
//		Check the availability for the pincode
		WebElement xcent = driver.findElement(By.xpath("(//input[@class='sd-input'])[2]"));
		xcent.sendKeys("600061");
		System.out.println("Pincode menu is Clicked");
		WebElement check = driver.findElement(By.xpath("//button[text()='Check']"));
		check.click();
		if (check.equals(600061)) {
			System.out.println("Product is available in ur area ");
		} else {
			System.out.println("Product is not available in ur area ");
		}
		Thread.sleep(10000);
		
//		Click the Quick View of the first product
		WebElement prod = driver.findElement(By.xpath("(//section[@class='js-section clearfix dp-widget  dp-fired'])[1]/div[1]"));
		act.moveToElement(prod).perform();
		WebElement quickview = driver.findElement(By.xpath("(//div[@class='clearfix row-disc'])[1]//div[@class ='center quick-view-bar  btn btn-theme-secondary  ']"));
		quickview.click();
		System.out.println("Quick View product is clicked");
		Thread.sleep(3000);
		
		
//		Click on View Details
		WebElement view = driver.findElement(By.xpath("//a[contains(text(),'view details')]"));
		view.click();

//		Capture the Price of the Product and Delivery Charge
		String prodprice = driver.findElement(By.xpath("//span[@class='pdp-final-price']")).getText();
		String origamt = prodprice.replaceAll("\\D", "");
		int actual = Integer.parseInt(origamt);
		System.out.println("The product price amount is:" +actual);
		String delivery = driver.findElement(By.xpath("(//span[@class='availCharges'])[2]")).getText();
		String delamt = delivery.replaceAll("\\D", "");
		int finalprice = Integer.parseInt(delamt);
		System.out.println("The delivery Charge amount is:" +finalprice);
		driver.findElement(By.xpath("//span[text()='add to cart']")).click();
		
		
//		Validate the You Pay amount matches the sum of (price+deliver charge)
		int totalprice = Integer.parseInt(origamt)+Integer.parseInt(delamt);
		System.out.println("The toal Charge amount is:" +totalprice);
		
//		Search for Sanitizer
		WebElement search = driver.findElement(By.id("inputValEnter"));
		search.sendKeys("Sanitizer",Keys.ENTER);
		System.out.println("Sanitizer has been entered on the search box");
		WebElement neem = driver.findElement(By.xpath("(//p[@class='product-title'])[1]"));
		neem.click();
		System.out.println("BioAyurveda Neem Power Hand Sanitizer is clicked");
		
//		Capture the Price and Delivery Charge
		Set<String> allwin = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(allwin);
		driver.switchTo().window(ls.get(1));
		String prodprice1 = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		int actual1 = Integer.parseInt(prodprice1);
		System.out.println("The product price amount is:" +actual1);
		String delivery1 = driver.findElement(By.xpath("(//span[@class='availCharges'])[2]")).getText();
		String delamt1 = delivery1.replaceAll("\\D", "");
		int delorig1 = Integer.parseInt(delamt1);
		System.out.println("The delivery Charge amount is:" +delorig1);
		
		
//		Click on Add to Cart
		WebElement add = driver.findElement(By.xpath("(//div[@id='add-cart-button-id'])[1]"));
		add.click();
		System.out.println("Add Cart button is clicked");
		
//		Click on Cart
		WebElement cart = driver.findElement(By.xpath("//div[@class='cartInner']"));
		cart.click();
		System.out.println("Cart button is clicked");
		
		
//		Validate the Proceed to Pay matches the total amount of both the products
		String CartTotal = driver.findElement(By.xpath("//input[contains(@value, 'PROCEED TO PAY')]")).getAttribute("value");
		System.out.println(CartTotal);
		CartTotal = CartTotal.replaceAll("\\D", "");
		System.out.println("Cart Total is: "+CartTotal);
	
		driver.close();

	}
}