package SeleniumTestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class HP {

	public static void main(String[] args) throws InterruptedException {

		String url = "https://store.hp.com/in-en/";
		
		// To display the console output in readable manner
		System.setProperty("webdriver.chrome.silentOutput", "true");

//      Launch the browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		Thread.sleep(3000);

		try {
			driver.findElement(By.xpath("//span[contains(@class,'close-icon')]")).click();
		} catch (Exception e) { 
			System.out.println("Sign up and Email alerts is not displayed.");
			e.printStackTrace();
		}
		// Mouse over on Laptops menu and click on Pavilion
		Actions ac = new Actions(driver);
//		WebElement laptop = driver.findElement(By.xpath("(//span[text()='Laptops'])[1]"));
		WebElement laptop = driver.findElement(By.linkText("Laptops"));
		ac.moveToElement(laptop).perform();
		System.out.println("Mouse hover done on Laptops");
		Thread.sleep(5000);
		WebElement pav = driver.findElement(By.xpath("(//a[@role='menuitem']//span[text()='Pavilion'])[1]"));
		pav.click();
		System.out.println("Mouse hover done on Pavillion");

//		Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
        WebElement process = driver.findElement(By.xpath("(//span[text()='Processor'])[2]"));
        process.click();
        JavascriptExecutor js  = ((JavascriptExecutor)driver);
        js.executeScript("window.scrollBy(0,200)");
        WebElement intel = driver.findElement(By.xpath("(//input[@class='product-filter-checkbox'])[2]"));
        intel.click();
        System.out.println("Intel i7 Clicked");
        Thread.sleep(3000);
         
//      Hard Drive Capacity -->More than 1TB
        js.executeScript("window.scrollBy(0,200)");
        try {
        	driver.findElement(By.xpath("//div[@class='inside_notify_content']"));
        } catch(Exception e) {
        	System.out.println("Dont miss out icon is not Closed");
        	e.printStackTrace();
        }
        WebElement tb = driver.findElement(By.xpath("(//input[@class='product-filter-checkbox'])[21]"));
        tb.click();
        
//      Select Sort By: Price: Low to High
        WebElement sort = driver.findElement(By.id("sorter"));
        Select s = new Select(sort);
        s.selectByValue("price_asc");
        
//      Print the First resulting Product Name and Price
        List<WebElement> pname = driver.findElements(By.xpath("//a[@class='product-item-link']"));
        System.out.println("The product name is:" +pname.get(0).getText());
        Thread.sleep(2000);
        List<WebElement> price = driver.findElements(By.xpath("(//span[@class='price'])[2]"));
        System.out.println("The Product price is:" +price.get(0).getText());
        
        //Click on Add to Cart
          WebElement addcart = driver.findElement(By.xpath("(//button[@class='action tocart primary'])[1]"));
          addcart.click();
        
//          Click on Shopping Cart icon --> Click on View and Edit Cart
          WebElement shopcart = driver.findElement(By.xpath("//a[@class='action showcart']"));
          shopcart.click();
          WebElement viewcart = driver.findElement(By.xpath("//a[@class='action primary viewcart']"));
          viewcart.click();
          WebElement pincode = driver.findElement(By.name("pincode"));
          pincode.sendKeys("600061");
          
          WebElement total = driver.findElement(By.xpath("(//span[@class='price'])[7]"));
          System.out.println("Total amount is:" +total);
          if (price.equals(total)) {
			System.out.println("Both product price and total amt are same");
		} else {
			System.out.println("Both product price and total amt are not  same");
		}
				 driver.findElement(By.xpath("( //span[text()='Proceed to Checkout'])[1]")).click();
			      Thread.sleep(3000);
			      js.executeScript("window.scrollBy(0, 250)");
			      driver.findElement(By.xpath("(//span[text()='Place Order'])[4]")).click();
			      String message = driver.findElement(By.xpath("//div[@class='message notice']")).getText();
			      System.out.println(message);

			      System.out.println("HP Test Cases Completed");
			      
			 driver.close();
        
//		driver.close();
	}

}
