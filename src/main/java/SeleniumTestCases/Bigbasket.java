package SeleniumTestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Bigbasket {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://www.bigbasket.com/";

		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Actions act = new Actions(driver);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);

//		2) mouse over on  Shop by Category
		WebElement shop = driver.findElement(By.xpath("//a[text()=' Shop by Category ']"));
		act.moveToElement(shop).perform();
		System.out.println("Mouse hover performed on Shop Category");
		Thread.sleep(3000);

//		Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS
		WebElement food = driver.findElement(By.xpath("(//a[text()='Foodgrains, Oil & Masala'])[2]"));
		act.moveToElement(food).perform();
		System.out.println("Mouse hover performed on Foodgrains & Oil and masala");
		Thread.sleep(3000);
		WebElement riceproducts = driver.findElement(By.xpath("(//a[text()='Rice & Rice Products'])[2]"));
		act.moveToElement(riceproducts).click().build().perform();
		System.out.println("Mouse hover performed by Rice and Rice products");

//	    Click on Boiled & Steam Rice
		WebElement boiled = driver
				.findElement(By.xpath("(//div[@class='col-xs-12 checkbox subcat ng-scope']//span[text()='Boiled & Steam Rice'])[1]/parent::div"));
		act.moveToElement(boiled).click();
		System.out.println("Boiled and Steam Rice Clicked");

		Thread.sleep(3000);
//	    Choose the Brand as bb Royal
		WebElement royal = driver.findElement(By.xpath("(//div[@class='col-xs-12 ng-scope']//span[text()='bb Royal'])[1]//preceding-sibling::span"));
		royal.click();
		System.out.println("Brand - BB Royal clicked");
		Thread.sleep(5000);

		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);

//      Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
		WebElement ele = driver.findElement(
				By.xpath("(//a[text()='Ponni Boiled Rice - Super Premium']/parent::div/following-sibling::div//i)[1]"));
		js.executeScript("arguments[0].click()", ele);
		WebElement kg = driver.findElement(By.xpath("//a[text()='Ponni Boiled Rice - Super Premium']/ancestor::div[@class='item prod-deck row ng-scope']//ul[@class='dropdown-menu drop-select']//span[text()='5 kg']"));
		kg.click();
		System.out.println("Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown");

		
		//Print the price of rice
		String text = driver.findElement(By.xpath("//a[text()='Ponni Boiled Rice - Super Premium']/ancestor::div[@class='item prod-deck row ng-scope']//span[@class='discnt-price']")).getText();
		String amt = text.replaceAll("\\D", "");
		double parseDouble = Double.parseDouble(amt);
		System.out.println("The Boiled price amt is: " +parseDouble);
//		Click on ADD TO BASKET
		driver.findElement(By.xpath("(((//a[text()='Ponni Boiled Rice - Super Premium']//parent::div)//following-sibling::div)[2])//button[text()='Add ']")).click();

		// Verify the success message displayed
//		String addMsg = driver.findElement(By.xpath("//div[@class='toast-title']")).getText();
//		if (addMsg.equalsIgnoreCase("Successfully added Ponni Boiled Rice - Super Premium 5 kg to the basket")) {
//			System.out.println("The meassage after adding rice into cart is " + addMsg);
//			Thread.sleep(3000);
//		} else {
//			System.out.println("Wrong message displayed" + addMsg);
//		}

		//Type Dal in Search field and enter
		driver.findElement(By.xpath("//input[@id='input']")).sendKeys("Dal",Keys.ENTER);

		//Go to Toor/Arhar Dal and select 2kg & set Qty 2
		driver.findElement(By.xpath("(//div[@qa='product_name']/a)[3]")).click();
		driver.findElement(By.xpath("//div[text()='2 kg']")).click();
		driver.findElement(By.xpath("//input[@name='qty']")).sendKeys(Keys.BACK_SPACE,"2");

		//Print the price of Dal
		String price2 = driver.findElement(By.xpath("//td[@data-qa='productPrice']")).getText();
		System.out.println("Price of Toor/Arhar Dal :" + price2);

		//Click on ADD TO BASKET
		driver.findElement(By.xpath("//span[text()='ADD TO BASKET']")).click();
        Thread.sleep(3000);
		//Mouse hover on My Basket
		WebElement tar5 = driver.findElement(By.xpath("//div[@data-qa='myBasket']"));
		act.moveToElement(tar5).perform();

		//Validate the Sub Total displayed for the selected items
		String product1 = driver.findElement(By.xpath("(//div[@class='row mrp']/span)[1]")).getText();
		String product2 = driver.findElement(By.xpath("(//div[@class='row mrp']/span)[2]")).getText();
		String total = driver.findElement(By.xpath("//span[@style='float: right']/span")).getText();
		int sum = Integer.parseInt(product1)+Integer.parseInt(product2);
		int subTotal = Integer.parseInt(total);
		if (sum == subTotal) {
			System.out.println("Total amount matches the sum of individual product amount");
		}else {
			System.out.println("Mismatch in amount information");
		}

		//Reduce the Quantity of Dal as 1 
		driver.findElement(By.xpath("(//button[@ng-click = 'vm.decreamentQuantity(cartItem);'])[2]")).click();

		//Validate the Sub Total for the current items
		String newProduct2 = driver.findElement(By.xpath("(//div[@class='row mrp']/span)[2]")).getText();
		String newTotal = driver.findElement(By.xpath("//span[@style='float: right']/span")).getText();
		int nsum = Integer.parseInt(product1)+Integer.parseInt(newProduct2);
		int nsubtot = Integer.parseInt(newTotal);
		if (nsum == nsubtot) {
			System.out.println("Total amount matches with the quantity of Dal as 1");
		}else {
			System.out.println("Mismatch in amount information after reducing the Quantity of Dal as 1");
		}
		
		
		System.out.println("Big Basked testcase Completed");
		driver.close();
	}

}
